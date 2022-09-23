package com.library.system.service;

import org.springframework.http.ResponseEntity;

import com.library.system.payloads.ApiRespone;
import com.library.system.payloads.StudentDto;

public interface StudentService {

	//Student Registration
	
	ResponseEntity<ApiRespone> studentRegistration(StudentDto studentDto);
	
	//Update Student Information
	ResponseEntity<ApiRespone> updateStudent(StudentDto studentDto, int studentId);
	
	//getAll Students
	ResponseEntity<ApiRespone> getAllStudents();
	
	//Get student by id
	ResponseEntity<ApiRespone> getStudentById(int studentId);
	
}
