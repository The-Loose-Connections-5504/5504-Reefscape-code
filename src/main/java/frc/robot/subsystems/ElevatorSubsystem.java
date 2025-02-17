// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.RelativeEncoder;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;


public class ElevatorSubsystem extends SubsystemBase {
  //defines objects
  private final SparkMax m_motor = new SparkMax(Constants.elevatorConstants.kLiftMotorId, MotorType.kBrushless);
  private final RelativeEncoder m_encoder = m_motor.getEncoder();
  // CHANGE PID VALUES IN CONSTANTS IF NEEDED
  private final PIDController m_Controller = new PIDController(
                                                Constants.elevatorConstants.kP,
                                                Constants.elevatorConstants.kI, 
                                                Constants.elevatorConstants.kD, 0.02);
  //Assuming this is roughly correct -- might change IF NEEDED.  
  private final double ticks2meters = 1.0/42*0.1*Math.PI;
  /** Creates a new ElevatorSubsystem. */
  public ElevatorSubsystem() {}
// Gets meters based off current position of Encoder
  public double GetMeters(){
    return m_encoder.getPosition() * ticks2meters;
  }
  public void setSpeed(double Speed){
    m_motor.set(Speed);
  }
  public void ClimbTo(double heightInMeters){
    setSpeed(m_Controller.calculate(GetMeters()));
    m_Controller.setSetpoint(heightInMeters);
  }
  @Override
  public void periodic() {
    double Variable = m_encoder.getPosition() * ticks2meters;
    System.out.println(Variable);
    // This method will be called once per scheduler run
  }
}
