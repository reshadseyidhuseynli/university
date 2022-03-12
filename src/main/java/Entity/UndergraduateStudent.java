package Entity;

import abstraction.Student;
import enamerations.BachelorGrade;
import java.time.LocalDate;

public class UndergraduateStudent extends Student {
    private BachelorGrade bachelorGrade;

    public UndergraduateStudent(int id, String name, String surname, LocalDate birthdate, Faculty faculty) {
        super(id, name, surname, birthdate, faculty);
        this.bachelorGrade = BachelorGrade.BACHELOR_I;
    }

    public void setBachelorGrade(BachelorGrade bachelorGrade) {
        this.bachelorGrade = bachelorGrade;
    }

    public BachelorGrade getBachelorGrade() {
        return bachelorGrade;
    }
}
