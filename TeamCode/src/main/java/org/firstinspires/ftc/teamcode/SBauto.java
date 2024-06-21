package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

public class SBauto extends LinearOpMode {
    SBRobot robot;
    @Override
    public void runOpMode() throws InterruptedException {
        robot = new SBRobot(hardwareMap);
        waitForStart();
        for (int i = 0; i<4;i++){
            robot.driveForward(100,.5);
            robot.turn (90, .5);
        }
        for (int i = 0; i<4;i++){
            robot.driveBack(100,.5);
            robot.turn (-90, .5);
        }
    }
}
