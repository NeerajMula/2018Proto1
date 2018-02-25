package org.usfirst.frc.team2554.robot.commands;

import org.usfirst.frc.team2554.robot.Robot;
import org.usfirst.frc.team2554.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */

         //0: HOME
	// 1: SWITCH
	// 2: SCALE
	// 3: CLIMB
public class MoveElevator extends Command {

	private int goal;
	private int currentLocation;
	private double speedUp = 0.5;
	private double speedDown = -0.05;
	private double speed=0.11;
    public MoveElevator(int goal) {
        requires(Robot.elevator);
       this.goal = goal;
       currentLocation = Robot.elevator.currentLocation();
    }

    protected void initialize() {
    	//if(currentLocation<0)
    		//cancel();
    	
    	System.out.println("Current Location: " + currentLocation);
    	if(goal > currentLocation)
    		speed = speedUp;
    	
    	
    	else
    		speed = speedDown;
    }

    protected void execute() {
    	
    	
    	System.out.println("Move Elevator Running");
    	
    	System.out.println(speed);
		Robot.elevator.move(speed);
   	
    }

    protected boolean isFinished() {
    	return Robot.elevator.getLimit(goal);
    }

    protected void end() {
    	Robot.elevator.stall();
    }

    protected void interrupted() {
    	Robot.elevator.stall();
    }
}
