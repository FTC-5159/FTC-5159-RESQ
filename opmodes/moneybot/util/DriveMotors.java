package com.qualcomm.ftcrobotcontroller.opmodes.moneybot.util;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.Gamepad;

/**
 * This class manages the drive motors
 */
public class DriveMotors {

    private DcMotor left_drive, right_drive;

    private String status;

    private boolean reversedControls = false;
    private boolean reverseCooldown = false;

    private boolean arcadeDrive = false;
    private boolean arcadeCooldown = false;


    final static int ENCODER_CPR = 1440;
    final static double WHEEL_DIAMETER = 2.5;
    static double WHEEL_CIRCUMFERENCE = WHEEL_DIAMETER * Math.PI;

    public DriveMotors(DcMotor left_drive, DcMotor right_drive)
    {
        this.left_drive = left_drive;
        this.right_drive = right_drive;

        left_drive.setDirection(DcMotor.Direction.REVERSE);

        status = "initialized";
    }

    public void drive(double inches, int turn, double power)
    {
        if (!isBusy())
        {
            if (!left_drive.getMode().equals(DcMotorController.RunMode.RUN_TO_POSITION))
                left_drive.setMode(DcMotorController.RunMode.RUN_TO_POSITION);
            if (!right_drive.getMode().equals(DcMotorController.RunMode.RUN_TO_POSITION))
                right_drive.setMode(DcMotorController.RunMode.RUN_TO_POSITION);
            double rotations = (inches / WHEEL_CIRCUMFERENCE);
            int counts = (int) Math.round(rotations * ENCODER_CPR);

            left_drive.setTargetPosition(counts);
            right_drive.setTargetPosition(counts);

            if (turn != 0)
            {
                left_drive.setPower(power * turn);
                right_drive.setPower(power * (-turn));
            } else
            {
                left_drive.setPower(power);
                right_drive.setPower(power);
            }



            status = "Driving for " + inches +
                    " inches with turn" + turn +
                    " and power " + power;
        }
    }

    public void drive(Gamepad gamepad)
    {
        if (gamepad.x && !reverseCooldown) {
            reversedControls = !reversedControls;
            reverseCooldown = true;
        } else if (!gamepad.x && reverseCooldown)
            reverseCooldown = false;

        if (gamepad.a && !arcadeCooldown) {
            arcadeDrive = !arcadeDrive;
            arcadeCooldown = true;
        } else if (!gamepad.a && arcadeCooldown)
            arcadeCooldown = false;

        if (arcadeDrive)
        {
            // left stick is the throttle, right stick is the direction
            double throttle = gamepad.left_stick_y,
                    turnRate = gamepad.right_stick_x;

            if (reversedControls) {
                throttle = -throttle;
                turnRate = -turnRate;
            }

            left_drive.setPower(throttle-turnRate);
            right_drive.setPower(throttle+turnRate);

            status = String.format("Driving [arcade] with power %s and turnRate %s", throttle, turnRate);

        } else // tank drive
        {

            double rThrottle = gamepad.right_stick_y,
                    lThrottle = gamepad.left_stick_y;

            if (reversedControls) {
                rThrottle = -rThrottle;
                lThrottle = -lThrottle;
            }

            left_drive.setPower(lThrottle);
            right_drive.setPower(rThrottle);

            status = String.format("Driving [tank] with power l: %s r: %s", lThrottle, rThrottle);

        }
    }

    public boolean isBusy()
    {
        return (left_drive.isBusy() || right_drive.isBusy());
    }

    public String getStatus()
    {
        return status;
    }
}
