// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.
//RUN "Git pull" in TERMINAL TO SEE IF CHANGES ARE MADE TO THE CODE, WHICH THEY HAVE BEEN MOST LIKELY
package frc.robot;
//Imports the things used in the code
import frc.robot.commands.Autos;
import frc.robot.subsystems.AlgeMover;
import frc.robot.subsystems.BargeLift;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.ElevatorSubsystem;
import frc.robot.subsystems.KennysArm;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj2.command.Command;
//Legit No difference from a CommandJoystick, just has the id's already set for a XBOX controller
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;
// 
/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {
  //The Joysticks are defined here...  
  // You maybe wondering why it's CommandXboxController and not just Joystick, this is because this is a Command Based Project
  private  final CommandXboxController stick1 = new CommandXboxController(Constants.OperatorConstants.mainDriverPort);
  private  final CommandXboxController stick2 = new CommandXboxController(Constants.OperatorConstants.secondDriverPort);
  // The robot's subsystems and commands are defined here...
  private final DriveTrain mdrive = new DriveTrain();
  private final BargeLift mBargeLift = new BargeLift();
  private final KennysArm mKennysArm = new KennysArm();
  private final AlgeMover mAlgeMover = new AlgeMover();
  private final ElevatorSubsystem mElevator = new ElevatorSubsystem();
  private final Command kTest = Autos.goofySpeeds(mdrive);
  private final Command kDriveForward = Autos.kDriveForwardForTwoSeconds(mdrive);
  private final Command kMotorTest = Autos.motorTest(mKennysArm);
  private final Command kElevatorTest = Autos.kTestElevator(mElevator);
  //The Auton Chooser is defined here...
  private final SendableChooser<Command> kChooser = new SendableChooser<>();
  //Values for throttle 
  double threshHoldY = 0.15;
  double threshHoldX = 0.15;
  double threshHoldZ = 0.15;
  double threshHoldTrig1 = 0.1;
  double threshHoldTrig2 = 0.1;
  double throttle = 0.25;
  //Deadzone
  double scaledDeadZoneX;
  double scaledDeadZoneY;
  double scaledDeadZoneTwist;


  // Replace with CommandPS4Controller or CommandJoystick if needed
  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    
    // Configure the trigger bindings
    configureBindings();
    //Adds the commands to sendable chooser
    defineCommands();
    //Throttle --- cause the driver would kill me otherwise
    if ((stick1.getRawAxis(2) > threshHoldTrig1 || stick1.getRawAxis(2) < threshHoldTrig1) && !(throttle < 0))
		{
			throttle = (throttle - (stick1.getRawAxis(2)/30));
		}

		if ((stick1.getRawAxis(3) > threshHoldTrig2 || stick1.getRawAxis(3) < threshHoldTrig2) && !(throttle > 1))
		{
			throttle = (throttle + (stick1.getRawAxis(3)/30));
		}
    //Clean Up throttle Value
    if(throttle > 1 || throttle < 0)
		{
			throttle = Math.round(throttle);
		}

    //Deadband 
    if (stick1.getRawAxis(1) > threshHoldY || stick1.getRawAxis(1) < threshHoldY * -1) 
		{
			scaledDeadZoneY = stick1.getRawAxis(1);
		}
		  else 
		  {
			scaledDeadZoneY = 0;
		}
    if (stick1.getRawAxis(0) > threshHoldX || stick1.getRawAxis(0) < threshHoldX * -1)
     {
      scaledDeadZoneX = stick1.getRawAxis(0);
      }
      else 
        {
          scaledDeadZoneX = 0;
        }
    if (stick1.getRawAxis(4) > threshHoldZ || stick1.getRawAxis(4) < threshHoldZ * -1) 
      {
        scaledDeadZoneTwist = stick1.getRawAxis(4);
      }
      else 
      {
        scaledDeadZoneTwist = 0;
      }

  //Hopefully deadband and Throttle functions as intended cause i pasted the original.. :P
  }

  /**
   * Use this method to define your trigger->command mappings. Triggers can be created via the
   * {@link Trigger#Trigger(java.util.function.BooleanSupplier)} constructor with an arbitrary
   * predicate, or via the named factories in {@link
   * edu.wpi.first.wpilibj2.command.button.CommandGenericHID}'s subclasses for {@link
   * CommandXboxController Xbox}/{@link edu.wpi.first.wpilibj2.command.button.CommandPS4Controller
   * PS4} controllers or {@link edu.wpi.first.wpilibj2.command.button.CommandJoystick Flight
   * joysticks}.
   */
  private void configureBindings() {
    //this cluster of a line controls the driving- currently doesn't have throttle- will fix ASAP
    mdrive.setDefaultCommand(mdrive.run(()-> mdrive.drive(scaledDeadZoneX * throttle, scaledDeadZoneY * throttle, scaledDeadZoneTwist * throttle)));
    //Button Inputs  and ()-> is required
    //Quinton' BargeLift - Player 1
    stick1.x().onTrue(mBargeLift.run(()->mBargeLift.powerBarge(1)));
    stick1.y().onTrue(mBargeLift.run(()->mBargeLift.powerBarge(-1)));

    //Kenny's Arm - Player 2
    stick2.rightBumper().onTrue(mKennysArm.run(()->mKennysArm.rotateArm(1)));
    stick2.leftBumper().onTrue(mKennysArm.run(()->mKennysArm.rotateArm(-1)));
    stick2.a().onTrue(mKennysArm.run(()->mKennysArm.intake(-1)));

    //Carsen and Nickolas' AlgeMover - Player 2
    stick2.b().onTrue(mAlgeMover.run(()->mAlgeMover.setMoverSpeed(1)));
    stick2.x().onTrue(mAlgeMover.run(()->mAlgeMover.setRotateSpeed(1)));
    stick2.y().onTrue(mAlgeMover.run(()->mAlgeMover.setRotateSpeed(-1)));

    //Swifty Elevator
    stick1.button(0).onTrue(mElevator.run(()->mElevator.ClimbTo(3)));
  }
  public void defineCommands(){
    //Adds a tab to the Shuffleboard based off the SmartDashBoard
    Shuffleboard.getTab("Autonomous").add(kChooser);
    //Adds the options for the commands created in Autos.java
    kChooser.addOption("Drive Forward for Two Seconds", kDriveForward);
    kChooser.addOption("goofySpeeds", kTest);
    kChooser.addOption("MotorTest", kMotorTest);
    kChooser.addOption("ElevatorTest", kElevatorTest);
  }
  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An example command will be run in autonomous
    return kChooser.getSelected();
  }
}
