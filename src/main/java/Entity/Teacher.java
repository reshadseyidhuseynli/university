package Entity;

import abstraction.Person;
import abstraction.Student;
import enamerations.AcademicRank;
import java.time.LocalDate;
import java.util.List;

public class Teacher extends Person {
    private final LocalDate birthdate;
    private AcademicRank academicRank;
    private Faculty faculty;
    private List<Student> students;
    private List<Lesson> lessons;

    public Teacher(int id, String name, String surname, LocalDate birthdate, AcademicRank academicRank, Faculty faculty) {
        super(id, name, surname);
        this.birthdate = birthdate;
        this.academicRank = academicRank;
        this.faculty = faculty;
    }

    public void setAcademicRank(AcademicRank academicRank) {
        this.academicRank = academicRank;
    }

    public void setDepartment(Faculty faculty) {
        this.faculty = faculty;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public void setLessons(List<Lesson> lessons) {
        this.lessons = lessons;
    }

    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
    }

    public void addStudent (Student student){
        students.add(student);
    }

    public void addLesson (Lesson lesson){
        lessons.add(lesson);
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public AcademicRank getAcademicRank() {
        return academicRank;
    }

    public Faculty getDepartment() {
        return faculty;
    }

    public List<Student> getStudents() {
        return students;
    }

    public List<Lesson> getLessons() {
        return lessons;
    }

    public Faculty getFaculty() {
        return faculty;
    }

    @Override
    public String toString() {
        return "Entity.Teacher{" +
                "id:" + getId() +
                ", " + getName() + " " + getSurname() +
                ", " + birthdate.getDayOfMonth() + " " + birthdate.getMonth().name() + " " + birthdate.getYear() +
                ", " + academicRank +
                ", department:" + faculty.getName() +
                '}';
    }
}
