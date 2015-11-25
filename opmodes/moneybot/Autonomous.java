package com.qualcomm.ftcrobotcontroller.opmodes.moneybot;

import com.qualcomm.ftcrobotcontroller.opmodes.moneybot.util.ClimberDepositor;
import com.qualcomm.ftcrobotcontroller.opmodes.moneybot.util.Lift;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.ftcrobotcontroller.opmodes.moneybot.util.DriveMotors;


public class Autonomous extends LinearOpMode {

    @Override
    public void runOpMode()
            throws InterruptedException
    {
        DriveMotors motors = new DriveMotors(hardwareMap.dcMotor.get("left_drive"),
                                             hardwareMap.dcMotor.get("right_drive"));
        Lift lift = new Lift(hardwareMap.dcMotor.get("lift"),
                             hardwareMap.servo.get("servo"));
        ClimberDepositor climbers = new ClimberDepositor(hardwareMap.servo.get("climbers"));

        waitForStart();

        lift.runLift(-1);
        
        // WARNING: VALUES DON'T ACTUALLY MEAN ANYTHING!

        motors.drive(30, 0, 0.8);
        waitForNextHardwareCycle();
        while (motors.isBusy())
        {
            waitForNextHardwareCycle();
        }

        motors.drive(30, 1, 0.8);
        waitForNextHardwareCycle();
        while (!motors.isBusy())
        {
            waitForNextHardwareCycle();
        }
    }
}
