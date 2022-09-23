package com.library.system.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.library.system.payloads.ApiRespone;
import com.library.system.payloads.StudentDto;
import com.library.system.service.StudentService;

@RestController
@RequestMapping("/student/")
public class StudentController {
	
	@Autowired
	private StudentService studentService;

	@PostMapping("registeration")
	public ResponseEntity<ApiRespone> studentRegister(@Valid @RequestBody StudentDto studentDto){
		return studentService.studentRegistration(studentDto);
	}
	
	@PutMapping("update/{studentId}")
	public ResponseEntity<ApiRespone> updateStudent(@Valid @RequestBody StudentDto studentDto ,@PathVariable int studentId){
		return studentService.updateStudent(studentDto, studentId);
	}
	
	@GetMapping()
	public ResponseEntity<ApiRespone> getAllStudents(){
		return studentService.getAllStudents();
	}
	@GetMapping("{studentId}")
	public ResponseEntity<ApiRespone> getStudentById(@PathVariable int studentId){
		return studentService.getStudentById(studentId);
	}
}
