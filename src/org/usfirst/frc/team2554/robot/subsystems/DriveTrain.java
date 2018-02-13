package org.usfirst.frc.team2554.robot.subsystems;


import org.usfirst.frc.team2554.robot.*;
import org.usfirst.frc.team2554.robot.commands.TankDrive;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */

public class DriveTrain extends Subsystem {


	public DriveTrain()
	{
		double distancePerPulse = (6.0 * Math.PI) / 128;

		encoderRight.setDistancePerPulse(distancePerPulse);
		encoderLeft.setDistancePerPulse(distancePerPulse);
		encoderRight.setMaxPeriod(.1);
		encoderLeft.setMaxPeriod(.1);
	}

	Victor frontLeft = new Victor(RobotMap.driveTrain[0]);
	Victor backLeft = new Victor(RobotMap.driveTrain[1]);

	Victor frontRight = new Victor(RobotMap.driveTrain[2]);
	Victor backRight = new Victor(RobotMap.driveTrain[3]);

	public SpeedControllerGroup left = new SpeedControllerGroup(frontLeft, backLeft);
	public SpeedControllerGroup right = new SpeedControllerGroup(frontRight, backRight);




	public Encoder encoderRight = new Encoder(RobotMap.encoderRight[0], RobotMap.encoderRight[1]);
	public Encoder encoderLeft = new Encoder(RobotMap.encoderLeft[0],RobotMap.encoderLeft[1]);



	public DifferentialDrive myDrive = new DifferentialDrive(left,right);

	public ADXRS450_Gyro gyro = new ADXRS450_Gyro();
	Timer timer; 
	double currentHeading;
	double headingError;
	double correction;
	double KP=0.01;


	public void initDefaultCommand() {
		setDefaultCommand(new TankDrive());
	}

	public void teleopDrive(double leftVal, double rightVal)
	{
		myDrive.tankDrive(leftVal, rightVal);
	}

	public void calibrateGyro()
	{
		gyro.calibrate();
	}

	public void resetGyro()
	{
		gyro.reset();
	}

	public double getGyroAngle()
	{
		return gyro.getAngle();
	}


	public double getDistance()
	{
		return ((encoderLeft.getDistance()+encoderRight.getDistance())/2);
	}

	public void resetDistance()
	{
		encoderLeft.reset();
		encoderRight.reset();
	}

	public void log()
	{
		SmartDashboard.putNumber("Angle", getGyroAngle());
		SmartDashboard.putNumber("Distance", getDistance());
		SmartDashboard.putNumber("Left Side Power", left.get());
		SmartDashboard.putNumber("Right Side Power", right.get());
	}
}