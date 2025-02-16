// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.spark.SparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.spark.SparkLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class elevator extends SubsystemBase {
  //defining the objects
  private final SparkMax elevatorMotor = new SparkMax(Constants.elevator.kLiftMotorId, MotorType.kBrushless);
  private final RelativeEncoder elevatorEncoder = elevatorMotor.getEncoder();
  /** Creates a new elevator. */
  public elevator() {}

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
