package org.usfirst.frc.team537.robot.commands;

import org.usfirst.frc.team537.robot.Robot;
import org.usfirst.frc.team537.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class CommandSwerve extends Command {

    public CommandSwerve() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.swerve);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.swerve.disable();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.swerve.setTarget(0, Robot.oi.thicc.getRawAxis(RobotMap.Axis.rcw), Robot.oi.thicc.getRawAxis(RobotMap.Axis.str), Robot.oi.thicc.getRawAxis(RobotMap.Axis.fwd));
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.swerve.disable();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.swerve.disable();
    }
}
