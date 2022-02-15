package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.botSetup.RoboSetup;

/**
 * This is a sample autonomous program for a meccanum
 *  drive style chassis with four motors it uses only
 *  time to run version 1. Version 2 (this program) will
 *  use two distance sensors to move off of the alliance
 *  wall and then move toward the audience wall.
 *
 *  Robot will start in the right side position on the Blue
 *   Alliance. It will drive forward then move toward
 *   the carousel to turn it, delivering the duck. Finally,
 *   it will park in the Storage Unit.
 *
 * @author DNelson
 * @version 1/06/2022 v1.0 update 01/07/2022
 */
@Autonomous(name = "NelsonBot: Auto Blue Side Carousel by Sensor", group = "NelsonBot")
//@Disabled

public class AutoBasicBluCarouselSensor extends LinearOpMode {
    //Add instance of the RoboSetup
    RoboSetup myMec = new RoboSetup();

    //Declare OpMode Members
    private ElapsedTime runtime = new ElapsedTime();

    //Variable to keep track of distance
    private double currentDist = 0.0;

    //Constants
    static final double FORWARD_SPEED = 0.2;
    static final double TURN_SPEED = 0.3;

    @Override
    public void runOpMode(){
        /*
            Initialize the drive system variables.
            THe init() method of the hardware map does
            all of the work here.
         */
        myMec.init(hardwareMap);

        //Send telemetry data about status
        telemetry.addData("Status","Ready to Run!");
        telemetry.addData("Dist Right (CM): ", myMec.getCurrentDistanceRight(DistanceUnit.CM));
        telemetry.addData("Dist Right (IN): ", myMec.getCurrentDistanceRight(DistanceUnit.INCH));
        telemetry.addData("Dist Back (CM): ", myMec.getCurrentDistanceBack(DistanceUnit.CM));
        telemetry.addData("Dist Back (IN): ", myMec.getCurrentDistanceBack(DistanceUnit.INCH));
        telemetry.update();



        //Wait for the game to start (driver presses PLAY)
        waitForStart();

        //Step through each leg of the path, ensuring that
        //the auto mode has not run incorrectly.

        // Step 1:  Drive forward until the robot is 9.5
        // Call the setForward method and pass it the FORWARD_SPEED constant.
        runtime.reset();//resets the time
        // distance from the audience side wall
        currentDist = myMec.getCurrentDistanceBack(DistanceUnit.INCH);
        while(currentDist < 9.5){
            myMec.setForward(FORWARD_SPEED);
            currentDist = myMec.getCurrentDistanceBack(DistanceUnit.INCH);
            telemetry.addData("Distance (IN)", myMec.getCurrentDistanceBack(DistanceUnit.INCH));
            telemetry.update();
        }

        // Step 2:  Stop for 0.5 seconds
        // Call the setForward method and pass it 0.0 to stop the motors
        myMec.setForward(0.0);
        runtime.reset();
        while (opModeIsActive() && (runtime.seconds() < 0.5)) {
            telemetry.addData("", "Leg 2: %2.5f S Elapsed",
                    runtime.seconds());
            telemetry.update();

        }

        //Alternative Sensor Driven movement
        //Step 3: Slide to the right until the robot is 11.5
        // distance from the audience side wall
        currentDist = myMec.getCurrentDistanceRight(DistanceUnit.INCH);
        while(currentDist > 11.5){
            myMec.setSlideRight(FORWARD_SPEED/1.8);
            currentDist = myMec.getCurrentDistanceRight(DistanceUnit.INCH);
            telemetry.addData("Distance (IN)", myMec.getCurrentDistanceRight(DistanceUnit.INCH));
            telemetry.update();
        }


        // Step 4:  Stop for 0.5 seconds
        myMec.setForward(0.0);
        myMec.setSlideLeft(0.0);
        runtime.reset();
        while (opModeIsActive() && (runtime.seconds() < 0.5)) {
            telemetry.addData("Path", "Leg 4: %2.5f S Elapsed",
                    runtime.seconds());
            telemetry.update();

        }

        // Step 5:  Drive Backwards for a tiny amount to ensure
        // contact with carousel
//        myMec.setForward(-FORWARD_SPEED/2);//Negative constant will move backward
//        runtime.reset();
//        while (opModeIsActive() && (runtime.seconds() < 0.8)) {
//            telemetry.addData("Path", "Leg 5: %2.5f S Elapsed",
//                    runtime.seconds());
//            telemetry.update();
//        }

        //Step 5: Drive Backwards to be 14 inches from the wall
        currentDist = myMec.getCurrentDistanceBack(DistanceUnit.INCH);
        while(currentDist > 14){
            myMec.setForward(-FORWARD_SPEED/2);
            currentDist = myMec.getCurrentDistanceBack(DistanceUnit.INCH);
        }

        //Step 6:  Stop for 0.5 seconds
        // Stop both motors to make sure nothing unintended is moving
        myMec.setForward(0.0);
        myMec.setSlideLeft(0.0);
        runtime.reset();
        while (opModeIsActive() && (runtime.seconds() < 0.5)) {
            telemetry.addData("Path", "Leg 6: %2.5f S Elapsed", runtime.seconds());
            telemetry.update();

        }


        //Step 7: Turn on the Servo for 8 seconds
        // this should be enough time to deliver the
        // duck via the carousel
        myMec.setServoMovement(2);
        runtime.reset();
        while(opModeIsActive() && (runtime.seconds() < 8.0)){
            telemetry.addData("Deliver Duck: ", "Servo Leg: %2.5f s Elapsed",
                    runtime.seconds());
            telemetry.update();
        }

        //Step 8: Turn off the Servo
        myMec.setServoMovement(0);
        runtime.reset();
        while(opModeIsActive() && (runtime.seconds() < 1.0)){
            telemetry.addData("Step: ", "Stop Servo");
            telemetry.update();
        }

        // Step 11 Enter Storage Unit:  Drive forward about 28.5 inches
        // Call the setForward method and pass it the FORWARD_SPEED constant.

        //Move slightly off the wall
        currentDist = myMec.getCurrentDistanceRight(DistanceUnit.INCH);
        while(currentDist < 12){
//            myMec.setForward(-FORWARD_SPEED/2);
            myMec.setMecanumDrive(0, -FORWARD_SPEED/2, 0);
            currentDist = myMec.getCurrentDistanceRight(DistanceUnit.INCH);
            telemetry.addData("Distance (IN)", myMec.getCurrentDistanceRight(DistanceUnit.INCH));
            telemetry.update();
        }

        //Drive Forward into the Storage unit 28.5 from wall
        currentDist = myMec.getCurrentDistanceBack(DistanceUnit.INCH);
        while(currentDist < 30){
            myMec.setForward(FORWARD_SPEED);
            currentDist = myMec.getCurrentDistanceBack(DistanceUnit.INCH);
            telemetry.addData("Distance (IN)", myMec.getCurrentDistanceBack(DistanceUnit.INCH));
            telemetry.update();
        }

        // Step Stop:  Stop for 0.5 seconds
        // Call the setForward method and pass it 0.0 to stop the motors
        myMec.setForward(0.0);
        runtime.reset();
        while (opModeIsActive() && (runtime.seconds() < 0.5)) {
            telemetry.addData("Path", "Leg 2: %2.5f S Elapsed",
                    runtime.seconds());
            telemetry.update();

        }

        // Step 8:  ALl Stop
        myMec.setTurnRight(0.0);
        myMec.setForward(0.0);
        myMec.setSlideLeft(0.0);
        myMec.setServoMovement(0.0);
        telemetry.addData("Path", "Complete");
        telemetry.update();
        sleep(1000);

        //End of the Path

    }//End of Op Mode
}
