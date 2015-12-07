package com.qualcomm.ftcrobotcontroller.opmodes.moneybot.util;

import com.qualcomm.robotcore.robocol.Telemetry;

/**
 * Class to handle all telemetry updates
 */

public class TelemetryUpdate
{
    private DriveMotors driveMotors;
    private Lift lift;

    private Telemetry telemetry;

    public TelemetryUpdate(Telemetry telemetry, DriveMotors driveMotors, Lift lift)
    {
        this.telemetry = telemetry;
        this.driveMotors = driveMotors;
        this.lift = lift;
    }

    public void updateTelemetry()
    {
        telemetry.addData("Drive Motors: ", driveMotors.getStatus());
        telemetry.addData("Lift: ", lift.getStatus());
    }

    public void wipeTelemetry()
    {
        telemetry.clearData();
    }
}
