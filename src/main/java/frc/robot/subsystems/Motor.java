package frc.robot.subsystems.Motors;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

/**
 * Subsystem to control two TalonFX motors independently (or optionally make the
 * top motor follow the bottom).
 */
public class Motors extends SubsystemBase {
    private static final int DEFAULT_BOTTOM_ID = 1;
    private static final int DEFAULT_TOP_ID = 2;
    private static final int TICKS_PER_REV = 2048; // TalonFX integrated sensor

    private final TalonFX bottom;
    private final TalonFX top;

    public MotorsSubsystem() {
        this(DEFAULT_BOTTOM_ID, DEFAULT_TOP_ID, false); // independent by default
    }

    /**
     * Construct with the given CAN IDs and option for top to follow bottom.
     * If topFollows is false, both motors are independently controllable.
     */
    public MotorsSubsystem(int bottomCanId, int topCanId, boolean topFollows) {
        bottom = new TalonFX(bottomCanId);
        top = new TalonFX(topCanId);

        bottom.configFactoryDefault();
        top.configFactoryDefault();

        if (topFollows) {
            top.follow(bottom);
        }

        // Use brake mode by default
        bottom.setNeutralMode(NeutralMode.Brake);
        top.setNeutralMode(NeutralMode.Brake);

        // Ensure encoders are zeroed on start
        bottom.setSelectedSensorPosition(0);
        top.setSelectedSensorPosition(0);
    }

    /**
     * Set bottom motor output as percent (-1.0 .. 1.0).
     */
    public void setBottomPercent(double percent) {
        bottom.set(ControlMode.PercentOutput, percent);
    }

    /**
     * Set top motor output as percent (-1.0 .. 1.0).
     * If top was configured to follow bottom, calling this will override that behavior.
     */
    public void setTopPercent(double percent) {
        top.set(ControlMode.PercentOutput, percent);
    }

    /**
     * Set both motors to the same percent output.
     */
    public void setBothPercent(double percent) {
        setBottomPercent(percent);
        setTopPercent(percent);
    }

    /**
     * Stop both motors (zero output).
     */
    public void stop() {
        setBothPercent(0.0);
    }

    /**
     * Get encoder position in rotations for bottom motor.
     */
    public double getBottomPositionRotations() {
        return bottom.getSelectedSensorPosition() / (double) TICKS_PER_REV;
    }

    /**
     * Get encoder position in rotations for top motor.
     */
    public double getTopPositionRotations() {
        return top.getSelectedSensorPosition() / (double) TICKS_PER_REV;
    }

    /**
     * Get motor velocity in RPM for bottom motor.
     */
    public double getBottomVelocityRPM() {
        double ticksPer100ms = bottom.getSelectedSensorVelocity();
        return ticksPer100ms * 600.0 / TICKS_PER_REV;
    }

    /**
     * Get motor velocity in RPM for top motor.
     */
    public double getTopVelocityRPM() {
        double ticksPer100ms = top.getSelectedSensorVelocity();
        return ticksPer100ms * 600.0 / TICKS_PER_REV;
    }

    /**
     * Reset bottom encoder position to zero.
     */
    public void resetBottomEncoder() {
        bottom.setSelectedSensorPosition(0);
    }

    /**
     * Reset top encoder position to zero.
     */
    public void resetTopEncoder() {
        top.setSelectedSensorPosition(0);
    }

    /**
     * Reset both encoders to zero.
     */
    public void resetEncoders() {
        resetBottomEncoder();
        resetTopEncoder();
    }
}