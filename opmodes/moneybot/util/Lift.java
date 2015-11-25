package com.qualcomm.ftcrobotcontroller.opmodes.moneybot.util;


import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.Servo;

public class Lift
{

    private DcMotor lift_motor;
    private Servo gate;

    private final double NORMAL_POWER = 0.75;

    private final double DOWN_POSITION = 0;
    private final double UP_POSITION = 0.5;

    private String status;


    // 1 = full on
    // 0 = off
    // -1 = backwards



    private int liftStatus = 0;
    private boolean dpad_cooldown = false;
    private boolean y_cooldown = false;

    public Lift(DcMotor lift_motor, Servo gate)
    {
        this.lift_motor = lift_motor;
        this.gate = gate;

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

        lift_motor.setPower(liftStatus * NORMAL_POWER);

        if (gamepad.y && !y_cooldown)
        {
            if (gate.getPosition() == UP_POSITION)
                gate.setPosition(DOWN_POSITION);
            else
                gate.setPosition(UP_POSITION);
            y_cooldown = true;

        } else if (!gamepad.y && y_cooldown)
            y_cooldown = false;

        status = String.format("Power: %s Gate: %s", liftStatus, gate.getPosition());

    }

    public void runLift(int power)
    {
        lift_motor.setPower(power * NORMAL_POWER);

    }

    public String getStatus()
    {
        return status;
    }
}
