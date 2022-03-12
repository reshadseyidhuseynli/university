package abstraction;

import java.time.LocalDate;
import Entity.Faculty;

public abstract class Student extends Person {
    private final LocalDate birthdate;
    private Faculty faculty;
    private boolean haveBachelorDiploma;
    private boolean haveMasterDiploma;

    protected Student(int id, String name, String surname, LocalDate birthdate, Faculty faculty) {
        super(id, name, surname);
        this.birthdate = birthdate;
        this.faculty = faculty;
    }


    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setDepartment(Faculty faculty) {
        this.faculty = faculty;
    }

    public Faculty getDepartment() {
        return faculty;
    }

    public boolean isHaveBachelorDiploma() {
        return haveBachelorDiploma;
    }

    public void setHaveBachelorDiploma(boolean haveBachelorDiploma) {
        this.haveBachelorDiploma = haveBachelorDiploma;
    }

    public boolean isHaveMasterDiploma() {
        return haveMasterDiploma;
    }

    public void setHaveMasterDiploma(boolean haveMasterDiploma) {
        this.haveMasterDiploma = haveMasterDiploma;
    }

    @Override
    public String toString() {
        return "Students{" +
                "id:" + getId() +
                ", " + getName() +
                " " + getSurname() +
                ", " + birthdate.getDayOfMonth() + " " + birthdate.getMonth().name() + " " + birthdate.getYear() +
                ", department:" + faculty.getName() +
                '}';
    }
}