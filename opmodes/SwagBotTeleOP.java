package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.Range;

/**
 * This class is an old version of the TeleOP. We no longer use it or support it.
 */

public class SwagBotTeleOP extends OpMode {

    public DcMotor R, L, Lift;
    public double lThrottle, rThrottle;

    public int liftstatus = 0;

    public boolean reversedControls = false;
    public boolean xcooldown, dpad_upcooldown, dpad_downcooldown = false;

    public final double LIFT_POWER = 0.75;

    public SwagBotTeleOP() {}

    @Override
    public void init() {
        R = hardwareMap.dcMotor.get("R");
        L = hardwareMap.dcMotor.get("L");
        L.setDirection(DcMotor.Direction.REVERSE);

        Lift = hardwareMap.dcMotor.get("Lift");
    }

    @Override
    public void loop() {

        if (gamepad1.x && !xcooldown) {
            reversedControls = !reversedControls;
            xcooldown = true;
        } else if (!gamepad1.x) {
            xcooldown = false;
        }

        if (reversedControls) {
            rThrottle = -gamepad1.right_stick_y;
            lThrottle = -gamepad1.left_stick_y;
        } else {
            rThrottle = gamepad1.right_stick_y;
            lThrottle = gamepad1.left_stick_y;
        }

        R.setPower(rThrottle);
        L.setPower(lThrottle);

        // Up
        if (gamepad1.dpad_up && !dpad_upcooldown) {
            liftstatus++;
            dpad_upcooldown = true;
        } else if (!gamepad1.dpad_up)
            dpad_upcooldown = false;

        // Down
        if (gamepad1.dpad_down && !dpad_downcooldown) {
            liftstatus--;
            dpad_downcooldown = true;
        } else if (!gamepad1.dpad_down)
            dpad_downcooldown = false;

        Range.clip(liftstatus, -1, 1);

        Lift.setPower(liftstatus * LIFT_POWER);

        telemetry.addData("Text", "*** Robot Data ***");
        telemetry.addData("left tgt pwr", "left  pwr: " + String.format("%.2f", lThrottle));
        telemetry.addData("right tgt pwr", "right pwr: " + String.format("%.2f", rThrottle));
        telemetry.addData("controls reversed", String.valueOf(reversedControls));
        telemetry.addData("lift status: ", Integer.toString(liftstatus));

    }

    public double throttleChange(double joystick) {
        double r;
        if (Math.abs(joystick) > 0.75) {
            r = 0.75;

            if (joystick < 0) {
                r = -r;
            }
            return r;
        } else
            return joystick;
    }


    /*
    public double scaleThrottle(double d) {
        double[] valueList = { 0.0, 0.05, 0.09, 0.10, 0.12, 0.15, 0.18, 0.24, 0.30, 0.36, 0.43,
                0.50, 0.60, 0.72, 0.85, 1.00, 1.00 };
        int valueIndex = (int) d * 16;

        if (valueIndex < 0)
            valueIndex = -valueIndex;

        if (valueIndex > 16)
            valueIndex = 16;

        if (d < 0)
            return -valueList[valueIndex];
        else
            return valueList[valueIndex];
    */

}
