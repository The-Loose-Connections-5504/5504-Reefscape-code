// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.
//RUN "Git pull" in TERMINAL TO SEE IF CHANGES ARE MADE TO THE CODE, WHICH THEY HAVE BEEN MOST LIKELY
package frc.robot;
//Imports the things used in the code

import frc.robot.subsystems.BargeLift;
import frc.robot.subsystems.ElevatorSubsystem;
import frc.robot.subsystems.KennysArm;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj2.command.Command;
//Legit No difference from a CommandJoystick, just has the id's already set for a XBOX controller
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;


//CTRE

import static edu.wpi.first.units.Units.*;

import com.ctre.phoenix6.swerve.SwerveModule.DriveRequestType;
import com.ctre.phoenix6.swerve.SwerveRequest;

import com.pathplanner.lib.auto.AutoBuilder;
import com.pathplanner.lib.auto.NamedCommands;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.sysid.SysIdRoutine.Direction;

import frc.robot.generated.TunerConstants;
import frc.robot.subsystems.CommandSwerveDrivetrain;
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
  private final BargeLift mBargeLift = new BargeLift();
  private final KennysArm mKennysArm = new KennysArm();
  private final ElevatorSubsystem mElevator = new ElevatorSubsystem();
  //The Auton Chooser is defined here...
