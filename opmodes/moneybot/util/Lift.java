package com.qualcomm.ftcrobotcontroller.opmodes.moneybot.util;


import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;

public class Lift
{

    private DcMotor lift_motor;

    // 1 = full on
    // 0 = off
    // -1 = backwards
    private int liftStatus = 0;
    private boolean dpad_cooldown = false;

    public Lift(DcMotor lift_motor)
    {
        this.lift_motor = lift_motor;
    }

    public void runLift(Gamepad gamepad) {
        if (!dpad_cooldown) {
            if (gamepad.dpad_up)
                liftStatus++;
            else if (gamepad.dpad_down)
                liftStatus--;

            dpad_cooldown = true;
        } else if (!gamepad.dpad_up && !gamepad.dpad_down)
        {
            dpad_cooldown = false;
        }

        lift_motor.setPower(liftStatus * 0.75);
    }

    public void runLift(double power)
    {
        lift_motor.setPower(power * 0.75);
    }
}
