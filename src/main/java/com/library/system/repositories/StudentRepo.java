package com.library.system.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.library.system.entities.Student;

@Repository
public interface StudentRepo extends JpaRepository<Student, Integer> {

}
