package org.usfirst.frc.team2554.robot.commands;

import org.usfirst.frc.team2554.robot.Robot;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class DistanceDriveAlternatePID extends Command {

    double currentAngle;
    double angleError;
    double correctionPower; 
    double Kp = 0.03;
    double distance;

   
    public DistanceDriveAlternatePID(double dist) {
    	
        requires(Robot.driveTrain);
        distance = dist;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.driveTrain.resetDriveTrain();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        currentAngle = Robot.driveTrain.getGyroAngle();
        correctionPower = currentAngle * Kp;
        
        double steeringSpeedRight = 0.5 + correctionPower;
        double steeringSpeedLeft =  0.5 - correctionPower;
        	
        Robot.driveTrain.myDrive.tankDrive(steeringSpeedLeft, steeringSpeedRight);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        if(Robot.driveTrain.getDistance()>=distance)
        	return true;
        	
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.driveTrain.resetDriveTrain();
        Robot.driveTrain.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.driveTrain.resetDriveTrain();
        Robot.driveTrain.stop();

        
    }
    
 
   
}