//CTRE
  private double MaxSpeed = TunerConstants.kSpeedAt12Volts.in(MetersPerSecond); // kSpeedAt12Volts desired top speed
  private double MaxAngularRate = RotationsPerSecond.of(0.75).in(RadiansPerSecond); // 3/4 of a rotation per second max angular velocity

  /* Setting up bindings for necessary control of the swerve drive platform */
  private final SwerveRequest.FieldCentric drive = new SwerveRequest.FieldCentric()
          .withDeadband(MaxSpeed * 0.2).withRotationalDeadband(MaxAngularRate * 0.2) // Add a 10% deadband
          .withDriveRequestType(DriveRequestType.OpenLoopVoltage); // Use open-loop control for drive motors
  private final SwerveRequest.SwerveDriveBrake brake = new SwerveRequest.SwerveDriveBrake();
  private final SwerveRequest.PointWheelsAt point = new SwerveRequest.PointWheelsAt();
  private final SwerveRequest.RobotCentric forwardStraight = new SwerveRequest.RobotCentric()
          .withDriveRequestType(DriveRequestType.OpenLoopVoltage);

  private final Telemetry logger = new Telemetry(MaxSpeed);

  public final CommandSwerveDrivetrain drivetrain = TunerConstants.createDrivetrain();

  /* Path follower */




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
  double ElevatorHeight = 0;


  //CTRE


  /* Path follower */
  private final SendableChooser<Command> kChooser;


  // Replace with CommandPS4Controller or CommandJoystick if needed
  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
  
    //Throttle --- cause the driver would kill me otherwise

  //Hopefully deadband and Throttle functions as intended cause i pasted the original.. :P



  //PathPlanner == DO NOT MESS WITH
  //NAMED COMMANDS AND EVENT MARKERS NEED TO BE REGISTERED BEFORE AutoBuilder is made so HENCE why defineCommands are made there
  //DO NOT TOUCH JAY!!!
  configureBindings();
  defineCommands();
  NamedCommands.registerCommand("elevatorMoveUp", mElevator.run(()->mElevator.setSpeed(.5)).withTimeout(1));
  NamedCommands.registerCommand("elevatorShut", mElevator.run(()->mElevator.setSpeed(.0)).withTimeout(2));
  NamedCommands.registerCommand("KennyArmMoveUp", mKennysArm.run(()->mKennysArm.rotateArm(.5)).withTimeout(1));
  NamedCommands.registerCommand("kennyArmShoot", mKennysArm.run(()->mKennysArm.intake(.6)).withTimeout(1.5));
  NamedCommands.registerCommand("KennyArmShutOff", mKennysArm.run(()->mKennysArm.rotateArm(0)).withTimeout(2));
  NamedCommands.registerCommand("KennyArmIntakeShutOff", mKennysArm.run(()->mKennysArm.intake(0)).withTimeout(2));
  kChooser  = AutoBuilder.buildAutoChooser("Drive in a Straight Line");
  SmartDashboard.putData("Auto Mode", kChooser);

  }

  /**
   * Use this method to define your trigger->command mappings. Triggers can be created via thes
   * {@link Trigger#Trigger(java.util.function.BooleanSupplier)} constructor with an arbitrary
   * predicate, or via the named factories in {@link
   * edu.wpi.first.wpilibj2.command.button.CommandGenericHID}'s subclasses for {@link
   * CommandXboxController Xbox}/{@link edu.wpi.first.wpilibj2.command.button.CommandPS4Controller
   * PS4} controllers or {@link edu.wpi.first.wpilibj2.command.button.CommandJoystick Flight
   * joysticks}.
   */
  private void configureBindings() {
    //this cluster of a line controls the driving- currently doesn't have throttle- will fix ASAP

    //Button Inputs  and ()-> is required
    //Quinton' BargeLift - Player 1
    stick1.x()
      .onTrue(mBargeLift.run(()->mBargeLift.powerBarge(0.5)))
      .onFalse(mBargeLift.run(()->mBargeLift.powerBarge(0)));

    stick1.y()
      .onTrue(mBargeLift.run(()->mBargeLift.powerBarge(-0.5)))
      .onFalse(mBargeLift.run(()->mBargeLift.powerBarge(0)));
    //Kenny's Arm - Player 2
    stick2.rightBumper()
      .onTrue(mKennysArm.run(()->mKennysArm.rotateArm(-0.25)))
      .onFalse(mKennysArm.run(()->mKennysArm.rotateArm(0)));
    stick2.leftBumper()
      .onTrue(mKennysArm.run(()->mKennysArm.rotateArm(0.25)))
      .onFalse(mKennysArm.run(()->mKennysArm.rotateArm(0)));
    stick2.a()
      .onTrue(mKennysArm.run(()->mKennysArm.intake(-0.5)))
      .onFalse(mKennysArm.run(()->mKennysArm.intake(0)));
    stick2.b()
      .onTrue(mKennysArm.run(()->mKennysArm.intake(0.5)))
      .onFalse(mKennysArm.run(()->mKennysArm.intake(0)));

    //Carsen and Nickolas' AlgeMover - Player 2
      //Swifty Elevator 
    stick2.povUp() //POV == Dpad
      .onTrue(mElevator.run(()-> mElevator.setSpeed(-0.5)).until(()-> ElevatorHeight <= 2000))
      .onFalse(mElevator.run(()-> mElevator.setSpeed(0)));
    stick2.povDown()
      .onTrue(mElevator.run(()-> mElevator.setSpeed(0.5)).until(()-> ElevatorHeight >= 0))
      .onFalse(mElevator.run(()-> mElevator.setSpeed(0)));  
    /*if (stick2.povUp().getAsBoolean() == true  & ElevatorHeight <=2000) {
      ElevatorHeight += 0.02; 
    }
    if (stick2.povDown().getAsBoolean() == true & ElevatorHeight >=0);
    { ElevatorHeight -= 0.02;}*/

    //DRIVING
    drivetrain.setDefaultCommand(
            // Drivetrain will execute this command periodically
            drivetrain.applyRequest(() ->
                drive.withVelocityX(-(stick1.getLeftY())* MaxSpeed) // Drive forward with negative Y (forward)
                    .withVelocityY(-stick1.getLeftX() * MaxSpeed) // Drive left with negative X (left)
                    .withRotationalRate(-stick1.getRightX() * MaxAngularRate) // Drive counterclockwise with negative X (left)
            )
        );
        stick1.a().whileTrue(drivetrain.applyRequest(() -> brake));
        stick1.b().whileTrue(drivetrain.applyRequest(() ->
            point.withModuleDirection(new Rotation2d(-stick1.getLeftY(), -stick1.getLeftX()))
        ));

        stick1.pov(0).whileTrue(drivetrain.applyRequest(() ->
            forwardStraight.withVelocityX(0.5).withVelocityY(0))
        );
        stick1.pov(180).whileTrue(drivetrain.applyRequest(() ->
            forwardStraight.withVelocityX(-0.5).withVelocityY(0))
        );

        // Run SysId routines when holding back/start and X/Y.
        // Note that each routine should be run exactly once in a single log.
        stick1.back().and(stick1.y()).whileTrue(drivetrain.sysIdDynamic(Direction.kForward));
        stick1.back().and(stick1.x()).whileTrue(drivetrain.sysIdDynamic(Direction.kReverse));
        stick1.start().and(stick1.y()).whileTrue(drivetrain.sysIdQuasistatic(Direction.kForward));
        stick1.start().and(stick1.x()).whileTrue(drivetrain.sysIdQuasistatic(Direction.kReverse));

        // reset the field-centric heading on left bumper press
        stick1.leftBumper().onTrue(drivetrain.runOnce(() -> drivetrain.seedFieldCentric()));

        drivetrain.registerTelemetry(logger::telemeterize);


    if (DriverStation.isTeleopEnabled()){
      if ((stick1.getLeftTriggerAxis() > threshHoldTrig1 || stick1.getLeftTriggerAxis() < threshHoldTrig1) && !(throttle < 0))
		{
			throttle = (throttle - (stick1.getLeftTriggerAxis()/30));
		}

		if ((stick1.getRightTriggerAxis() > threshHoldTrig2 || stick1.getRightTriggerAxis() < threshHoldTrig2) && !(throttle > 1))
		{
			throttle = (throttle + (stick1.getRightTriggerAxis()/30));
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
    }
  }
  public void defineCommands(){

  }
  //Getting the Angle and Speed 
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
