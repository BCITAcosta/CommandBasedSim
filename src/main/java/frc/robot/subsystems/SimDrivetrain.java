package frc.robot.subsystems;

import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.sim.SparkMaxSim;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;

import edu.wpi.first.math.system.plant.DCMotor;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class SimDrivetrain extends SubsystemBase{
    private static SimDrivetrain m_simDrive;

    private final SparkMax leftDriveLeader, rightDriveLeader;
    private final SparkMax leftDriveFollower, rightDriveFollower;

    private final SparkMaxConfig leftDriveLeaderConfig, rightDriveLeaderConfig;
    private final SparkMaxConfig leftDriveFollowerConfig, rightDriveFollowerConfig;

    private final SparkMaxSim leftDriveLeaderSim, rightDriveLeaderSim;
    private final SparkMaxSim leftDriveFollowerSim, rightDriveFollowerSim; 

    public SimDrivetrain(){
        leftDriveLeader = new SparkMax(10, MotorType.kBrushless);
        rightDriveLeader = new SparkMax(20, MotorType.kBrushless);

        leftDriveFollower = new SparkMax(11, MotorType.kBrushless);
        rightDriveFollower = new SparkMax(21, MotorType.kBrushless);

        leftDriveLeaderConfig = new SparkMaxConfig();
        rightDriveLeaderConfig = new SparkMaxConfig();

        leftDriveFollowerConfig = new SparkMaxConfig();
        rightDriveFollowerConfig = new SparkMaxConfig();

        leftDriveLeaderSim = new SparkMaxSim(leftDriveLeader, DCMotor.getNEO(1));
        rightDriveLeaderSim = new SparkMaxSim(leftDriveFollower, DCMotor.getNEO(1));

        leftDriveFollowerSim = new SparkMaxSim(leftDriveFollower, DCMotor.getNEO(1));
        rightDriveFollowerSim = new SparkMaxSim(rightDriveFollower, DCMotor.getNEO(1));
        configureLeftSparks();
        configureRightSparks();
    }

    public static SimDrivetrain getInstance(){
        if(m_simDrive == null){
            m_simDrive = new SimDrivetrain();
        }

        return m_simDrive;
    }

    @Override
    public void simulationPeriodic(){

    }
    
    private void configureLeftSparks(){
        leftDriveLeaderConfig.idleMode(IdleMode.kCoast);
        leftDriveLeaderConfig.smartCurrentLimit(60);
        leftDriveLeaderConfig.inverted(false);

        leftDriveFollowerConfig.apply(leftDriveLeaderConfig);
        leftDriveFollowerConfig.follow(leftDriveLeader);

        leftDriveLeader.configure(leftDriveLeaderConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
        leftDriveFollower.configure(leftDriveFollowerConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
    }

    private void configureRightSparks(){
        rightDriveLeaderConfig.idleMode(IdleMode.kCoast);
        rightDriveLeaderConfig.smartCurrentLimit(60);
        rightDriveLeaderConfig.inverted(true);

        rightDriveFollowerConfig.apply(rightDriveLeaderConfig);
        rightDriveFollowerConfig.follow(rightDriveLeader);

        rightDriveLeader.configure(rightDriveLeaderConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
        rightDriveFollower.configure(rightDriveFollowerConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
    }
}
