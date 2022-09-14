package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.Range;

@TeleOp(name="V1", group="Linear Opmode")

public class OldCode extends LinearOpMode {

    // Declare OpMode members.
    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor M0 = null;
    private DcMotor M1 = null;
    private DcMotor M2 = null;
    private DcMotor M3 = null;
    private DcMotor M1_2 = null;


    static final double     COUNTS_PER_MOTOR_REV    = 288;
    static final double     GEAR_REDUCTION    = 2.7778;
    static final double     COUNTS_PER_GEAR_REV    = COUNTS_PER_MOTOR_REV * GEAR_REDUCTION;
    static final double     COUNTS_PER_DEGREE    = COUNTS_PER_GEAR_REV/360;

    @Override
    public void runOpMode() {
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        // Initialize the hardware variables. Note that the strings used here as parameters
        // to 'get' must correspond to the names assigned during the robot configuration
        // step (using the FTC Robot Controller app on the phone).
        M0  = hardwareMap.get(DcMotor.class, "M0");
        M1 = hardwareMap.get(DcMotor.class, "M1");
        M2  = hardwareMap.get(DcMotor.class, "M2");
        M3 = hardwareMap.get(DcMotor.class, "M3");
        M1_2 = hardwareMap.get(DcMotor.class, "M1_2");

        M0.setDirection(DcMotor.Direction.FORWARD);
        M0.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        M1.setDirection(DcMotor.Direction.FORWARD);
        M1.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        M2.setDirection(DcMotor.Direction.FORWARD);
        M2.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        M3.setDirection(DcMotor.Direction.FORWARD);
        M3.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        M1_2.setDirection(DcMotor.Direction.FORWARD);
        M1_2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        M1_2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        // Send telemetry message to indicate successful Encoder reset
        telemetry.addData("reset",  "Starting at %7d",
                M1_2.getCurrentPosition());



        telemetry.update();
        // Wait for the game to start (driver presses PLAY)
        waitForStart();
        runtime.reset();

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {

            // Setup a variable for each drive wheel to save power level for telemetry
            double leftPower;
            double rightPower;
            int newLeftTarget;

            // Choose to drive using either Tank Mode, or POV Mode
            // Comment out the method that's not used.  The default below is POV.

            // POV Mode uses left stick to go forward, and right stick to turn.
            // - This uses basic math to combine motions and is easier to drive straight.
            double drive = gamepad1.left_stick_y;
            double strafe  =  gamepad1.left_stick_x;
            double turn = -gamepad1.right_trigger/2.5+gamepad1.left_trigger/2.5;

            leftPower    = Range.clip(-drive + turn, -.75, .75);
            rightPower   = Range.clip(drive + turn, -.75, .75);



            // Tank Mode uses one stick to control each wheel.
            // - This requires no math, but it is hard to drive forward slowly and keep straight.
            // leftPower  = -gamepad1.left_stick_y ;
            // rightPower = -gamepad1.right_stick_y ;

            // Send calculated power to wheels
            M0.setPower(Range.clip(rightPower+strafe,-1,1));
            M1.setPower(Range.clip(leftPower+strafe,-1,1));
            M2.setPower(Range.clip(leftPower-strafe,-1,1));
            M3.setPower(Range.clip(rightPower-strafe,-1,1));

            //Lock slide motor when not moving
            M1_2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

            /*if (X) {
               telemetry.addData("Sslide", "Is Not Pressed");
            }

            else {
                telemetry.addData("Sslide", "Is Pressed");
            }

            if (Sclaw.getState() == false) {
                telemetry.addData("Sclaw", "Is Not Pressed");
            } else {
                telemetry.addData("Sclaw", "Is Pressed");
            }

            if (dpaddown) {
                telemetry.addData("dpaddown", "true");
            } else {
                telemetry.addData("dpaddown", "false");
            }

            if (rightbumper) {
                telemetry.addData("rightbumper", "true");
            } else {
                telemetry.addData("rightbumper", "false");
            }

            if (leftbumper) {
                telemetry.addData("leftbumper", "true");
            } else {
                telemetry.addData("leftbumper", "false");
            }*/

            if (gamepad2.left_stick_y<-0.1) {
                M1_2.setPower(1);}
            else if (gamepad2.left_stick_y>0.1){
                M1_2.setPower(-1);}

            telemetry.update();

        }
    }
    void myFunction(){
        telemetry.addData("Sslide-2", "Is Pressed");
    }
}
