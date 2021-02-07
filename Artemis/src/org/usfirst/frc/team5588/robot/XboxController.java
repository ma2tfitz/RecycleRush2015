/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.usfirst.frc.team5588.robot;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.GenericHID;
//import edu.wpi.first.wpilibj.parsing.IInputOutput;
import edu.wpi.first.wpilibj.Joystick.AxisType;

/**
 * A nearly drop in replacement for Joystick using an XBOX USB Controller
 * @author Gustave Michel @edits Kaveen Herath & James Lightfoot
 */
public class XboxController extends GenericHID  {
    
    private DriverStation m_ds;
    private final int m_port;
    
    /**
     * Represents an analog axis on a joystick.
     */
    public static class AxisType {

    public final int value;
    
       /* public static final int kLeftX_val = 0;
        public static final int kLeftY_val = 1;
        public static final int kTrigger_val = 3;
        public static final int kRightX_val = 4;
        public static final int kRightY_val = 5;
        public static final int kDLeftRight_val = 6;
        */
        public AxisType(int value)
        {
            this.value = value;
        }
            // Axis: Left X
           // public static final AxisType kDRIVEltRT = new AxisType(4);
            // Axis: Left Y
           // public static final AxisType kDRIVEfwdREV = new AxisType(5);
            // Axis: Triggers
           // public static final AxisType kBBarmMANctrl = new AxisType(kTrigger_val);
            // Axis: Right X
            //public static final AxisType kRightX = new AxisType(kLeftX_val);
            // Axis: Right Y
            public static final AxisType leftY = new AxisType(1);
            public static final AxisType leftX = new AxisType(0);
            public static final AxisType rightX = new AxisType(4);
            public static final AxisType rightY = new AxisType(5);
            
            // Axis: D-Pad Left-Right
            //public static final AxisType kDLeftRight = new AxisType(kDLeftRight_val);
    }
   /* 
   * Represents a digital button on a joystick.
   */
    public static class ButtonType {
        /**
        * The integer values for each of the Xbox Controller's buttons
        */
        public final int value;
        public static final int kA_val = 1;
        public static final int kB_val = 2;
        public static final int kX_val = 3;
        public static final int kY_val = 4;
        // public static final int kLB_val = 5;
        public static final int kRB_val = 6;
        // public static final int kBack_val = 7;
        // public static final int kStart_val = 8;
        //public static final int kLeftStick_val = 9;
         public static final int kRightStick_val = 10;
        // public static final int kRTrigger_val = 11;
        // public static final int kLTrigger_val = 12;
        
        public ButtonType(int value) {
            this.value = value;
        }
            //  Button: A
            public static final ButtonType kBParmsCLOSE = new ButtonType(kA_val);
            //  Button: B
            public static final ButtonType kBParmsPROCautoSTART = new ButtonType(kB_val);
             //  Button: X
            public static final ButtonType kBParmsOPEN = new ButtonType(kX_val);
            //  Button: Y
            public static final ButtonType kBParmsPROCautoCANCEL = new ButtonType(kY_val);
            //  Button: LB
            // public static final ButtonType kLB = new ButtonType(kLB_val);
            //  Button: RB
            public static final ButtonType kBBarmFIRE = new ButtonType(kRB_val);
            //  Button: Back
            // public static final ButtonType kBack = new ButtonType(kBack_val);
            //  Button: Start
            // public static final ButtonType kStart = new ButtonType(kStart_val);
            //  Button: Left Joystick
            public static final ButtonType kDRIVEspeedSLOW = new ButtonType(kRightStick_val);
            //  Button: Right Joystick
            // public static final ButtonType kRightStick = new ButtonType(kRightStick_val);
            //  Button: Right Trigger
            // public static final ButtonType kRTrigger = new ButtonType(kRTrigger_val);
            //  Button: Left Trigger
            // public static final ButtonType kLTrigger = new ButtonType(kLTrigger_val);
            
    }
     /**
     * Constructor
     * @param port USB Port on DriverStation
     */
    public XboxController(int port) {
        super();
        m_port = port;
        m_ds = DriverStation.getInstance();
    }
    /**
     * Get Value from an Axis
     * @param axis Axis Number
     * @return Value from Axis (-1 to 1)
     */
    public double getRawAxis(int axis) {
        return m_ds.getStickAxis(m_port, axis);
    }
    
    /**
     * Get Value from an Axis
     * @param axis AxisType
     * @return 
     */
    public double getAxis(AxisType axis) {
        return getRawAxis(axis.value);
    }
    
