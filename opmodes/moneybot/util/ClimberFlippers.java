package com.qualcomm.ftcrobotcontroller.opmodes.moneybot.util;

import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by dchotzen-hartzell19 on 12/3/15.
 */
public class ClimberFlippers {

    private Servo left_flipper;
    private Servo right_flipper;

    private boolean leftToggleCooldown = false;
    private boolean rightToggleCooldown = false;

    // false = up, true = down
    private boolean leftFlipperStatus = false;
    private boolean rightFlipperStatus = false;

    private double DOWN_POSITION = 0.5;
    private double UP_POSITION = 0;

    public ClimberFlippers(Servo left_flipper, Servo right_flipper)
    {
        this.left_flipper = left_flipper;
        this.right_flipper = right_flipper;

    }

    public void updateFlippers(Gamepad gamepad)
    {
        if (gamepad.left_bumper && !leftToggleCooldown)
        {
            if (leftFlipperStatus)
                left_flipper.setPosition(DOWN_POSITION);
            else
                left_flipper.setPosition(UP_POSITION);
            leftToggleCooldown = true;
            leftFlipperStatus = !leftFlipperStatus;

        } else if (!gamepad.left_bumper && leftToggleCooldown)
            leftToggleCooldown = false;


        if (gamepad.right_bumper && !leftToggleCooldown)
        {
            if (rightFlipperStatus)
                right_flipper.setPosition(DOWN_POSITION);
            else
                right_flipper.setPosition(UP_POSITION);
            rightToggleCooldown = true;
            rightFlipperStatus = !rightFlipperStatus;

        } else if (!gamepad.right_bumper && rightToggleCooldown)
            rightToggleCooldown = false;
    }
}
