// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.ctre.phoenix6.controls.DutyCycleOut;
import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.wpilibj.MotorSafety;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.subsystems.Motor;


public class RobotContainer {

    Motor topShooter = new Motor(1);
    Motor bottomShooter = new Motor(4);
//    Motor leftConveyor = new Motor(3);
//    Motor rightConveyor = new Motor(4);
    CommandXboxController controller = new CommandXboxController(0);

  public RobotContainer() {
    configureBindings();
  }

  private void configureBindings() {
      controller.povUp().onTrue(Commands.runOnce((topShooter::faster),topShooter));
      controller.povDown().onTrue(Commands.runOnce((topShooter::slower),topShooter));
      controller.x().onTrue(Commands.runOnce((() -> bottomShooter.setPercent(40)), bottomShooter));
      controller.x().onFalse(Commands.runOnce((() -> bottomShooter.setPercent(0)), bottomShooter));
      //controller.x().onTrue(Commands.runOnce(topShooter::stop,topShooter).andThen(Commands.runOnce(bottomShooter::stop,bottomShooter)));
      controller.y().onTrue(Commands.runOnce(topShooter::toggleIsRunning, topShooter));
  }


  public Command getAutonomousCommand() {
    return Commands.print("No autonomous command configured");

  }
}
