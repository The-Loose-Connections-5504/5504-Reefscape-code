// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;


//imports stuff used in code
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkLowLevel.MotorType;
public class BargeLift extends SubsystemBase {
  //Defines Motors
  private final SparkMax kBargeMotor = new SparkMax(Constants.BargeLiftConstants.bargeLiftID, MotorType.kBrushless);
  /** Creates a new BargeLift. */
  public BargeLift() {
  }

  public void powerBarge(double Power){
    kBargeMotor.set(Power);
  }
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
