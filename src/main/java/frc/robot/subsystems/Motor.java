package frc.robot.subsystems;

import com.ctre.phoenix6.controls.DutyCycleOut;
import com.ctre.phoenix6.hardware.TalonFX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

/**
 * Subsystem to control two TalonFX motors independently (or optionally make the
 * top motor follow the bottom).
 */
public class Motor extends SubsystemBase {

    private static final int TICKS_PER_REV = 2048; // TalonFX integrated sensor

    private final TalonFX motor;
    final DutyCycleOut m_dutyCycle = new DutyCycleOut(0);
    double speed = 0;
    int m_id = 0;
    boolean isRunning = false;
    
    public void setPercentIfRunning (double speed) {
    if (isRunning == true) {
        setPercent(speed);
    } else{
        setPercent(0);
    }
}

    public Motor(int id) {
        m_id = id;
        motor = new TalonFX(id);
        motor.setPosition(0);
    }


    public void setPercent(double percent) {
        System.out.println("Setting motor "+ m_id + " speed to " + percent);
        motor.setControl(m_dutyCycle.withOutput(percent));
    }


    public void changeSpeed(double input) {
        speed = speed + input;
        if (speed > 1) {
            speed = 1;
        } else if (speed < -1) {
            speed = -1;
        }
        setPercent(speed);
    }

    public void faster() {
        speed = speed + 0.1;
        if (speed > 1) { 
            speed = 1;
        }
        setPercentIfRunning(speed);
    }

    public void slower() {
        speed = speed - 0.1;
        if (speed < -1) {
            speed = -1;
        }
        setPercentIfRunning(speed);

    }

    public void stop() {
        speed = 0;
        setPercent(0.0);
    }

    public double getPositionRotations() {
        return motor.getPosition().getValueAsDouble() / (double) TICKS_PER_REV;
    }


    public double getVelocityRPM() {
        double ticksPer100ms = motor.getVelocity().getValueAsDouble();
        return ticksPer100ms * 600.0 / TICKS_PER_REV;
    }


    public void resetEncoder() {
        motor.getPosition().getValueAsDouble();
    }

    public void toggleIsRunning() {
        isRunning = !isRunning;
        setPercentIfRunning(speed);
    }
}