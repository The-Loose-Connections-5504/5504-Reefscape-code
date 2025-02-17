// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.
//Imports stuff used in the code
package frc.robot.commands;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import frc.robot.subsystems.AlgeMover;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.ElevatorSubsystem;
import frc.robot.subsystems.KennysArm;

public final class Autos {
  /** Example static factory for an autonomous command. */
  // the (DriveTrin DriveTain) is adding a requirment to the function. - More can be added by adding a , 
  public static  Command kDriveForwardForTwoSeconds(DriveTrain DriveTrain){ // Simple Auto -- Keep
    return Commands.sequence( //Sequenence:List of Commands to Run, Paralell:Runs all the commands at once
      DriveTrain.run(()-> DriveTrain.drive(.5, 0, 0)).withTimeout(2),
      //withTimeout() is the duration of a command/how long it lasts
      DriveTrain.run(()-> DriveTrain.drive(0, 0, 0))
    );

  }
  
  public static Command goofySpeeds(DriveTrain DriveTrain){ //Test Command Write Over this -- DO NOT COMMENT OUT
    return Commands.sequence(
      DriveTrain.run(()-> DriveTrain.drive(.5, 0, 0)).withTimeout(2),
      DriveTrain.run(()-> DriveTrain.drive(0, .5, 0)).withTimeout(5),
      DriveTrain.run(()-> DriveTrain.drive(0, .0, 0)).withTimeout(10)
    );
  }
    public static Command motorTest(KennysArm kennysArm){ //Test Command - Write Over this -- DO NOT COMMENT OUT
      return Commands.sequence(
        kennysArm.run(()->kennysArm.rotateArm(1)).withTimeout(2),
        kennysArm.run(()->kennysArm.rotateArm(-1)).withTimeout(3),
        kennysArm.run(()->kennysArm.rotateArm(1)).withTimeout(5),
        kennysArm.run(()->kennysArm.rotateArm(0)).withTimeout(9)
        );
    }
     public static Command kTestElevator(ElevatorSubsystem kElevatorSubsystem){ //Test Command - Write Over this -- DO NOT COMMENT OUT
      return Commands.sequence(
        kElevatorSubsystem.run(()->kElevatorSubsystem.ClimbTo(1.5)).withTimeout(9),
        kElevatorSubsystem.run(()->kElevatorSubsystem.ClimbTo(0)).withTimeout(10)
        );
  }
    public static Command kRunonceExample(AlgeMover kMoo){ //REMOVE -- DO NOT COMMENT OUT....
      return kMoo.run(()->kMoo.setMoverSpeed(1));
    }
  private Autos() {
    throw new UnsupportedOperationException("This is a utility class!");
  }
}


