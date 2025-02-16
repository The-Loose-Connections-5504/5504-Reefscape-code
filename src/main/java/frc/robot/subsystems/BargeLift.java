// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;


//imports stuff used in code
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;
public class BargeLift extends SubsystemBase {
  //Defines Motors
  private final SparkMax kBargeMotor = new SparkMax(Constants.BargeLiftConstants.bargeLiftID, MotorType.kBrushless);
  private final SparkMaxConfig config = new SparkMaxConfig();
  /** Creates a new BargeLift. */
  public BargeLift() {
    kBargeMotor.configure(config, ResetMode.kResetSafeParameters,PersistMode.kPersistParameters);
  }

  public void powerBarge(double Power){
    kBargeMotor.set(Power);
  }
  public void isBrakeModeOn(boolean isOn){
    if(isOn = true){
      config.idleMode(IdleMode.kBrake);
    }
    else if(isOn = false){
      config.idleMode(IdleMode.kCoast);
    }
    
  }
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
