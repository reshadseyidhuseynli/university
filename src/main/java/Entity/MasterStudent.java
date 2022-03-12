package Entity;

import abstraction.Student;
import enamerations.MasterGrade;

import java.time.LocalDate;

public class MasterStudent extends Student {
    MasterGrade masterGrade;

    public MasterStudent(int id, String name, String surname, LocalDate birthdate, Faculty faculty) {
        super(id, name, surname, birthdate, faculty);
        this.masterGrade = MasterGrade.MASTER_I;
    }

    public MasterGrade getMasterGrade() {
        return masterGrade;
    }

    public void setMasterGrade(MasterGrade masterGrade) {
        this.masterGrade = masterGrade;
    }
}
