package com.universidad.proyecto.repository;
import com.universidad.proyecto.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student,Long>{}
