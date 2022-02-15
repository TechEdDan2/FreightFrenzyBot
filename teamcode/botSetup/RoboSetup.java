package org.firstinspires.ftc.teamcode.botSetup;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

/**
 * This is the code for setting up the robot which consists of
 *  a small mecanum chassis. This will be a hardcoded version
 *  of mecanum drive. The class will review other version using
 *  mathematical equations to achieve more elegant coding.
 *
 *  Hardware Setup
 *  MOTORS
 *  Expansion Hub
 *  Port 0 - backLeft
 *  Port 1 - frontLeft
 *  Port 2 - frontRight
 *  Port 3 - backRight
 *
 *  SERVOS
 *  Expansion
 *  Port 0 - carousel
 *
 *  SENSORS
 *  I2C Expansion
 *  Port 0 - right
 *  Port 1 - back
 *
 * Control Hub
 * Port 0 - liftMotor
 * Port 3 - intakeMotor
 *
 * @author DNel2
 * @version 11/27
 *
 */
public class RoboSetup {
    //Variables for Mechanisms
    //Motors
    private DcMotor backLeft;
    private DcMotor backRight;
    private DcMotor frontLeft;
    private DcMotor frontRight;
    private DcMotor liftMotor;
    private DcMotor intakeMotor;
    //Servos
    private CRServo carousel;
    //private Servo carousel2;

    //Sensors
    private DistanceSensor myDistanceSensorRight;
    private DistanceSensor myDistanceSensorBack;

    //Counter Numbers
    private double ticksPerRotation;

    //Add the param for init with HardwareMap
    public void init(HardwareMap hwMap){
        //setting up the motors
        //Left Back Wheel Motor
        backLeft = hwMap.get(DcMotor.class, "backLeft");
        backLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        //All left will be reversed for Math version of Mec Drive
        backLeft.setDirection(DcMotorSimple.Direction.REVERSE);

        //Left Front Wheel Motor
        frontLeft = hwMap.get(DcMotor.class, "frontLeft");
        frontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        //All left will be reversed for Math version of Mec Drive
        frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);

        //Right Back Wheel Motor
        backRight = hwMap.get(DcMotor.class, "backRight");
        backRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        //All Right will be reversed ... never
        //backRight.setDirection(DcMotorSimple.Direction.REVERSE);

        //Right Front Wheel Motor
        frontRight = hwMap.get(DcMotor.class, "frontRight");
        frontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        //All Right will be reversed ... never
        //frontRight.setDirection(DcMotorSimple.Direction.REVERSE);

