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
import frc.robot.subsystems.Motors;


public class RobotContainer {
    TalonFX Motor1 = new TalonFX(1);
    TalonFX Motor2 = new TalonFX(2);
    double speed1 = 0;
    double speed2 = 0;
    MotorSafety motors = new Motors();
    CommandXboxController controller = new CommandXboxController(0);

  public RobotContainer() {


    configureBindings();
  }

  private void configureBindings() {
      controller.a().onTrue(Commands.runOnce((Motor1) -> faster),motors);
  }

  public void faster(TalonFX motor) {
    DutyCycleOut control = new DutyCycleOut(

    )
    motor.setControl()
    motor.dutyCyclemotor.getDutyCycle() + 0.1 
  }
  public Command getAutonomousCommand() {
    return Commands.print("No autonomous command configured");

  }
}
