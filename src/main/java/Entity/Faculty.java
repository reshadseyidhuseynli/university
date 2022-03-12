package Entity;

import abstraction.Identifier;
import abstraction.Student;
import enamerations.AcademicRank;

import java.util.List;

public class Faculty extends Identifier {

    private Teacher head;
    private List<Lesson> lessons;
    private List<Teacher> teachers;
    private List<Student> students;

    public Faculty(int id, String name) {
        super(id,name);
    }

    public boolean setHead(Teacher head) {
        if (head.getAcademicRank()== AcademicRank.PROFESSOR) {
            this.head = head;
            return true;
        }
        return false;
    }

    public void addLesson(Lesson lesson){
        lessons.add(lesson);
    }

    public void addTeacher(Teacher teacher){
        teachers.add(teacher);
    }

    public  void addStudent(Student student){
        students.add(student);
    }

    public List<Lesson> getLessons() {
        return lessons;
    }

    public List<Teacher> getTeachers() {
        return teachers;
    }

    public List<Student> getStudents() {
        return students;
    }

    @Override
    public String toString() {
        return "Entity.Department{" +
                "id:" + getId() +
                ", " + getName() +
                ", lessons:" + lessons +
                ", teachers:" + teachers +
                '}';
    }
}
