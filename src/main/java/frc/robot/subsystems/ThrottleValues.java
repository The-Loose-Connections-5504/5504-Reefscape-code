// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.Constants;

public class ThrottleValues extends SubsystemBase {
  private  final CommandXboxController stick1 = new CommandXboxController(Constants.OperatorConstants.mainDriverPort);
  double threshHoldY = 0.15;
  double threshHoldX = 0.15;
  double threshHoldZ = 0.15;
  double threshHoldTrig1 = 0.1;
  double threshHoldTrig2 = 0.1;
  double throttle = 0.25;
  //Deadzone
  double scaledDeadZoneX;
  double scaledDeadZoneY;
  double scaledDeadZoneTwist;
  double ElevatorHeight;
  /** Creates a new ThrottleValues. */
  public ThrottleValues(CommandXboxController stick1) {}

  @Override
  public void periodic() {

      SmartDashboard
       .putNumber("X_AXIS", X_AXIS());

      SmartDashboard
      .putNumber("Y_AXIS", Y_AXIS());

      SmartDashboard
      .putNumber("Twist_AXIS", Twist_AXIS());
      
    if ((stick1.getRawAxis(2) > threshHoldTrig1 || stick1.getRawAxis(2) < threshHoldTrig1) && !(throttle < 0))
    {
      throttle = (throttle - (stick1.getRawAxis(2)/30));
    }

    if ((stick1.getRawAxis(3) > threshHoldTrig2 || stick1.getRawAxis(3) < threshHoldTrig2) && !(throttle > 1))
    {
      throttle = (throttle + (stick1.getRawAxis(3)/30));
    }
    //Clean Up throttle Value
    if(throttle > 1 || throttle < 0)
    {
      throttle = Math.round(throttle);
    }

    //Deadband 
    if (stick1.getRawAxis(1) > threshHoldY || stick1.getRawAxis(1) < threshHoldY * -1) 
    {
      scaledDeadZoneY = stick1.getRawAxis(1);
    }
      else 
      {
      scaledDeadZoneY = 0;
    }
    if (stick1.getRawAxis(0) > threshHoldX || stick1.getRawAxis(0) < threshHoldX * -1)
     {
      scaledDeadZoneX = stick1.getRawAxis(0);
      }
      else 
        {
          scaledDeadZoneX = 0;
        }
    if (stick1.getRawAxis(4) > threshHoldZ || stick1.getRawAxis(4) < threshHoldZ * -1) 
      {
        scaledDeadZoneTwist = stick1.getRawAxis(4);
      }
      else 
      {
        scaledDeadZoneTwist = 0;
      }
    }
    public double X_AXIS(){
      return scaledDeadZoneX * throttle;
    }
    public double Y_AXIS(){
      return scaledDeadZoneY * throttle;
    }
    public double Twist_AXIS(){
      return scaledDeadZoneTwist * throttle;
    }
    // This method will be called once per scheduler run
  }

