// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;


public class ElevatorSubsystem extends SubsystemBase {
  //defines objects
  private final SparkMax m_motor = new SparkMax(Constants.elevatorConstants.kLiftMotorId, MotorType.kBrushless);
  private final SparkMax m_motor2 = new SparkMax(Constants.elevatorConstants.kLiftMotor2ID, MotorType.kBrushless);
  // CHANGE PID VALUES IN CONSTANTS IF NEEDED
  //Assuming this is roughly correct -- might change IF NEEDED.  
  /** Creates a new ElevatorSubsystem. */
  double HeightInTicks =0;
  public ElevatorSubsystem() {}
// Gets meters based off current position of Encoder
  public void setSpeed(double Speed){
    m_motor.set(Speed);
    m_motor2.set(Speed);
  }
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
  public void addHeight(double rate) {
     HeightInTicks += rate;
  }
  public void subtract(double rate) {
     HeightInTicks -= rate;
  }
  public double GetHeightInTicks() {
    return HeightInTicks;
  }
  public Command MoveUp(double speed){
    return Commands.parallel(
     run(()-> addHeight(2)),
     run(()->setSpeed(speed))
    ).until(()->GetHeightInTicks() >=2000);
  }
  //God this is a Hacky solution to a problem but I do not care because of time...
  public Command MoveDown(double speed){
    return Commands.parallel(
     run(()-> addHeight(2)),
     run(()->setSpeed(speed))
    ).until(()->GetHeightInTicks() <= 0);
  }
}
