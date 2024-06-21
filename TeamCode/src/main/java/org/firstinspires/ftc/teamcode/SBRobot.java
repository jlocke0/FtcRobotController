package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class SBRobot {

    private DcMotor frontLeft;
    private DcMotor frontRight;
    private DcMotor backLeft;
    private DcMotor backRight;

    //For Autonomous
    private final double WHEEL_DIA = 9.6;
    private final double ROBOT_DIA = 45.3;
    private final double ROBOT_CIR = ROBOT_DIA * Math.PI;
    private final double TICS_PER_REV= 537.7;
    private final double DISTANCE_PER_REV = WHEEL_DIA * Math.PI;
    private final double TICS_PER_CM = TICS_PER_REV / DISTANCE_PER_REV;
    private final double TICS_PER_ROT = TICS_PER_CM * ROBOT_CIR;
    private final double TICS_PER_DEGREE = TICS_PER_ROT / 360;

    public SBRobot(HardwareMap hardwareMap){
        frontLeft = hardwareMap.dcMotor.get("fl");
        frontRight = hardwareMap.dcMotor.get("fr");

        backLeft = hardwareMap.dcMotor.get("bl");
        backRight = hardwareMap.dcMotor.get("br");

        //Needs to be reversed in these sets
        frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        backRight.setDirection(DcMotorSimple.Direction.FORWARD);

        frontRight.setDirection(DcMotorSimple.Direction.FORWARD);
        backLeft.setDirection(DcMotorSimple.Direction.REVERSE);

        frontLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        frontRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        backLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        backRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }

    public void setSpeedForEachMotor(double frSpeed, double flSpeed, double brSpeed, double blSpeed){
        frontLeft.setPower(flSpeed);
        backLeft.setPower(blSpeed);
        backRight.setPower(brSpeed);
        frontRight.setPower(frSpeed);

    }

    //This is for Autonomous
    public void driveForward(double cm, double power){
        int targetTics = (int) (cm * TICS_PER_CM);
        setEachSideTarget(targetTics, targetTics);
        setSpeedForEachMotor(power,power,power,power);
    }

    public void driveBack(double cm, double power){
        int targetTics = -(int) (cm * TICS_PER_CM);
        setEachSideTarget(targetTics, targetTics);
        setSpeedForEachMotor(power,power,power,power);
    }
    public void setEachSideTarget(int leftTarget, int rightTarget){
        frontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        frontLeft.setTargetPosition(leftTarget);
        backLeft.setTargetPosition(leftTarget);
        frontRight.setTargetPosition(rightTarget);
        backRight.setTargetPosition(rightTarget);

        frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }
    public void turn(double angle, double power){
        int targetTics = (int) (angle * TICS_PER_DEGREE);
        setEachSideTarget(targetTics, -targetTics);
        setSpeedForEachMotor(power,power,power,power);
    }
}
