// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.SparkLowLevel.MotorType;


import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;


public class ElevatorSubsystem extends SubsystemBase {
  //defines objects
  private final SparkMax m_motor = new SparkMax(Constants.elevatorConstants.kLiftMotorId, MotorType.kBrushless);

  private final SparkMaxConfig kRotatorConfig = new SparkMaxConfig();
  private final RelativeEncoder mEncoder;

  // CHANGE PID VALUES IN CONSTANTS IF NEEDED
  //Assuming this is roughly correct -- might change IF NEEDED.  
  /** Creates a new ElevatorSubsystem. */
  public ElevatorSubsystem() {
     mEncoder = m_motor.getEncoder();
     mEncoder.setPosition(0);
     kRotatorConfig
     .idleMode(IdleMode.kBrake);
     
    //I now know what voltage does and should not be used unless needed

     m_motor.configure(kRotatorConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
    
  }
// Gets meters based off current position of Encoder
  public void setSpeed(double Speed){
    m_motor.set(Speed);
   
  }
  public void climbTo(double height){
    if (height > mEncoder.getPosition()){
      setSpeed(-0.55);
      kRotatorConfig
      .idleMode(IdleMode.kCoast);
      m_motor.configure(kRotatorConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
    }
    else if(height < mEncoder.getPosition() && mEncoder.getPosition() > 0){
      setSpeed(0.55);
      kRotatorConfig
        .idleMode(IdleMode.kCoast);
      m_motor.configure(kRotatorConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
    }
    else
      setSpeed(0);
      kRotatorConfig
      . idleMode(IdleMode.kBrake);
      m_motor.configure(kRotatorConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
  }
  @Override
  public void periodic() {
    SmartDashboard.putNumber("Elevator Encoder", mEncoder.getPosition());
    // This method will be called once per scheduler run
  }

  }