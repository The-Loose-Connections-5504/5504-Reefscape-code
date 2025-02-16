// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class AlgeMover extends SubsystemBase {
  private final SparkMax kMoverRoatater = new SparkMax(Constants.AlgeMoverConstants.algaeRotater, MotorType.kBrushless);
  private final SparkMax kIntake = new SparkMax(Constants.AlgeMoverConstants.algaeMoverID, MotorType.kBrushless);
  private final SparkMaxConfig config = new SparkMaxConfig();
  /** Creates a new AlgeMover. */
  public AlgeMover() {
    kMoverRoatater.setVoltage(1);
    config.idleMode(IdleMode.kBrake);
    kMoverRoatater.configure(config, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
  }
  public void setMoverSpeed(double speed){
    kIntake.set(speed);
  }
  public void setRotateSpeed(double speed){
    kMoverRoatater.set(speed);
  }


  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
