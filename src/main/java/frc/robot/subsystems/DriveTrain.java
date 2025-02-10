// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

//imports stuff for the code, you know what.... you get the gist  
import edu.wpi.first.wpilibj.drive.MecanumDrive;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.SparkMax;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class DriveTrain extends SubsystemBase {
  //Defines Motors
  private final SparkMax frontLeft = new SparkMax(Constants.DriveTrainMotorIDs.leftFrontWheelID, MotorType.kBrushless);
  private final SparkMax frontRight = new SparkMax(Constants.DriveTrainMotorIDs.rightFrontWheelID, MotorType.kBrushless);
  private final SparkMax backLeft = new SparkMax(Constants.DriveTrainMotorIDs.leftBackWheelID, MotorType.kBrushless);
  private final SparkMax backRight = new SparkMax(Constants.DriveTrainMotorIDs.rightBackWheelID, MotorType.kBrushless);
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
