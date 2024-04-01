package com.example.csv.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.csv.entity.Student;
import com.example.csv.repo.StudentRepo;
import com.example.csv.service.StudentService;
import com.opencsv.CSVWriter;

@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	private StudentRepo studentRepo;

	File file = new File("C:\\Kanchan\\demo.csv");

	@Override
	public List<Student> getAllStudent() {

		return studentRepo.findAll();
	}

	@Override
	public Student addStudent(Student student) {
		// TODO Auto-generated method stub

		Student addedStudent = null;
		FileWriter outputfile = null;
		try {
			addedStudent = studentRepo.save(student);

			outputfile = new FileWriter(file, true);

			String[] data = { String.valueOf(addedStudent.getId()), addedStudent.getName() };

			CSVWriter writer = new CSVWriter(outputfile);

			writer.writeNext(data);

			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return addedStudent;

	}

	// Update a student
	@Override
	public Student updateStudent(int id, Student student) {
		Student updatedStudent = null;

		student.setId(id);
		updatedStudent = studentRepo.save(student);

		return updatedStudent;
	}

	// Delete a student by ID
	@Override
	public boolean deleteStudentById(int id) {
		int count = studentRepo.deleteStudentById(id);
		if (count > 0) {
			return true;
		}
		return false;
	}

	@Override
	public Student getStudentById(int id) {
		Student studentOptional = studentRepo.getById(id);
		return studentOptional;
	}

	@Override
	public List<Student> readCsv() {
		String line = "";
		String splitBy = ",";

		List<Student> listStudent = new ArrayList<Student>();
		try {

			BufferedReader br = new BufferedReader(new FileReader("C:\\Kanchan\\demo.csv"));
			while ((line = br.readLine()) != null) {
				String[] data = line.split(splitBy);
				System.out.println("Student [id=" + data[0] + ", name=" + data[1] + "]");

				Student studentData = new Student(Integer.parseInt(data[0]), data[1]);
				System.out.println("hey" + studentData.toString());
				studentRepo.save(studentData);

				listStudent.add(studentData);

			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return listStudent;
	}

}
