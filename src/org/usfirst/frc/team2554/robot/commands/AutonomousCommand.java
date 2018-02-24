package org.usfirst.frc.team2554.robot.commands;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutonomousCommand extends CommandGroup {
	int ELEVATOR_INITIAL_POSITION = 1; //Scoring Position

    public AutonomousCommand() {
    		
    		final String scaleSide = DriverStation.getInstance().getGameSpecificMessage(); //FMS on which side the scale is one
		int direction = 0;
    		
		while(scaleSide.length() == 3)
		{
    		if (scaleSide.charAt(0) == 'L')
    		{
    			direction = -1; //Left 
    		}
    		else
    		{
    			direction = 1; //Right 
    		}
		
    		int positionRobotDashboard = 0;//to read position
    		switch (positionRobotDashboard) 
    		{
    			case 0://Robot starts on the right
    			{	
    				addSequential(new MoveElevator(1));			
            		if(direction == 1) //If the scale is on the right
            		{
            			addSequential(new DistanceDrive(150));
            			addSequential(new RotateToAngle(-90));
            			addSequential(new DistanceDrive(41));
            		}
            		else //If the scale is on the left
            		{
            			addSequential(new DistanceDrive(209));
            			addSequential(new RotateToAngle(-90));
            			addSequential(new DistanceDrive(264));
            			addSequential(new RotateToAngle(-90));
            			addSequential(new DistanceDrive(61));
            			addSequential(new RotateToAngle(-90));
            			addSequential(new DistanceDrive(41));
            		}
    				break;
    			}
    			case 1://Middle
    			{
    				addSequential(new MoveElevator(1));
            		addSequential(new DistanceDrive(60));
            		addSequential(new RotateToAngle(90*direction));
            		addSequential(new DistanceDrive(132));
            		addSequential(new RotateToAngle(-90*direction));
            		addSequential(new DistanceDrive(69));
            		addSequential(new RotateToAngle(-90*direction));
            		addSequential(new DistanceDrive(41));
    				break;
    			}
    			case 2://Left
    			{
    				addSequential(new MoveElevator(1));			
            		if(direction == 1) //If the scale is on the right
            		{
            			addSequential(new DistanceDrive(209));
            			addSequential(new RotateToAngle(90));
            			addSequential(new DistanceDrive(264));
            			addSequential(new RotateToAngle(90));
            			addSequential(new DistanceDrive(61));
            			addSequential(new RotateToAngle(90));
            			addSequential(new DistanceDrive(41));
            		}
            		else //If the scale is on the left
            		{
            			addSequential(new DistanceDrive(150));
            			addSequential(new RotateToAngle(90));
            			addSequential(new DistanceDrive(41));
            		}
    				break;
    			}
    		}
		}
    		
    		//Code to put cube one scale and end operations
    		

    		
    		
    		
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.

        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.

        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
    }
}
