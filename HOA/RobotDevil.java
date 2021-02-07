
package org.usfirst.frc.team5588.robot;

//import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class RobotDevil extends IterativeRobot {
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
	Encoder encoderR, encoderL;
	DigitalInput limitSwitch;
	
	
    public void robotInit() {
    	myRobot = new RobotDrive(0, 1, 2, 3);
    	stick = new Joystick(0);
    	xBox= new XboxController(1);
    	encoderR = new Encoder(2, 3);
    	encoderL = new Encoder (0, 1);
    	count = new Timer();
    	pulleyMotor = new Talon(4);
    	armSol = new DoubleSolenoid(0,1,0);
    	breakSol = new DoubleSolenoid(0,2,3);
    	limitSwitch = new DigitalInput(4);
    	
    	compressor = new Compressor(1); //create a compressor with the default slots and relay and pressure switch channel 1.
    	compressor.start();
    	
    	encoderL.setDistancePerPulse(0.001);
        encoderL.getDistance();
        encoderR.setDistancePerPulse(0.001);
        encoderR.getDistance();
        myRobot.setInvertedMotor(RobotDrive.MotorType.kFrontLeft, true);
    	myRobot.setInvertedMotor(RobotDrive.MotorType.kFrontRight, true);
    	myRobot.setInvertedMotor(RobotDrive.MotorType.kRearRight, true);
    	myRobot.setInvertedMotor(RobotDrive.MotorType.kRearLeft, true);
    	
    	}
    	

    



    /**
     * This function is called periodically during autonomous
     */
   
    
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
    
    public void endAuto(double timeOfAuto){
    	count.reset();
    	count.start();
    	while(!count.hasPeriodPassed(timeOfAuto)){
    		myRobot.arcadeDrive(0.0,0.0);
    	}
    }
    public void autonomousInit()
    {
    	
    	count.reset();
  	   count.start();
  	   
     	// if in station 1
     	//this.autonomousA();
     	// if in station 2
     	//autonomousB(); //Use to get into auto zone without bump
     	// if in station 3
     	//this.autonomousC();
     	//testEncoder();
     	//testBreak();
  	  // autonomousD();
  	  // autonomousE();
  	  //autonomousF();//Use to get into auto zone with bump
  	   //autonomousG();
  	  //autonomousH;//Go nowhere, just lift bin
  	// autonomousF2();
  	 autonomousB2();
     
    }
    /*public void autonomousPeriodic()
    {
    	count.reset();
 	   count.start();
 	   
    	// if in station 1
    	//this.autonomousA();
    	// if in station 2
    	//autonomousB(); //Use to get into auto zone without bump
    	// if in station 3
    	//this.autonomousC();
    	//testEncoder();
    	//testBreak();
 	  // autonomousD();
 	  // autonomousE();
 	  //autonomousF();//Use to get into auto zone with bump
 	   //autonomousG();
 	  //autonomousH;//Go nowhere, just lift bin
 	 //autonomousF2();
 	autonomousB2();
    }*/
    
   public void autonomousA(){
    	//TODO pick up tote
		//180 degree turm
		while (!count.hasPeriodPassed(4.3))
			myRobot.arcadeDrive(0.1,0.4);
		
		while (!count.hasPeriodPassed(8.0))
			myRobot.arcadeDrive(-0.25,0.0);
		
			
		while(!count.hasPeriodPassed(10.0))
			myRobot.arcadeDrive(0.0,0.0);
}
    //picks up green tote and brings it to the autozone
    
    public void testBreak(){

    	breakSol.set(Value.kForward);
    	while(!count.hasPeriodPassed(20.0))
			myRobot.arcadeDrive(0.0,0.0);
    	breakSol.set(Value.kReverse);
    }
    
    public void testEncoder(){
    	encoderR.reset();
    	encoderL.reset();
    	while(isAutonomous()&& isEnabled()){
    		
    		SmartDashboard.putNumber("Distance Left", encoderL.getDistance());
    		SmartDashboard.putNumber("Distance Right", encoderR.getDistance());
    		SmartDashboard.putNumber("testnum",1.0);
    		//myRobot.arcadeDrive(-0.25,0.0);
    }
    }
    public void autonomousB2()
    {
    	openCloseArms(false);
    	moveArmsVertical(1.0,0.5);
    	driveStraight(5.3,-0.5);
    	//endAuto(7.7);
    	//myRobot.arcadeDrive(0.0,0.0);
    }
    
    // Starting in the station without bump
    public void autonomousB()
    {
    	openCloseArms(false);
    	moveArmsVertical(2.0,0.5);
    	driveStraight(5.5,0.5);
    	//myRobot.arcadeDrive(0.0,0.0);
    	endAuto(6.5);
    	/*
    	//close arms
    	armSol.set(Value.kForward);
    	
    	// pick up
    	count.reset();
  	   count.start();
    	
    	while(!count.hasPeriodPassed(3.0))
    	{
    		pulleyMotor.set(-.4);
    		
    	}
    	pulleyMotor.set(0.0);
    	breakSol.set(Value.kForward);
    	
    	//driveforward 18ft11inches
    	while(!count.hasPeriodPassed(8.5))
    		myRobot.arcadeDrive(-.5, 0.0);
    	
    	breakSol.set(Value.kReverse);
    	while(!count.hasPeriodPassed(11.0))
    	{
    		
    		pulleyMotor.set(0.1);
    	}
    	pulleyMotor.set(0.0);
    	breakSol.set(Value.kForward);
    	
    	
    	//stop until 20 sec
    	while(!count.hasPeriodPassed(20.0))
    	{
    		myRobot.arcadeDrive(0.0, 0.0);
    	}
    	*/
    
    }
    
   
    
    //bump
    public void autonomousD()
    {
    	
    	//close arms
    	armSol.set(Value.kForward);
    	
    	// pick up
    	count.reset();
  	   count.start();
    	
    	while(!count.hasPeriodPassed(3.0))
    	{
    		pulleyMotor.set(-.4);
    		
    	}
    	pulleyMotor.set(0.0);
    	breakSol.set(Value.kForward);
    	
    	//driveforward 18ft11inches
    	while(!count.hasPeriodPassed(8.75))
    		myRobot.arcadeDrive(-.5, 0.0);
    	
    	breakSol.set(Value.kReverse);
    	while(!count.hasPeriodPassed(11.25))
    	{
    		
    		pulleyMotor.set(0.1);
    	}
    	pulleyMotor.set(0.0);
    	breakSol.set(Value.kForward);
    	
    	
    	//stop until 20 sec
    	while(!count.hasPeriodPassed(30.0))
    	{
    		myRobot.arcadeDrive(0.0, 0.0);
    	}
    
    }
    
    //Testing modularization of code (possibly same as D)
    //you want to use this for going over bump
    public void autonomousF()
    {
    	//Close arms
    	openCloseArms(false);
    	
    	//Move up
    	moveArmsVertical(3.0,.5);
    	
    	count.reset();
    	count.start();
    	
    	while(!count.hasPeriodPassed(2.25))
    		myRobot.arcadeDrive(-0.5,0.225);
    	
    	// Drive to middle of auto zone
    	driveStraight(4.5,.5);
    	
    	
    	
    	//Stop
    	endAuto(5.25);
    	//myRobot.arcadeDrive(0.0,0.0);
    
    }
    
    public void autonomousG()
    {
    	//Close arms
    	openCloseArms(false);
    	
    	//Move up
    	moveArmsVertical(3.0,.5);
    	
    	
    	
    	count.reset();
    	count.start();
    	while(!count.hasPeriodPassed(4.0)){
    		myRobot.arcadeDrive(-0.6, -0.15 );
    	}
    	
    	
    	
    	//Stop
    	endAuto(6.2);
    
    }
    
    public void autonomousH(){
    	//Close arms
    	openCloseArms(false);
    	
    	//Move up
    	moveArmsVertical(3.0,.5);
    	
    	endAuto(12);
    }
    
    // Stacks tote and bin and moves both to the auto zone
    public void autonomousE()
    {
    	openCloseArms(false);
    	moveArmsVertical(2.0,0.5);
    	//driveStraight(2.5,.35);
    	count.reset();
    	count.start();
    	while(!count.hasPeriodPassed(2.5)){
    		myRobot.arcadeDrive(-0.35, -0.15 );
    	}
    	moveArmsVertical(1.5,-0.4);
    	openCloseArms(true);
    	//driveTurn(0.5,.25);
    	//driveStraight(0.25,-.35);
    	moveArmsVertical(2.0,-0.4);
    	openCloseArms(false);
    	moveArmsVertical(1.5, 0.5);
    	count.reset();
    	count.start();
    	while (!count.hasPeriodPassed(1.5))
			myRobot.arcadeDrive(-0.1,-0.5);
    	//driveStraight(0.5, 0.4);
    	//driveStraight(4.75, .65);
    	//driveStraight(0.5, 0.4);
    	endAuto(15.0);
    	
    	while(!count.hasPeriodPassed(30.0))
    	{
    		myRobot.arcadeDrive(0.0, 0.0);
    	}
    	
    
    }
    
    //coopertition stack: move green can back
   public void autonomousC()
   {
	   //close arms
	   armSol.set(Value.kForward);
	   
	// pick up
   	while(!count.hasPeriodPassed(2.0))
   	{
   		pulleyMotor.set(.25);
   		//TODO set break here
   	}
   	
   	//move back 1 ft 6 in
	   while(!count.hasPeriodPassed(4.0))
		   myRobot.arcadeDrive(-.1, 0.0);
	   
	 //90 degree turn right
	 	while (!count.hasPeriodPassed(2.3))
	 			myRobot.arcadeDrive(0.1,0.4);
	 		
	while(!count.hasPeriodPassed(6.0))
   	{
   		//TODO let up break
   		pulleyMotor.set(-0.1);
   	}
   	
	armSol.set(Value.kReverse); 
	
	//90 degree turn left
 	while (!count.hasPeriodPassed(8.3))
 			myRobot.arcadeDrive(-0.1,0.4);
	  
	//moveforward 19 ft
	while(!count.hasPeriodPassed(14)){
		 myRobot.arcadeDrive(.25, 0.0);
	   }
	while(!count.hasPeriodPassed(20)){
		 myRobot.arcadeDrive(0.0, 0.0);
	   }
   }
    	
    	
   public void autonomousF2()
   {
   	//Close arms
	   
   	openCloseArms(false);
   	
   	//Move up
   	moveArmsVertical(3.0,.5);
   	
   	count.reset();
   	count.start();
   	
   	while(!count.hasPeriodPassed(2.25))
   		myRobot.arcadeDrive(0.5,0.225);
   	// Drive to middle of auto zone
   	driveStraight(4.5,-.5);
   	
   	
   	
   	//Stop
   	//myRobot.arcadeDrive(0.0,0.0);
   	//endAuto(5.5);
   
   }
    	
    

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
    	encoderR.reset();
    	encoderL.reset();
    while(isEnabled())	{
    	 
    		SmartDashboard.putNumber("Distance Left", encoderL.getDistance());
    		SmartDashboard.putNumber("Distance Right", encoderR.getDistance());
    		SmartDashboard.putNumber("testnum",1.0);
    		SmartDashboard.putBoolean("I am the limit", limitSwitch.get());
    		
    		
    		myRobot.arcadeDrive(stick, true);
    		
    	   pulleyMotor.set(0.0);
    	   breakSol.set(Value.kForward);
  
    	   /*while(!(limitSwitch.get())){ 	 
    	    	 pulleyMotor.set(Math.pow(xBox.getY(), 2));
    	    	  }
    		   */
 if(xBox.getY()>.15 || xBox.getY()<-0.1)
    	   breakSol.set(Value.kReverse);
 while(xBox.getY()>.15 || xBox.getY()<-0.1){
    //boolean hasLimitReached=false;
	
	
	
    	   pulleyMotor.set(Math.pow(xBox.getY(), 3));
    	   myRobot.arcadeDrive(stick,true);
    	   
    	   while(xBox.getAButton())
       	   {
       		   armSol.set(Value.kReverse);       		  
       	   }
       	   
       	   while(xBox.getBButton())
     	   {   
    			armSol.set(Value.kForward);     			     		   
     	   }
    
    
       }
     
     pulleyMotor.set(0.0);
	 breakSol.set(Value.kForward);

   	 while(xBox.getAButton())
   	   {
   		   armSol.set(Value.kReverse);  		  
   	   }
   	   
   	 while(xBox.getBButton())
 	   {   
			armSol.set(Value.kForward); 
 	   }

    		    	
    }
}
       	
       	
       	//armSol.set(false);
       	//armSol1.set(false);  //turns the Solenoid off	
    	
       	 
    	
        
    
  
    
    
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
    
    }
    
}
