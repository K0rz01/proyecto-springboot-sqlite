package com.universidad.proyecto.service;

import com.universidad.proyecto.dto.StudentDTO;
import com.universidad.proyecto.exception.StudentNotFoundException;
import com.universidad.proyecto.model.Student;
import com.universidad.proyecto.repository.StudentRepository;
import com.universidad.proyecto.util.AppConstants;
import com.universidad.proyecto.util.PDFGeneratorUtil;
import com.universidad.proyecto.util.ValidationUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StudentService {
 private final StudentRepository repository;

 @Transactional
 public Student create(StudentDTO dto){
  Student student = new Student();
  copyValues(student, dto);
  return repository.save(student);
 }

 @Transactional(readOnly = true)
 public List<Student> findAll(){
  Comparator<String> stringComparator = Comparator.nullsLast(String.CASE_INSENSITIVE_ORDER);
  return repository.findAll()
   .stream()
   .sorted(Comparator.comparing(Student::getLastName, stringComparator)
    .thenComparing(Student::getFirstName, stringComparator))
   .collect(Collectors.toList());
 }

 @Transactional(readOnly = true)
 public Student findById(Long id){
  return repository.findById(id)
   .orElseThrow(() -> new StudentNotFoundException(AppConstants.STUDENT_NOT_FOUND.formatted(id)));
 }

 @Transactional
 public Student update(Long id,StudentDTO dto){
  Student student = findById(id);
  copyValues(student, dto);
  return repository.save(student);
 }

 @Transactional
 public void delete(Long id){
  Student student = findById(id);
  repository.delete(student);
 }

 @Transactional(readOnly = true)
 public byte[] generateCertificate(Long id){
  Student student = findById(id);
  return PDFGeneratorUtil.buildCertificate(student);
 }

 private void copyValues(Student student, StudentDTO dto){
  student.setFirstName(dto.getFirstName());
  student.setLastName(dto.getLastName());
  student.setEmail(dto.getEmail());
  student.setProgram(dto.getProgram());
  student.setDocumentNumber(dto.getDocumentNumber());
  student.setDateOfBirth(ValidationUtil.parseIsoDate(dto.getDateOfBirth()));
 }
}
