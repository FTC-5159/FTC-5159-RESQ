package com.qualcomm.ftcrobotcontroller.opmodes.moneybot.util;


import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

public class Lift
{

    private DcMotor lift_motor;
    private Servo gate;

    private final double NORMAL_POWER = 0.75;

    private final double DOWN_POSITION = 1;
    private final double UP_POSITION = 0.5;


    // false = down, true = up
    private boolean gateStatus = false;

    private String status;

    private int liftStatus = 0;
    private boolean dpad_cooldown = false;
    private boolean y_cooldown = false;

    public Lift(DcMotor lift_motor, Servo gate)
    {
        this.lift_motor = lift_motor;
        this.gate = gate;

        //gate.setPosition(DOWN_POSITION);

    }

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

        lift_motor.setPower(Range.clip(liftStatus * NORMAL_POWER, -1, 1));

        if (gamepad.y && !y_cooldown)
        {
            if (gateStatus)
                gate.setPosition(DOWN_POSITION);
            else
                gate.setPosition(UP_POSITION);
            y_cooldown = true;
            gateStatus = !gateStatus;

        } else if (!gamepad.y && y_cooldown)
            y_cooldown = false;

        status = String.format("Power: %s Gate: %s", liftStatus, gate.getPosition());

    }

    public void runLift(int power)
    {
        lift_motor.setPower(Range.clip(power * NORMAL_POWER, -1, 1));

    }

    public String getStatus()
    {
        return status;
    }
}
