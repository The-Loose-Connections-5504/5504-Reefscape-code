// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;


public class ElevatorSubsystem extends SubsystemBase {
  //defines objects
  private final SparkMax m_motor = new SparkMax(Constants.elevatorConstants.kLiftMotorId, MotorType.kBrushless);
  private final SparkMaxConfig kRotatorConfig = new SparkMaxConfig();

  // CHANGE PID VALUES IN CONSTANTS IF NEEDED
  //Assuming this is roughly correct -- might change IF NEEDED.  
  /** Creates a new ElevatorSubsystem. */
  public ElevatorSubsystem() {
     kRotatorConfig.idleMode(IdleMode.kCoast);
    //I don't know what the hell this voltage  does but it's here and it might work sooo.......
    
    m_motor.configure(kRotatorConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
  }
// Gets meters based off current position of Encoder
  public void setSpeed(double Speed){
    m_motor.set(Speed);
  }
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  }