package com.qualcomm.ftcrobotcontroller.opmodes.moneybot;

import com.qualcomm.ftcrobotcontroller.opmodes.moneybot.util.DriveMotors;
import com.qualcomm.ftcrobotcontroller.opmodes.moneybot.util.Lift;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

/**
 * Controls for TeleOP:
 * Joysticks: Control drive motor movement
 * D-Pad: Up makes lift go forward, Down makes lift go backward
 * X: Reverses controls
 * A:
 */
public class TeleOP extends LinearOpMode {

    @Override
    public void runOpMode()
            throws InterruptedException
    {
        DriveMotors motors = new DriveMotors(hardwareMap.dcMotor.get("left_drive"),
                hardwareMap.dcMotor.get("right_drive"));

        Lift lift = new Lift(hardwareMap.dcMotor.get("lift"));

        waitForStart();

        while (opModeIsActive())
        {
            motors.drive(gamepad1);
            lift.runLift(gamepad1);

            // TODO: Telemetry
            waitForNextHardwareCycle();
        }
    }
}
