// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.RunCommand;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Arm;
public class RobotContainer {
  Drivetrain m_dt = new Drivetrain();
  Arm m_arm = new Arm();
  CommandXboxController m_XboxController = new CommandXboxController(0);
  
  public RobotContainer() {
    configureBindings();
  }

  private void configureBindings() {
    m_dt.setDefaultCommand(new RunCommand(() -> m_dt.arcadeDrive(m_XboxController.getLeftY(), m_XboxController.getRightX()), m_dt));
    m_arm.setDefaultCommand(m_arm.moveArm(0));
    

    m_XboxController.x().whileTrue(m_arm.moveArm(180));
    m_XboxController.a().whileTrue(m_arm.moveArm(135));
    m_XboxController.b().whileTrue(m_arm.moveArm(90));
    m_XboxController.y().whileTrue(m_arm.moveArm(45));
  }

  public Command getAutonomousCommand() {
    return Commands.print("No autonomous command configured");
  }
}
