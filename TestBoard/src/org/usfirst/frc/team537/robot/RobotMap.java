/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team537.robot;

import org.usfirst.frc.team537.robot.subsystems.SwerveModule.PID;
import org.usfirst.frc.team537.robot.subsystems.SwerveModule;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	
	public static final int kPIDLoopIdx = 0;
	public static final int kTimeoutMs = 10;
	//public static final PID main = new PID(4.2, 0.0, 4.4);
	
	public class Board {
		public static final int swervePort = 5;
		public static final int speedPort = 4;
		public static final int Compressor = 2;
		
		public static final int Length = 22;
		public static final int Width =  20;
	}
	
	public class Buttons {
		public static final int compressorActive = 2;
	}
	
	public class Axis{
		public static final int fwd = 1;
		public static final int str = 0;
		public static final int rcw = 2;
	}
	
	public class Compressor {
		public static final int Solinoid = 1;
		
	}
	
	}
}
