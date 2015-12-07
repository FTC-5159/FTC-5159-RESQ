package com.qualcomm.ftcrobotcontroller.opmodes.moneybot.util;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.LightSensor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.TouchSensor;

/**
 * Created by dchotzen-hartzell19 on 12/1/15.
 */

public class LightSensorDataTest extends OpMode
{
    private LightSensor lightSensor;
    private TouchSensor touchSensor;


    public void init()
    {
        lightSensor = hardwareMap.lightSensor.get("light_sensor");
        touchSensor = hardwareMap.touchSensor.get("touch_sensor");

        lightSensor.enableLed(true);
    }

    public void loop()
    {
        telemetry.addData("Light: ", lightSensor.getLightDetected());
        telemetry.addData("Touch: ", touchSensor.isPressed());
    }
}
