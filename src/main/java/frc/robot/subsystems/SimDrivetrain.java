package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class SimDrivetrain extends SubsystemBase{
    private static SimDrivetrain m_simDrive;

    public SimDrivetrain(){

    }

    public static SimDrivetrain getInstance(){
        if(m_simDrive == null){
            m_simDrive = new SimDrivetrain();
        }

        return m_simDrive;
    }
    
}
