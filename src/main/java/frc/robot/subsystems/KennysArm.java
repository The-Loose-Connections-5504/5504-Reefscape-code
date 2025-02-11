// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class KennysArm extends SubsystemBase {
  private final SparkMax kKennyArmRotator = new SparkMax(Constants.OperatorConstants.iKennyArmRotaterID, MotorType.kBrushless);
  private final SparkMax kKennyKoralSeeder = new SparkMax(Constants.OperatorConstants.iKoralFeederID, MotorType.kBrushless);
  private final SparkMaxConfig kRotatorConfig = new SparkMaxConfig();
  /** Creates a new KennysArm. */
  public KennysArm() {
    //I blame Kenny if this doesn't work :P
    kRotatorConfig.idleMode(IdleMode.kBrake);
    //I don't know what the hell this voltage  does but it's here and it might work sooo.......
    kKennyArmRotator.setVoltage(1);
    kKennyArmRotator.configure(kRotatorConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
  }

  public void rotateArm (double speed){
    kKennyArmRotator.set(speed);
  }
  public void intake (double speed){
    kKennyKoralSeeder.set(speed);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
