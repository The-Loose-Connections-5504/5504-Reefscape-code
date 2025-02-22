// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.math.util.Units;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */

 // Public means you can access these ANYWHERE in the code.  
public final class Constants {
  public static class OperatorConstants { // Operate constants/Players
    public static final int mainDriverPort = 0;
    public static final int secondDriverPort = 1;
    public static final int iKennyArmRotaterID = 15;
    public static final int iKoralFeederID = 16;
  
  }
  public static class DriveTrainMotorIDs{ // OLD DRIVETRAIN Constants -- They Never Change so it's static
    public static final int leftFrontWheelID  = 30;
    public static final int rightFrontWheelID  = 31;
    public static final int leftBackWheelID  = 32;
    public static final int rightBackWheelID  = 33;
  }

  public static class AlgeMoverConstants{ // AlgeMover Constants -- They Never Change so it's static
    public static final int algaeMoverID = 17;
    public static final int algaeRotater = 18;
  }
  public static class BargeLiftConstants{ // BargeLift Constants -- They Never Change so it's static
    public static final int bargeLiftID = 14;
  }
  public static class KennysArmConstants{ // Kenny's Arm Constants -- Static cause they never change
    public static final int iKennyArmRotaterID = 20;
    public static final int iKoralFeederID = 21;
  }
  public static class elevatorConstants{ // Kenny's Arm Constants -- Static cause they never change
    public static final int kLiftMotorId = 13;
    public static final int kLiftMotor2ID = 28;
  }
  public static class GettinSwervy{
    public static final double maximumSpeed = Units.feetToMeters(4.5);
  }
}
