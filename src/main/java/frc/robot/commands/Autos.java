// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.
//Imports stuff used in the code
package frc.robot.commands;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import frc.robot.subsystems.DriveTrain;

public final class Autos {
  /** Example static factory for an autonomous command. */
  // the (DriveTrin DriveTain) is adding a requirment to the function.  
  public static Command Test(DriveTrain DriveTrain){
    return Commands.sequence(
      DriveTrain.run(()-> DriveTrain.drive(1, 0, 0)).withTimeout(1),
      DriveTrain.run(()-> DriveTrain.drive(0, 1, 0)).withTimeout(2),
      DriveTrain.run(()-> DriveTrain.drive(0, .0, 0)).withTimeout(3)
    );

  }
  
  public static Command goofySpeeds(DriveTrain DriveTrain){
    return Commands.sequence(
      DriveTrain.run(()-> DriveTrain.drive(.5, 0, 0)).withTimeout(4),
      DriveTrain.run(()-> DriveTrain.drive(0, .5, 0)).withTimeout(5),
      DriveTrain.run(()-> DriveTrain.drive(0, .0, 0)).withTimeout(6)
    );
  }
  private Autos() {
    throw new UnsupportedOperationException("This is a utility class!");
  }
}