        //liftMotor setup
        liftMotor = hwMap.get(DcMotor.class, "liftMotor");
        liftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        liftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        //intakeMotor setup
        intakeMotor = hwMap.get(DcMotor.class, "intakeMotor");
        intakeMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        intakeMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);



        //Setting up the servos
        carousel = hwMap.get(CRServo.class, "carousel");
        carousel.resetDeviceConfigurationForOpMode();

        //Setting up the Sensors
        myDistanceSensorRight = hwMap.get(DistanceSensor.class, "distSensor");
        myDistanceSensorBack = hwMap.get(DistanceSensor.class, "distSensorBack");

    }//End of InIt

    //Methods

    ///////////////////////////////////////////////////
    //                                               //
    // Hardcoded Mecanum Methods - Not Best Practice //
    //                                               //
    ///////////////////////////////////////////////////
    /**
     * This method will move the robot forward
     * @param power - double value to set the power of the movement
     */
    public void setForward(double power){
        backLeft.setPower(power);
        frontLeft.setPower(power);
        backRight.setPower(power);
        frontRight.setPower(power);
    }

    /**
     * This method will move the robot backward
     * @param power - double value to set the power of the movement
     */
     public void setBackward(double power){
        backLeft.setPower(power);
        frontLeft.setPower(power);
        backRight.setPower(power);
        frontRight.setPower(power);
     }

    /**
     * This method will move the robot to slide to the left
     * @param power - double value to set the power of the movement
     */
     public void setSlideLeft(double power){
        backLeft.setPower(power);
        frontLeft.setPower(-power);
        backRight.setPower(-power);
        frontRight.setPower(power);
     }

     /**
     * This method will move the robot to slide to the Right
     * @param power - double value to set the power of the movement
     */
     public void setSlideRight(double power){
        backLeft.setPower(-power);
        frontLeft.setPower(power);
        backRight.setPower(power);
        frontRight.setPower(-power);
     }

     /**
     * This method will move the robot to turn left
     * @param power - double value to set the power of the movement
     */
     public void setTurnLeft(double power){
        backLeft.setPower(-power);
        frontLeft.setPower(-power);
        backRight.setPower(power);
        frontRight.setPower(power);
     }

    /**
     * This method will move the robot to turn left
     * @param power - double value to set the power of the movement
     */
     public void setTurnRight(double power){
        backLeft.setPower(power);
        frontLeft.setPower(power);
        backRight.setPower(-power);
        frontRight.setPower(-power);
     }

    /**
     * This method will move the robot diagonally forward to the left
     * @param power - double value to set the power of the movement
     */
    public void setDiagLtF(double power){
        backLeft.setPower(power);
        frontLeft.setPower(0);
        backRight.setPower(0);
        frontRight.setPower(power);
    }

    /**
     * This method will move the robot diagonally backward to the left
     * @param power - double value to set the power of the movement
     */
    public void setDiagLtB(double power){
        backLeft.setPower(0);
        frontLeft.setPower(-power);
        backRight.setPower(-power);
        frontRight.setPower(0);
    }

    /**
     * This method will move the robot diagonally forward to the right
     * @param power - double value to set the power of the movement
     */
    public void setDiagRtF(double power){
        backLeft.setPower(0);
        frontLeft.setPower(power);
        backRight.setPower(power);
        frontRight.setPower(0);
    }

    /**
     * This method will move the robot diagonally backward to the left
     * @param power - double value to set the power of the movement
     */
    public void setDiagRtB(double power){
        backLeft.setPower(-power);
        frontLeft.setPower(0);
        backRight.setPower(0);
        frontRight.setPower(-power);
    }


    //////////////////////////////////////////////////////////
    //                                                      //
    //   CLEANER VERSION OF THE PROGRAM FOR MECANUM         //
    //                                                      //
    //////////////////////////////////////////////////////////

    /**
     * This method will set all the values for the motors
     *  to drive the mecanum chassis. This method is inspired
     *  by code from gmZero programming tutorial
     *
     *  The left stick will control linear movement:
     *   forward, back, slide left, slide right, and
     *   diagonal movement.
     *  The right stick will control the rotational movement:
     *   spin left, spin right.
     *
     * @param y - the left stick y (double) value
     * @param x - the left stick x (double) value
     * @param rx - the right stick x (double) value
     */
     public void setMecanumDrive(double y, double x, double rx){
         double denominator = Math.max(Math.abs(y) + Math.abs(x)+Math.abs(rx), 1);
         double frontLeftPower = (y + x + rx) / denominator;
         double backLeftPower = (y - x + rx) / denominator;
         double frontRightPower = (y - x - rx) / denominator;
         double backRightPower = (y + x - rx) /denominator;

         frontLeft.setPower(frontLeftPower);
         backLeft.setPower(backLeftPower);
         frontRight.setPower(frontRightPower);
         backRight.setPower(backRightPower);
     }

    //////////////////////////////////////////////
    //                                          //
    //      Motor Driven Components Methods     //
    //                                          //
    /////////////////////////////////////////////
    public void setLiftPower(double currentPower){
         liftMotor.setPower(currentPower);
    }

//    public void setLiftPowerDown(double currentPower){
//         liftMotor.setPower(currentPower);
//    }

    public void setIntakeIN(){
         intakeMotor.setPower(0.8);
    }

    public void setIntakeOut(){
         intakeMotor.setPower(-0.8);
    }

    //////////////////////////////////////////////
    //                                          //
    //      Servo Methods                      //
    //                                          //
    /////////////////////////////////////////////


    /**
     * This method will help control the servo
     *
     * @param power - this vaiable will set the power / speed
     *              of the servo.
     */
     public void setServoMovement(double power){

         carousel.setPower(power);
     }

    //////////////////////////////////////////////
    //                                          //
    //      Sensor Methods                      //
    //                                          //
    /////////////////////////////////////////////


    /**
     * This method will return the sensor reading
     * of the distance sensor
     *
     * @return Double value for the distance read from the sensor
     *
     */
    public double getCurrentDistanceRight(DistanceUnit du){
        return myDistanceSensorRight.getDistance(du);
    }

    /**
     * This method will return the sensor reading
     * of the distance sensor that is located at the back
     * of the robot
     *
     * @return Double value for the distance read from the sensor
     *
     */
    public double getCurrentDistanceBack(DistanceUnit du){
        return myDistanceSensorBack.getDistance(du);
    }
}//End of class
