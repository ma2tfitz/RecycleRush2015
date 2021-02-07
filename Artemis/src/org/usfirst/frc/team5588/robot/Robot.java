
package org.usfirst.frc.team5588.robot;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


public class Robot extends IterativeRobot {
	
	    /**
	     * This function is run when the robot is first started up and should be
	     * used for any initialization code.
	     */
		RobotDrive myRobot;
		Joystick stick;
		XboxController xBox;
		Timer count;
		Talon pulleyMotor;
		Compressor compressor;
		DoubleSolenoid armSol, breakSol;
		Command autoCommand;	
		SendableChooser autoChooser;
		
	    public void robotInit() {
	    	myRobot = new RobotDrive(0, 1, 2, 3);
	    	stick = new Joystick(0);
	    	xBox= new XboxController(1);
	    	count = new Timer();
	    	pulleyMotor = new Talon(4);
	    	armSol = new DoubleSolenoid(0,1,0);
	    	breakSol = new DoubleSolenoid(0,2,3);
	    	
	    	
	    	compressor = new Compressor(1); //create a compressor with the default slots and relay and pressure switch channel 1.
	    	compressor.start();

	        myRobot.setInvertedMotor(RobotDrive.MotorType.kFrontLeft, true);
	    	myRobot.setInvertedMotor(RobotDrive.MotorType.kFrontRight, true);
	    	myRobot.setInvertedMotor(RobotDrive.MotorType.kRearRight, true);
	    	myRobot.setInvertedMotor(RobotDrive.MotorType.kRearLeft, true);
	    	
	    	/*autoChooser= new SendableChooser();
	    	autoChooser.addDefault("No bump, backwards, bring container", autonomousA());
	    	autoChooser.addObject("No bump, forwards, bring container", autonomousB());
	    	autoChooser.addObject("Bump, forwards, bring container", autonomousC());
	    	autoChooser.addObject("Bump, backwards, bring container", autonomousD());
	    	SmartDashboard.putData("Autonomous mode chooser", autoChooser);*/
	    	
	    	}
//Modularizes our autonomous capabilities, i.e. makes it pretty
	    public void moveArmsVertical(double time, double speed){
	    	count.reset();
	    	count.start();
	    	speed *= -1;// Positive speed up, negative speed value down
	    	breakSol.set(Value.kReverse);
	    	while(!count.hasPeriodPassed(time))
	    	{
	    		pulleyMotor.set(speed);
	    		
	    	}
	    	pulleyMotor.set(0.0);
	    	breakSol.set(Value.kForward);
	    }
	    
	    public void openCloseArms(boolean open){
	    	if(open){
	    		armSol.set(Value.kReverse);
	    	}
	    	else{
	    		armSol.set(Value.kForward);
	    	}
	    }
	    
	    public void driveStraight(double time, double speed){
	    	count.reset();
	    	count.start();
	    	speed*=-1; // negative speed backwards, positive speed forward
	    	while(!count.hasPeriodPassed(time)){
	    		myRobot.arcadeDrive(speed, 0.0);
	    	}
	    }
	    
	    public void driveTurn(double time, double rotationVal){
	    	count.reset();
	    	count.start();
	    	while(!count.hasPeriodPassed(time)){
	    		myRobot.arcadeDrive(0.1,rotationVal);
	    	}
	    }
	    
	    public void autonomousInit()
	    {
	    	//autoCommand= (Command) autoChooser.getSelected();
	    	//autoCommand.start();
	    	//autonomousA();	//Without bump, facing backward, bring container
	    	//autonomousB();	//Without bump, facing forward, bring container
	    	//autonomousC();	//With bump, facing forward, bring container
	    	//autonomousD();	//With bump, facing backward, bring container
	    	//autonomousE();	//No bump, starting IN LANDFILL, bring tote backwards
	    	
	     
	    }
	
	   /* public void autonomousPeriodic(){
	    	Scheduler.getInstance().run();
	    }*/
	    
	  //Without bump, facing backward, bring container
	    public Object autonomousA()
	    {
	    	//openCloseArms(false);
	    	moveArmsVertical(1.0,0.5);
	    	driveStraight(5.3,-0.5);
	    	return new Object();
	    }
	    
	    // Without bump, facing forward, bring container
	    public Object autonomousB()
	    {
	    	//openCloseArms(false);
	    	moveArmsVertical(2.0,0.5);
	    	driveStraight(5.5,0.5);
	    	return new Object();

	    }
	    
	    
	    //With bump, facing forward, bring container
	    public Object autonomousC()
	    {
	    	//Close arms
	    	//openCloseArms(false);
	    	
	    	//Move up
	    	moveArmsVertical(3.0,.5);
	    	
	    	count.reset();
	    	count.start();
	    	
	    	while(!count.hasPeriodPassed(2.25))
	    		myRobot.arcadeDrive(-0.5,0.225);
	    	
	    	// Drive to middle of auto zone
	    	driveStraight(4.5,.5);
	    	return new Object();

	    }

	    //With bump, facing backward, bring container
	   public Object autonomousD()
	   {
	   	//Close arms
		   
	   	//openCloseArms(false);
	   	
	   	//Move up
	   	moveArmsVertical(3.0,.5);
	   	
	   	count.reset();
	   	count.start();
	   	
	   	while(!count.hasPeriodPassed(2.25))
	   		myRobot.arcadeDrive(0.5,0.15);
	   	// Drive to middle of auto zone
	   	driveStraight(4.5,-.5);
	   	return new Object();

	   }
	   
	   public Object autonomousE(){
		  // openCloseArms(false);
	    	moveArmsVertical(1.0,0.5);
	    	driveStraight(1.5,-0.5);
	    	return new Object();
	   }
	    	
	   public void teleopInit()
	   {
		   pulleyMotor.set(0.0);
    	   breakSol.set(Value.kForward);   
	   }

	    /**
	     * This function is called periodically during operator control
	     */
	    public void teleopPeriodic() {
	    	
	    if(isEnabled() && isOperatorControl())	{	    	     		
	    		myRobot.arcadeDrive(stick, true);
    	 
	    		if(xBox.getY()>.2 || xBox.getY()<-0.2){
	    			breakSol.set(Value.kReverse);
	    			pulleyMotor.set(Math.pow(xBox.getY(), 3));
	    		}
	    
	 else
	 	{   
	     	pulleyMotor.set(0.0);
	     	breakSol.set(Value.kForward);
	 	}
	    }

	    }
}
	 /*if(xBox.getAButton())
	 	{
		 
	   		armSol.set(Value.kReverse);  		  
	   	}
	   	   
	 if(xBox.getBButton())
	 	   {   
				armSol.set(Value.kForward); 
	 	   }

	    		    	
	    }*/
	    



