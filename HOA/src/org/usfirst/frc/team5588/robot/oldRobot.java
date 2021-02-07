
package org.usfirst.frc.team5588.robot; 
//import java.util.ArrayList;

 

//import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Timer;

/**

* The VM is configured to automatically run this class, and to call the
* functions corresponding to each mode, as described in the IterativeRobot
* documentation. If you change the name of this class or the package after
* creating this project, you must also update the manifest file in the resource
* directory.
*/ 
/**
* This function is run when the robot is first started up and should be
* used for any initialization code.
*/

public class oldRobot extends IterativeRobot {
	
	RobotDrive myRobot; 
	Joystick stick;
	//Gyro gyro;

/**
* This function is run when the robot is first started up and should be
* used for any initialization code.
*/
	public oldRobot()
	{
		super();
		robotInit();
	}
	
 
public void robotInit() {
	System.out.print("Im in robotInit");
	myRobot = new RobotDrive(0, 1, 12, 13);
	stick = new Joystick(0);
	//gyro = new Gyro(1);
	
} 

public void autonomous()
{ 
	while (isAutonomous() && isEnabled())
	{
		double angle = gyro.getAngle();
		myRobot.arcadeDrive(-1.0,-angle * Kp);
		Timer.delay(0.01);
	}
	
 
public void operatorControl()

{
	System.out.print("Im in operatorControl");
	while (isOperatorControl() ) // loop until change
	{
	 myRobot.arcadeDrive(stick); // drive with joysticks
	 //if(stick.getTrigger()){
	 System.out.print("openClose");//openClose();
	// }
	 Timer.delay(0.01);
	}
}

}
