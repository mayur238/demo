package com.example.csv.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.csv.entity.Student;

import jakarta.transaction.Transactional;

@Repository
public interface StudentRepo extends JpaRepository<Student, Integer> {

	@Transactional
	@Modifying
	@Query(value = "DELETE FROM students WHERE id = ?", nativeQuery = true)
	public int deleteStudentById(int id);
}
