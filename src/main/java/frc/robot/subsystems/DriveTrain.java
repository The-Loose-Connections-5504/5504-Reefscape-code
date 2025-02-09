// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;


import edu.wpi.first.wpilibj.drive.MecanumDrive;
import edu.wpi.first.wpilibj.motorcontrol.PWMSparkMax;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class DriveTrain extends SubsystemBase {
  //Defines Motors
  private final PWMSparkMax frontLeft = new PWMSparkMax(Constants.DriveTrainMotorIDs.leftFrontWheelID);
  private final PWMSparkMax frontRight = new PWMSparkMax(Constants.DriveTrainMotorIDs.rightFrontWheelID);
  private final PWMSparkMax backLeft = new PWMSparkMax(Constants.DriveTrainMotorIDs.leftBackWheelID);
  private final PWMSparkMax backRight = new PWMSparkMax(Constants.DriveTrainMotorIDs.rightBackWheelID);
  //defines DriveTrain
  private final MecanumDrive mDrive = new MecanumDrive(frontLeft, backLeft, frontRight, backRight);
  
  /** Creates a new DriveTrain. */
  public DriveTrain() {}

//defines the method for driving  -- Note from the programmer, we'll be using this quite a bit for Autons
  public void drive(double xspeed, double yspeed, double turn){
    mDrive.driveCartesian(xspeed, yspeed, turn);
  }
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
