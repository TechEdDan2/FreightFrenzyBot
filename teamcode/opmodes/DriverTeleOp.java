package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.botSetup.RoboSetup;

/**
 * This is a driver program for a small mecanum chassis
 *
 * @author DNel2
 * @version 11/28/2021 v1.0
 */
@TeleOp(name = "Basic Mecanum Drive: Iterative Opmode", group = "Iterative Opmode")
//@Disabled
public class DriverTeleOp extends OpMode {
    //Create an instance of the RoboSetup
    RoboSetup myMecanumBot = new RoboSetup();

    @Override
    public void init(){
        myMecanumBot.init(hardwareMap);
        telemetry.addData("Welcome to Team NelsonBot", "Program Initialized");
        telemetry.update();

    }

    @Override
    public void loop() {
        //Variables
        double motorSpeedX = 0.0;
        double motorSpeedY = 0.0;
        double motorSpeedRx = 0.0;

        telemetry.addData("Dist Right (CM): ", myMecanumBot.getCurrentDistanceRight(DistanceUnit.CM));
        telemetry.addData("Dist Right (IN): ", myMecanumBot.getCurrentDistanceRight(DistanceUnit.INCH));
        telemetry.addData("Dist Back (CM): ", myMecanumBot.getCurrentDistanceBack(DistanceUnit.CM));
        telemetry.addData("Dist Back (IN): ", myMecanumBot.getCurrentDistanceBack(DistanceUnit.INCH));
        telemetry.update();


        if (gamepad1.left_stick_y > 0.2 || gamepad1.left_stick_y < -0.2 ||
                gamepad1.left_stick_x > 0.2 || gamepad1.left_stick_x < -0.2) {
            //Make the y value negative because forward on the game controller
            // creates negative values
            motorSpeedY = -gamepad1.left_stick_y;
            motorSpeedX = gamepad1.left_stick_x;
            motorSpeedRx = gamepad1.right_stick_x;
            myMecanumBot.setMecanumDrive(motorSpeedY, motorSpeedX, motorSpeedRx);
            telemetry.addData("Gamepad Driving:", gamepad1.left_stick_y);
            telemetry.update();
        } else if (gamepad1.right_stick_x > 0.2 || gamepad1.right_stick_x < -0.2) {
            motorSpeedY = -gamepad1.left_stick_y;
            motorSpeedX = gamepad1.left_stick_x;
            motorSpeedRx = gamepad1.right_stick_x;
            myMecanumBot.setMecanumDrive(motorSpeedY, motorSpeedX, motorSpeedRx);
            telemetry.addData("Gamepad Right Stick X:", gamepad1.right_stick_x);
            telemetry.update();
        } else {
            myMecanumBot.setForward(0.0);
            myMecanumBot.setDiagLtB(0.0);
            myMecanumBot.setDiagLtF(0.0);
            myMecanumBot.setSlideLeft(0.0);
            myMecanumBot.setSlideRight(0.0);
        }

        //Carousel Control
        if (gamepad1.a) {
            myMecanumBot.setServoMovement(-0.75);
            telemetry.addData("Servo Moving", "Yes");
            telemetry.update();
        } else if (gamepad1.x){
            myMecanumBot.setServoMovement(0.75);
            telemetry.addData("Servo Moving", "Yes");
            telemetry.update();
        }else{
            myMecanumBot.setServoMovement(0);
        }

        //Lift Control
        if(gamepad1.left_bumper) {
            myMecanumBot.setLiftPower(0.8);
            telemetry.addData("Lift Moving: ", "Yes");
            telemetry.update();
        } else if(gamepad1.right_bumper){
            myMecanumBot.setLiftPower(-0.8);
            telemetry.addData("Lift Moving: ", "Yes");
            telemetry.update();
        }else{
            myMecanumBot.setLiftPower(0.0);
        }

        //Intake Control

        //This is old code will drive the robot around
//        if(gamepad1.left_stick_y > 0.2 || gamepad1.left_stick_y < -0.2){
//            motorSpeed = gamepad1.left_stick_y;
//            myMecanumBot.setForward(-motorSpeed);
//            telemetry.addData("Gamepad Stick Y:", gamepad1.left_stick_y);
//        }else if(gamepad1.left_stick_x > 0.2 || gamepad1.left_stick_x < -0.2) {
//            motorSpeed = gamepad1.left_stick_x;
//            myMecanumBot.setSlideLeft(-motorSpeed);
//            telemetry.addData("Gamepad Stick X:", gamepad1.left_stick_y);
//        }else if(gamepad1.right_stick_x > 0.2 || gamepad1.right_stick_x < -0.2) {
//            motorSpeed = gamepad1.right_stick_x;
//            myMecanumBot.setTurnLeft(-motorSpeed);
//            telemetry.addData("Gamepad Stick X:", gamepad1.right_stick_x);
//        }else{
//            myMecanumBot.setForward(0.0);
//            myMecanumBot.setDiagLtB(0.0);
//            myMecanumBot.setDiagLtF(0.0);
//            myMecanumBot.setSlideLeft(0.0);
//            myMecanumBot.setSlideRight(0.0);
//        }


    }

}
