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
    Motor bottomShooter = new Motor(2);
    Motor leftConveyor = new Motor(3);
    Motor rightConveyor = new Motor(4);
    CommandXboxController controller = new CommandXboxController(0);

  public RobotContainer() {
    configureBindings();
  }

  private void configureBindings() {
      controller.a().onTrue(Commands.runOnce((topShooter::faster),topShooter));
      controller.b().onTrue(Commands.runOnce((topShooter::slower),topShooter));
      controller.povUp().onTrue(Commands.runOnce((bottomShooter::faster),bottomShooter));
      controller.povDown().onTrue(Commands.runOnce((bottomShooter::slower),bottomShooter));
      //controller.x().onTrue(Commands.runOnce(topShooter::stop,topShooter).andThen(Commands.runOnce(bottomShooter::stop,bottomShooter)));
      controller.y().onTrue(Commands.runOnce(topShooter::toggleIsRunning, topShooter).andThen(bottomShooter::toggleIsRunning, bottomShooter));
  }


  public Command getAutonomousCommand() {
    return Commands.print("No autonomous command configured");

  }
}
