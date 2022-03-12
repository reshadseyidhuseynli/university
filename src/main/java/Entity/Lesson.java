package Entity;

import abstraction.Identifier;

import java.util.List;

public class Lesson extends Identifier {
    private final Faculty faculty;
    private List<Teacher> teachers;

    public Lesson(int id, String name, Faculty faculty) {
        super(id, name);
        this.faculty = faculty;
    }

    public void addTeacher(Teacher teacher){
        teachers.add(teacher);
    }


    public Faculty getDepartment() {
        return faculty;
    }

    public List<Teacher> getTeachers() {
        return teachers;
    }

    @Override
    public String toString() {
        return "Entity.Lesson{" +
                "id:" + getId() +
                ", " + getName() +
                ", department:" + faculty.getName() +
                '}';
    }
}
