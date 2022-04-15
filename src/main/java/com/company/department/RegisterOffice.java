package com.company.department;

import com.company.database.entity.Faculty;
import com.company.database.entity.Teacher;
import com.company.database.entity.Student;
import java.util.HashSet;
import java.util.Set;

public class RegisterOffice {
    public static final RegisterOffice registerOffice = new RegisterOffice();
    private final Set<Faculty> faculties = new HashSet<>();
    private final Set<Teacher> teachers = new HashSet<>();
    private final Set<Student> students = new HashSet<>();
    private final Set<Student> graduateStudents = new HashSet<>();

    private RegisterOffice() {
    }

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
        faculties.add(faculty);
    }

    public void registerTeacher(Teacher teacher) {
        teachers.add(teacher);
    }

    public void registerStudent(Student student) {
        students.add(student);
    }

//    public void passNextYear() {
//        for (abstraction.Student s : students) {
//            if (s instanceof Student) {
//                Student us = (Student) s;
//                Grade bg = us.getBachelorGrade();
//                us.setBachelorGrade(bg.increase());
//                if (us.getBachelorGrade() == Grade.GRADUATE_BACHELOR) {
//                    graduateStudents.add(us);
//                    students.remove(us);
//                }
//            } else if (s instanceof MasterStudent) {
//                MasterStudent ms = (MasterStudent) s;
//                MasterGrade mg = ms.getMasterGrade();
//                ms.setMasterGrade(mg.increase());
//                if (ms.getMasterGrade() == MasterGrade.GRADUATE_MASTER) {
//                    graduateStudents.add(ms);
//                    students.remove(ms);
//                }
//            }
//        }
//    }
}
