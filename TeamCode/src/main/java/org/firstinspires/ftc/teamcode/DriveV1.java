package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

//name and class

@TeleOp(name = "DriveV2", group="Linear Opmode")


public class DriveV1 extends OpMode {

    //Define Motors
    DcMotor M0;
    DcMotor M1;
    DcMotor M2;
    DcMotor M3;
    DcMotor M0_2;

    //drive loop
    public void MoveDriveTrain(){
        //drive variables
        double yAxis;
        double xAxis;
        double Rotate;

        //input to change variables
        yAxis = gamepad1.left_stick_y;
        xAxis = gamepad1.left_stick_x;
        Rotate = -gamepad1.left_trigger+gamepad1.right_trigger;

        //apply variables to motor
        M0.setPower(Rotate + (-yAxis + xAxis));
        M1.setPower(Rotate + (+yAxis + xAxis));
        M2.setPower(Rotate + (yAxis - xAxis));
        M3.setPower(Rotate + (-yAxis - xAxis));

    }

    //init sequence
    @Override
    public void init() {
        //Add Motors
        M0 = hardwareMap.get(DcMotor.class,"M0");
        M1 = hardwareMap.get(DcMotor.class,"M1");
        M2 = hardwareMap.get(DcMotor.class,"M2");
        M3 = hardwareMap.get(DcMotor.class,"M3");
        M0_2 = hardwareMap.get(DcMotor.class,"M0_2")

        //Set Motors
        M0.setDirection(DcMotor.Direction.FORWARD);
        M0.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        M1.setDirection(DcMotor.Direction.FORWARD);
        M1.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        M2.setDirection(DcMotor.Direction.FORWARD);
        M2.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        M3.setDirection(DcMotor.Direction.FORWARD);
        M3.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        M0_2.setDirection(DcMotor.Direction.FORWARD);
        M0_2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        M0_2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    //loop while init
    @Override
    public void init_loop() {

    }

    //runs once after start is pressed
    @Override
    public void start(){

    }

    //looping program after start
    @Override
    public void loop() {
        MoveDriveTrain();

    }

}
