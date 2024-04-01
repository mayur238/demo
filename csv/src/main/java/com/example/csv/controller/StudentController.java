package com.example.csv.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.csv.entity.Student;
import com.example.csv.service.StudentService;

@RestController
@RequestMapping("/")
public class StudentController {

	// DB name tts
	@Autowired
	private StudentService studentService;

	@PostMapping("/add")
	public ResponseEntity<Object> getStudent(@RequestBody Student student) {

		Student student2 = studentService.addStudent(student);
		System.out.println(student.toString());
		if (student2 == null) {
			return new ResponseEntity<Object>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<Object>(student2, HttpStatus.OK);
		}

	}

	// get all student
	@GetMapping("/all")
	public ResponseEntity<Object> getStudent() {

		List<Student> list = studentService.getAllStudent();
		System.out.println(list);
		if (list == null) {
			return new ResponseEntity<Object>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<Object>(list, HttpStatus.OK);
		}

	}

	// read csv and dump data in DB
	@GetMapping("/readcsv")
	public List<Student> readCsv() {

		return studentService.readCsv();
	}

	// Update a student
	@PutMapping("/update/{id}")
	public ResponseEntity<Object> updateStudent(@PathVariable int id, @RequestBody Student updatedStudent) {

		Student student2 = studentService.updateStudent(id, updatedStudent);

		if (student2 == null) {
			return new ResponseEntity<Object>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<Object>(student2, HttpStatus.OK);
		}

	}

	// Delete a student
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Object> deleteStudent(@PathVariable int id) {

		boolean isDelete = studentService.deleteStudentById(id);

		if (isDelete) {
			return new ResponseEntity<Object>(HttpStatus.OK);

		} else {
			return new ResponseEntity<Object>(HttpStatus.NOT_FOUND);
		}

	}

}
