package com.company.department;

import com.company.database.entity.Faculty;
import com.company.database.entity.Teacher;
import com.company.database.entity.Student;
import com.company.database.service.impl.FacultyServiceImpl;
import com.company.database.service.impl.TeacherServiceImpl;
import com.company.database.service.inter.FacultyService;
import com.company.database.service.inter.StudentService;
import com.company.database.service.inter.TeacherService;
import com.company.util.enamerations.Grade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

@Component
public class RegisterOffice {
    public static final RegisterOffice of = new RegisterOffice();

    @Autowired
    private FacultyService facultyService;
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private StudentService studentService;

    private final List<Faculty> faculties = new FacultyServiceImpl().getAll();
    private final List<Teacher> teachers = new TeacherServiceImpl().getAll();
    private final List<Student> students;
    private final List<Student> graduateStudents;
    {
        students = new ArrayList<>();
        graduateStudents = new ArrayList<>();
        students.addAll(studentService.getAll());
        for (Student s : students){
            if (s.getGrade() == Grade.GRADUATE_BACHELOR || s.getGrade() == Grade.GRADUATE_MASTER){
                students.remove(s);
                graduateStudents.add(s);
            }
        }
    }

    private RegisterOffice() {}

    public Faculty[] getFaculties() {
        return faculties.toArray(new Faculty[faculties.size()]);
    }

    public Teacher[] getTeachers() {
        return teachers.toArray(new Teacher[teachers.size()]);
    }

    public Student[] getStudents() {
        return students.toArray(new Student[students.size()]);
    }

    public Student[] getGraduateStudents() {
        return graduateStudents.toArray(new Student[graduateStudents.size()]);
    }

    public void registerFaculty(Faculty faculty) {
        facultyService.addOrUpdate(faculty);
        faculties.add(faculty);
    }

    public void registerTeacher(Teacher teacher) {
        teacherService.addOrUpdate(teacher);
        teachers.add(teacher);
    }

    public void registerStudent(Student student) {
        studentService.addOrUpdate(student);
        students.add(student);
    }

    public void passNextYear() {
        for (Student s : students){
            s.setGrade(s.getGrade().passNextYear());
        }
    }
}
