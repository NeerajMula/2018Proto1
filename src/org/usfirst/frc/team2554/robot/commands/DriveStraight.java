package org.usfirst.frc.team2554.robot.commands;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.PIDCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team2554.robot.Robot;

/**
 *
 */
public class DriveStraight extends PIDCommand {
	
	
	double distance;
	double currentAngle;
	double straightKp;
	double correction;
	double speed = 0;
	
	 double p = 0;
	 double i = 0;
	 double d = 0;
	
	int timeCheck = 0;
	Timer timer = new Timer();
	boolean timerStatus = false;
	double timeOnTarget = 0.2;
	
	PIDController SpeedPID = getPIDController();
	public DriveStraight(int dist) {
		super(0,0,0);
		distance = dist;
		SpeedPID.setPID(p, i, d);
    	SpeedPID.setOutputRange(-0.8, 0.8);
    	SpeedPID.setAbsoluteTolerance(1.0f);
		SpeedPID.setSetpoint(distance);
	
	}
	
	public DriveStraight(double dist, double p, double i, double d)
	{
		super(0,0,0);
		distance = dist;
		this.p = p; 
		this.i = i;
		this.d = d;
		SpeedPID.setPID(p, i, d);
    	SpeedPID.setOutputRange(-0.8, 0.8);
    	SpeedPID.setAbsoluteTolerance(1.0f);
		SpeedPID.setSetpoint(distance);
	}

	protected void initialize() {

	}

	@Override
	protected double returnPIDInput() {
		return Robot.driveTrain.getDistance();
	}

	@Override
	protected void usePIDOutput(double speed) {

    	currentAngle = Robot.driveTrain.getGyroAngle();
    	correction = currentAngle*straightKp ;
    	double rightSpeed = speed + correction;
    	double leftSpeed = speed - correction;
    	
    	
        Robot.driveTrain.myDrive.tankDrive(leftSpeed, rightSpeed);
	}

	@Override
	protected boolean isFinished() {
	   	if(SpeedPID.onTarget() && timeCheck<1)
    	{
    		timeCheck = 1;
    		timer.start();
    		timerStatus = true;
    	}
    	
    	if(!SpeedPID.onTarget() && timeCheck>0)
    	{
    		timer.stop();
    		timer.reset();
    		timerStatus = false;
    		timeCheck = 0;
    	}
    		
        return timerStatus && timer.get()>timeOnTarget;
	}
	

	protected void end(){
	SpeedPID.disable();
	Robot.driveTrain.stop();
	}
}