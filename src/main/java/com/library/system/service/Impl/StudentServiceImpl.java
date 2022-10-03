package com.library.system.service.Impl;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.library.system.entities.Student;
import com.library.system.exceptions.ResourceNotFoundException;
import com.library.system.payloads.ApiRespone;
import com.library.system.payloads.StudentDto;
import com.library.system.payloads.StudentsDataDto;
import com.library.system.repositories.StudentRepo;
import com.library.system.service.StudentService;
import com.library.system.util.constants.STUDENT;

@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	private StudentRepo studentRepo;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public ResponseEntity<ApiRespone> studentRegistration(StudentDto studentDto) {
		Student student = modelMapper.map(studentDto, Student.class);
		student.setResgistrationDate(LocalDateTime.now());
		Student registeredStudent = studentRepo.save(student);
		return ApiRespone.success(STUDENT.STUDENT_REGISTRATION.getCode(), STUDENT.STUDENT_REGISTRATION.getMessage(),
				modelMapper.map(registeredStudent, StudentDto.class));
	}

	@Override
	public ResponseEntity<ApiRespone> updateStudent(StudentDto studentDto, int studentId) {
		Student student = studentRepo.findById(studentId)
				.orElseThrow(() -> new ResourceNotFoundException("Student", "ID", studentId));
		student.setStudentName(studentDto.getStudentName());
		student.setCourse(studentDto.getCourse());
		student.setEmail(studentDto.getEmail());
		student.setMsisdn(studentDto.getMsisdn());
		student.setPassword(studentDto.getPassword());
		Student updatedStudent = studentRepo.save(student);
		return ApiRespone.success(STUDENT.STUDENT_UPDATE.getCode(), STUDENT.STUDENT_UPDATE.getMessage(),
				modelMapper.map(updatedStudent, StudentDto.class));
	}

	@Override
	public ResponseEntity<ApiRespone> getAllStudents() {
		List<Student> student = studentRepo.findAll();
		List<StudentsDataDto> getAllStudents = student.stream().map(getStudent -> modelMapper.map(getStudent, StudentsDataDto.class)).collect(Collectors.toList());
		return ApiRespone.success(STUDENT.STUDENT_LIST.getCode(), STUDENT.STUDENT_LIST.getMessage(), getAllStudents);
	}

	@Override
	public ResponseEntity<ApiRespone> getStudentById(int studentId) {
		Student student = studentRepo.findById(studentId)
				.orElseThrow(() -> new ResourceNotFoundException("Student", "ID", studentId));
		return ApiRespone.success(STUDENT.STUDENT_LIST.getCode(), STUDENT.STUDENT_LIST.getMessage(), modelMapper.map(student, StudentsDataDto.class));
	}

}
