package com.qualcomm.ftcrobotcontroller.opmodes.moneybot;

import com.qualcomm.ftcrobotcontroller.opmodes.moneybot.util.ClimberDepositor;
import com.qualcomm.ftcrobotcontroller.opmodes.moneybot.util.DriveMotors;
import com.qualcomm.ftcrobotcontroller.opmodes.moneybot.util.Lift;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

/**
 * Controls for TeleOP:
 * Joysticks: Control drive motor movement
 * D-Pad: Up makes lift go forward, Down makes lift go backward
 * X: Reverses controls
 * A: Switches between Tank Drive and Arcade Drive
 * Y: Opens/Closes the lift gate
 */
public class TeleOP extends LinearOpMode {

    @Override
    public void runOpMode()
            throws InterruptedException
    {
        // DC Motors
        DriveMotors motors = new DriveMotors(hardwareMap.dcMotor.get("left_drive"),
                hardwareMap.dcMotor.get("right_drive"));
        Lift lift = new Lift(hardwareMap.dcMotor.get("lift"), hardwareMap.servo.get("gate"));

        // Servos

        ClimberDepositor climber = new ClimberDepositor(hardwareMap.servo.get("climber_depositor"));


        waitForStart();

        while (opModeIsActive()) {
            motors.drive(gamepad1);
            lift.runLift(gamepad1);

            telemetry.addData("Drive Motors: ", motors.getStatus());
            telemetry.addData("Lift:", lift.getStatus());

            waitForNextHardwareCycle();

        }
    }
}
