package org.usfirst.frc.team2554.robot.subsystems;
import org.usfirst.frc.team2554.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.SpeedControllerGroup;

/**
 *
 */
public class Claw extends Subsystem {

   public Victor leftMotor = new Victor(RobotMap.claw[0]);
    Victor rightMotor = new Victor(RobotMap.claw[1]);
    
    Victor winch = new Victor(RobotMap.winch);
    Encoder winchTracker = new Encoder(RobotMap.encoderWinch[0], RobotMap.encoderWinch[1]);
    
    public double uprightToFlatDistance;
    public double distancePulse;
   

    public void initDefaultCommand() {
    	
    	winchTracker.setDistancePerPulse(distancePulse);
    	winchTracker.setMaxPeriod(0.1);
     
    }
    public Claw() {
    		
    }
   
    public void setSpeed(double speed)
    {
    	leftMotor.set(speed); 
    	rightMotor.set(speed);
    }
    
    public void stop()
    {
    	leftMotor.set(0);
    	rightMotor.set(0);
    }
    
    public double distance()
    {
    	return winchTracker.getDistance();
    }
    
    public void reset()
    {
    	winchTracker.reset();
    }
 
    public void log()
    {
    	SmartDashboard.putNumber("Claw Speed", leftMotor.get());
    }
}

