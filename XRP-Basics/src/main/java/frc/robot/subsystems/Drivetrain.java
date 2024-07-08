// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.xrp.XRPGyro;
import edu.wpi.first.wpilibj.xrp.XRPMotor;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.FunctionalCommand;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Drivetrain extends SubsystemBase {
  /** Creates a new Drivetrain. */

  private final XRPMotor leftMotor = new XRPMotor(0);
  private final XRPMotor rightMotor = new XRPMotor(1);
  private final Encoder leftEncoder = new Encoder(4,5);
  private final Encoder rightEncoder = new Encoder(6,7);
  private final XRPGyro m_Gyro;
  private final PIDController m_PidController = new PIDController(0.09,0,0.00135);


  public Drivetrain() {
    m_Gyro = new XRPGyro();
    rightMotor.setInverted(true);
  }

  public void arcadeDrive(final double fwd, final double turn){
    double left = fwd + turn;
    double right = fwd - turn;
    leftMotor.set(left);
    rightMotor.set(right);
  }

  public double getAngleZ(){
    return m_Gyro.getAngleZ();
  }

  public double getAngleY(){
    return m_Gyro.getAngleY();
  }

  public double getAngleX(){
    return m_Gyro.getAngleX();
  }

  public void goToSetpoint(double angle){
    double turnSpeed = m_PidController.calculate(getAngleY(),angle);
    arcadeDrive(0, turnSpeed);  
  }

  public boolean isAtSetpoint(double angle){
    return MathUtil.isNear(angle, getAngleZ(), .5)
  }


  public void resetEncoders(){
    leftEncoder.reset();
    rightEncoder.reset();
  }

  public double getLeftDistanceInch(){
    return leftEncoder.getDistance();
  }

  public double getRightDistanceInch(){
    return rightEncoder.getDistance();
  }

  public double getAvgDistanceInch(){
    return (getLeftDistanceInch() + getRightDistanceInch())/2;
  }



  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    SmartDashboard.putNumber("AngleX", getAngleX());
    SmartDashboard.putNumber("AngleY", getAngleY());
    SmartDashboard.putNumber("AngleZ", getAngleZ())
  }

  public Command turnRobotCommand(double angle) {
    return new FunctionalCommand(
      () -> {},
      () -> goToSetpoint(angle), 
      interrupted -> arcadeDrive(0,0),
      () -> isAtSetpoint(angle),
      this);
  }
}