// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;
//Imports the things used in the code
import frc.robot.commands.Autos;
import frc.robot.subsystems.BargeLift;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.KennysArm;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import edu.wpi.first.wpilibj2.command.button.CommandJoystick;
// 
/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {
  //The Joysticks are defined here...  
  // You maybe wondering why it's CommandJoystick and not just Joystick, this is because this is a Command Based Project
  private  final CommandJoystick stick1 = new CommandJoystick(Constants.OperatorConstants.mainDriverPort);
  private  final CommandJoystick stick2 = new CommandJoystick(Constants.OperatorConstants.secondDriverPort);
  // The robot's subsystems and commands are defined here...
  private final DriveTrain mdrive = new DriveTrain();
  private final BargeLift mBargeLift = new BargeLift();
  private final KennysArm mKennysArm = new KennysArm();
  private final Command kTest = Autos.goofySpeeds(mdrive);
  private final Command kTest2 = Autos.Test(mdrive);
  private final Command kMotorTest = Autos.motorTest(mKennysArm);
  //The Auton Chooser is defined here...
  private final SendableChooser<Command> kChooser = new SendableChooser<>();


  // Replace with CommandPS4Controller or CommandJoystick if needed
  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the trigger bindings
    configureBindings();
    //Adds the commands to sendable chooser
    defineCommands();
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
    mdrive.setDefaultCommand(mdrive.run(()-> mdrive.drive(stick1.getRawAxis(1), stick1.getRawAxis(1), stick1.getRawAxis(1))));
    //Button Inputs  and ()-> is required
    //This is for the Barge
    stick1.button(2)
      .onTrue(mBargeLift.run(()->mBargeLift.powerBarge(1)))
      .onFalse(mBargeLift.run(()->mBargeLift.powerBarge(0)));
  }
  public void defineCommands(){
    //Adds a tab to the Shuffleboard based off the SmartDashBoard
    Shuffleboard.getTab("Autonomous").add(kChooser);
    //Adds the options for the commands created in Autos.java
    kChooser.addOption("goofySpeeds", kTest);
    kChooser.addOption("Test 2 Man", kTest2);
    kChooser.addOption("MotorTest", kMotorTest);
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
