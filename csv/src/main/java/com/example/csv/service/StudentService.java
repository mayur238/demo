package com.example.csv.service;

import java.util.List;

import com.example.csv.entity.Student;

public interface StudentService {

	List<Student> getAllStudent();

	Student addStudent(Student student);

	Student updateStudent(int id, Student updatedStudent);

	boolean deleteStudentById(int id);

	Student getStudentById(int id);

	List<Student> readCsv();
}