    /**
     * Retrieve value for X axis of both joysticks
     * @param hand Hand associated with the Joystick
     * @return Value of Axis (-1 to 1)
     */
    public double getX(Hand hand) {
            return 0;
        
    }
    
    /**
     * Retrieve value for Y axis of both joysticks
     * @param hand Hand associated with the Joystick
     * @return Value of Axis (-1 to 1)
     */
    public double getY(Hand hand) {
        	return getAxis(AxisType.leftY); 		 
    }
   
    
    /**
     * Unused
     * @param hand Unused
     * @return 0
     */
    public double getZ(Hand hand) {
        return 0;
    }
    
    /**
     * Gets Value from D-Pad Left and Right Axis
     * @return Axis Value (-1 to 1)
     */
    // public double getTwist() {
       // return getAxis(AxisType.kDLeftRight);
    // }
    
    /**
     * Gets Value from Back Triggers
     * @return Axis Value (-1 to 1)
     */
   public double getThrottle() {
        return 0.0; //getAxis(AxisType.kBBarmMANctrl);
    }
    
    
    /**
     * Gets value from a trigger functioning as a button
     * @param button number of the button 
     * @return State of the button
     */
    // public boolean getRawButton(int button) {
        // if(button == ButtonType.kRTrigger.value) { //Abstracted Buttons from Analog Axis
            // if(getThrottle() <= -.6) {
                // return true;
            // }
            // else {
                // return false;
            // }
        // }
        // if(button == ButtonType.kLTrigger.value) { //Abstracted Buttons from Analog Axis
            // if(getThrottle() >= .6) {
                // return true;
            // }
            // else {
                // return false;
            // }
        // }
        // return ((0x1 << (button - 1)) & m_ds.getStickButtons(m_port)) != 0;
    // 
    
    /**
     * Get Value from a button
     * @param button Button Type
     * @return 
     */
    public boolean getButton(ButtonType button) {
        return getRawButton(button.value);
    }
    public boolean getRawButton(final int button) {
        return m_ds.getStickButton(m_port, (byte)button);
    }
    
  
    public double getTwist(){
    	return 0.0;
    }
    public int getPOV(int i){
    	return 0;
    }
   
    
    /**
     * Get Trigger Value as Button
     * @param hand Hand associated with button
     * @return false
     */
    public boolean getTrigger(Hand hand) {
        /* if(hand == Hand.kLeft) {
             return getButton(ButtonType.kLTrigger);
         } else if(hand == Hand.kRight) {
             return getButton(ButtonType.kRTrigger);
         } else {*/
             return false;
         
     }
    
    /**
     * Get Button from Joystick
     * @param hand hand associated with the button
     * @return Button Status (true or false)
     */
    public boolean getTop(Hand hand) {
        // if(hand == Hand.kRight) {
            // return getButton(ButtonType.kRightStick);
        // } else if(hand == Hand.kLeft) {
	if(hand == Hand.kLeft) {    
            return getButton(ButtonType.kDRIVEspeedSLOW);
        } else {
            return false;
        }
    }
    
    /**
     * Get Value from Back buttons
     * @param hand hand associated with the button
     * @return state of left or right 
     */
    public boolean getBumper(Hand hand) {
        if(hand == Hand.kRight) {
            return getButton(ButtonType.kBBarmFIRE);
        // } else if(hand == Hand.kLeft) {
            // return getButton(ButtonType.kLB);
        } else {
            return false;
          }
    }
    
    /**
     * Get State of Select Button
     * @return State of button
     */
    // public boolean getStart() {
        // return getButton(ButtonType.kStart);
    // }
    
    /**
     * Get State of Back Button
     * @return State of button
     */
    // public boolean getBack() {
        // return getButton(ButtonType.kBack);
    // }
    
    /**
     * Get State of A Button
     * @return State of button
     */
    public boolean getAButton() {
        return getButton(ButtonType.kBParmsCLOSE);
    }
    
    /**
     * Get State of B Button
     * @return State of button
     */
    public boolean getBButton() {
        return getButton(ButtonType.kBParmsPROCautoSTART);
    }
    
    /**
     * Get State of X Button
     * @return State of button
     */
    public boolean getXButton() {
        return getButton(ButtonType.kBParmsOPEN);
    }
    
    /**
     * Get State of Y Button
     * @return State of button
     */
    public boolean getYButton() {
        return getButton(ButtonType.kBParmsPROCautoCANCEL);

        }
    }
