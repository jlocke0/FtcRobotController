package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp
//@Disabled
public class SBTeleOp extends OpMode {
    public SBRobot robot;
    double SpeedFactor = .7;

    @Override
    public void init() {
        robot = new SBRobot(hardwareMap);
    }

    @Override
    public void loop() {
        if (Math.abs(gamepad1.left_stick_y) > 0.1 || Math.abs(gamepad1.left_stick_x) > 0.1 || Math.abs(gamepad1.right_stick_y) > 0.1 || Math.abs(gamepad1.right_stick_x) > 0.1 ){

            double r = Math.hypot(-gamepad1.left_stick_x, gamepad1.left_stick_y);
            double robotAngle = Math.atan2(gamepad1.left_stick_y, -gamepad1.left_stick_x) - Math.PI / 4;

            double rightX = -gamepad1.right_stick_x;
            // added by DavidDa
            double rightY = gamepad1.right_stick_y;

            final double vFL = r * Math.cos(robotAngle) + rightX + rightY;
            final double vFR = r * Math.sin(robotAngle) - rightX + rightY;
            final double vBL = r * Math.sin(robotAngle) + rightX + rightY;
            final double vBR = r * Math.cos(robotAngle) - rightX + rightY;

            robot.setSpeedForEachMotor(SpeedFactor*vFR,SpeedFactor*vFL,SpeedFactor*vBR,SpeedFactor*vBL);
        }else {
            robot.setSpeedForEachMotor(0,0,0,0);
        }

        if (gamepad1.a){
            SpeedFactor = .4;
        }else if (gamepad1.x){
            SpeedFactor = .7;
        }
    }
}
