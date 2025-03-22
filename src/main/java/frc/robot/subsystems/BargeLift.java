// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;


//imports stuff used in code
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.SparkLowLevel.MotorType;
public class BargeLift extends SubsystemBase {
  //Defines Motors
  private final SparkMax kBargeMotor = new SparkMax(Constants.BargeLiftConstants.bargeLiftID, MotorType.kBrushless);
  private final SparkMaxConfig kRotatorConfig = new SparkMaxConfig();
  /** Creates a new BargeLift. */
  public BargeLift() {
    kRotatorConfig
    .idleMode(IdleMode.kCoast);
    

    //I don't know what the hell this voltage  does but it's here and it might work sooo.......
    
    kBargeMotor.configure(kRotatorConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
  }

  public void powerBarge(double Power){
    kBargeMotor.set(Power);
  }
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
