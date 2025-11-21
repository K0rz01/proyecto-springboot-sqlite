package com.universidad.proyecto.controller;

import com.universidad.proyecto.dto.StudentDTO;
import com.universidad.proyecto.model.Student;
import com.universidad.proyecto.service.StudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class StudentController {
 private final StudentService service;

 @PostMapping
 public ResponseEntity<Student> create(@Valid @RequestBody StudentDTO dto){
  Student student = service.create(dto);
  return ResponseEntity.status(HttpStatus.CREATED).body(student);
 }

 @GetMapping
 public ResponseEntity<List<Student>> findAll(){
  return ResponseEntity.ok(service.findAll());
 }

 @GetMapping("/{id}")
 public ResponseEntity<Student> findById(@PathVariable Long id){
  return ResponseEntity.ok(service.findById(id));
 }

 @PutMapping("/{id}")
 public ResponseEntity<Student> update(@PathVariable Long id,@Valid @RequestBody StudentDTO dto){
  return ResponseEntity.ok(service.update(id,dto));
 }

 @DeleteMapping("/{id}")
 public ResponseEntity<Void> delete(@PathVariable Long id){
  service.delete(id);
  return ResponseEntity.noContent().build();
 }

 @PostMapping("/{id}/certificate")
 public ResponseEntity<byte[]> generateCertificate(@PathVariable Long id){
  byte[] pdf = service.generateCertificate(id);
  return ResponseEntity.ok()
   .header(HttpHeaders.CONTENT_DISPOSITION,"attachment; filename=\"student-"+id+"-certificate.pdf\"")
   .contentType(MediaType.APPLICATION_PDF)
   .body(pdf);
 }
}
