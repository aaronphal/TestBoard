package org.usfirst.frc.team537.robot.subsystems;

import org.usfirst.frc.team537.robot.Robot;
import org.usfirst.frc.team537.robot.RobotMap;
import org.usfirst.frc.team537.robot.commands.CommandSwerve;


import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class SwerveModule extends Subsystem {
		
	public TalonSRX rotate = new TalonSRX(RobotMap.Board.swervePort);
	public TalonSRX speed = new TalonSRX(RobotMap.Board.speedPort);
		
		
		
	//double pidAngle = PID(p, i, d);
	public class PID{
		private double p, i, d;
			//public static final PID DRIVE_ANGLE_FRONT_RIGHT = new PID(3.8, 0.0, 4.0);
		public PID(double p, double i, double d) {
			this.p = p;
			this.i = i;
			this.d = d;
		}
		
		public double getP() {
			return p;
		}
		
		public double getI() {
			return i;
		}
		
		public double getD() {
			return d;
		}
	}
	public double currentAngle;
		
	private int gyro = 0;
		
	public SwerveModule() {
		rotate.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Absolute, 0, 0);
		rotate.config_kP(RobotMap.kPIDLoopIdx, 5.0, RobotMap.kTimeoutMs);
        rotate.config_kI(RobotMap.kPIDLoopIdx, 0.0, RobotMap.kTimeoutMs); 
        rotate.config_kD(RobotMap.kPIDLoopIdx, 20.0, RobotMap.kTimeoutMs);
	}
		
		
	public void setTarget(double gyroAngle, double rcw, double str, double fwd) {
		
		/*
		double temp = fwd * Math.cos(0) + str * Math.sin(0);
		str = -fwd * Math.sin(0) + str * Math.cos(0);
		fwd = temp;
		*/
		
		
		double fwd2  = (fwd * Math.cos(gyro)) + str * Math.sin(gyro);
		double str2 = (-fwd * Math.sin(gyro)) + str * Math.cos(gyro);
		
		
		double L = RobotMap.Board.Length;
		double W = RobotMap.Board.Width;
		double R = Math.sqrt((L * L) + (W * W));
		
		double A = str - rcw * (L / R); //rear
		double B = str + rcw * (L / R); //front
		double C = fwd - rcw * (W / R); //right
		double D = fwd + rcw * (W / R); //Left
		
		/*
		double A = str2 - rcw * (L / R); //rear
		double B = str2 + rcw * (L / R); //front
		double C = fwd2 - rcw * (W / R); //right
		double D = fwd2 + rcw * (W / R); //Left
		*/
		
		double frontLeftSpeed = Math.sqrt((B * B) * (D * D)); //ws2
		double frontRightSpeed = Math.sqrt((B * B) * (C * C)); //ws1
		double rearLeftSpeed = Math.sqrt((A * A) * (D * D)); //ws3
		double rearRightSpeed = Math.sqrt((A * A) * (C * C)); //ws4
		
		double frontLeftAngle = Math.atan2(B, D) * 180 / Math.PI;
		double frontRightAngle = Math.atan2(B, C) * 180 / Math.PI;
		double rearLeftAngle = Math.atan2(A, D) * 180 / Math.PI;
		double rearRightAngle = Math.atan2(A, C) * 180 / Math.PI;
		
		double realAngle = (frontRightAngle * 4096)/360;
		double max = frontRightSpeed;
		
		
		if (frontLeftSpeed > max) {
			max = frontLeftSpeed;
		}
		
		if (rearLeftSpeed > max) {
			max = rearLeftSpeed;
		}
		
		if (rearRightSpeed > max) {
			max = rearRightSpeed;
		}
		
		
		if (max > 1.0) {
			frontLeftSpeed /= max;
			frontRightSpeed /= max;
			rearLeftSpeed /= max;
			rearRightSpeed /= max;
		}
		
		currentAngle = rotate.getSelectedSensorPosition(0);
		SmartDashboard.putNumber("Angle", currentAngle);
		
		rotate.set(ControlMode.Position, realAngle);
		speed.set(ControlMode.PercentOutput, frontLeftSpeed);
		
		}
	
	/*
	public void test(double test) {
		rotate.set(ControlMode.PercentOutput, test);
	}*/
	
	public void disable() {
		speed.set(ControlMode.PercentOutput, 0.0);
		rotate.set(ControlMode.PercentOutput, 0.0);
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	
    	
    	//SmartDashboard.putNumber("Module", currentAngle);
    	
    	setDefaultCommand(new CommandSwerve());
    }
}

