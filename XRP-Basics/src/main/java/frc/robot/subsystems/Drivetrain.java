// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.xrp.XRPMotor;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Drivetrain extends SubsystemBase {
  /** Creates a new Drivetrain. */

  private final XRPMotor leftMotor = new XRPMotor(0);
  private final XRPMotor rightMotor = new XRPMotor(1);
  private final Encoder leftEncoder = new Encoder(4,5);
  private final Encoder rightEncoder = new Encoder(6,7);

  public Drivetrain() {
    rightMotor.setInverted(true);
  }

  public void arcadeDrive(final double fwd, final double turn){
    double left = fwd + turn;
    double right = fwd - turn;
    leftMotor.set(left);
    rightMotor.set(right);
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
  }
}
