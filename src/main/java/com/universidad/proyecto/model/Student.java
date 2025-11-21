package com.universidad.proyecto.model;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Data
@Entity
@Table(name="students")
public class Student {
 @Id
 @GeneratedValue(strategy=GenerationType.IDENTITY)
 private Long id;

 @Column(name="first_name", nullable=false, length=50)
 private String firstName;

 @Column(name="last_name", nullable=false, length=50)
 private String lastName;

 @Column(nullable=false, unique=true, length=120)
 private String email;

 @Column(name="date_of_birth")
 private LocalDate dateOfBirth;

 @Column(nullable=false, length=80)
 private String program;

 @Column(name="document_number", nullable=false, length=20)
 private String documentNumber;
}
