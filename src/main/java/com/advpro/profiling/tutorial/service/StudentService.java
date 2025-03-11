package com.advpro.profiling.tutorial.service;

import com.advpro.profiling.tutorial.model.Student;
import com.advpro.profiling.tutorial.model.StudentCourse;
import com.advpro.profiling.tutorial.repository.StudentCourseRepository;
import com.advpro.profiling.tutorial.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author muhammad.khadafi
 */
@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private StudentCourseRepository studentCourseRepository;

    public List<StudentCourse> getAllStudentsWithCourses() {
        List<Student> students = studentRepository.findAll();
        java.util.Map<Long, Student> studentMap = new java.util.HashMap<>();
        for (Student student : students) {
            studentMap.put(student.getId(), student);
        }
        List<StudentCourse> allStudentCourses = studentCourseRepository.findAll();
        List<StudentCourse> studentCourses = new ArrayList<>();
        for (StudentCourse sc : allStudentCourses) {
            StudentCourse studentCourse = new StudentCourse();
            studentCourse.setStudent(studentMap.get(sc.getStudent().getId()));
            studentCourse.setCourse(sc.getCourse());
            studentCourses.add(studentCourse);
        }
        return studentCourses;
    }

    public Optional<Student> findStudentWithHighestGpa() {
        return studentRepository.findTopByOrderByGpaDesc();
    }

    public String joinStudentNames() {
        List<Student> students = studentRepository.findAll();
        String result = "";
        for (Student student : students) {
            result += student.getName() + ", ";
        }
        return result.substring(0, result.length() - 2);
    }
}

