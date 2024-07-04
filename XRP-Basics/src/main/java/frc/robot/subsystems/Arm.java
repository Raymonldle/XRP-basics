// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.xrp.XRPServo;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Arm extends SubsystemBase {
  /** Creates a new Servo. */
  XRPServo arm = new XRPServo(4);
  public Arm() {
    arm.setAngle(0);
  }

  public Command moveArm(double angle){
    return run(() -> arm.setAngle(angle));
    //return runOnce(() -> arm.setAngle(angle));
  }

  public void moveArmX(double angle){
    arm.setAngle(angle);
  }

  

  

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
