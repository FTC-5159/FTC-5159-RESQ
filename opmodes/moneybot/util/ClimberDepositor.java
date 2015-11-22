package com.qualcomm.ftcrobotcontroller.opmodes.moneybot.util;

import com.qualcomm.robotcore.hardware.Servo;

/**
 * ...
 */
public class ClimberDepositor {

    private Servo climber_motor;

    private double DOWN_POSITION = 0;
    private double UP_POSITION = 1;

    public ClimberDepositor(Servo climber_motor)
    {
        this.climber_motor = climber_motor;
    }

    public void setPosition(int pos) // 0 = down, 1 = up
    {
        switch(pos)
        {
            case 0:
                climber_motor.setPosition(DOWN_POSITION);
            case 1:
                climber_motor.setPosition(UP_POSITION);
        }
    }
}
