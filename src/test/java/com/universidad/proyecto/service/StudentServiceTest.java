package com.universidad.proyecto.service;

import com.universidad.proyecto.dto.StudentDTO;
import com.universidad.proyecto.exception.StudentNotFoundException;
import com.universidad.proyecto.model.Student;
import com.universidad.proyecto.repository.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class StudentServiceTest {

    @Mock
    private StudentRepository repository;

    @InjectMocks
    private StudentService service;

    private StudentDTO dto;

    @BeforeEach
    void setUp() {
        dto = new StudentDTO();
        dto.setFirstName("Ana");
        dto.setLastName("Gomez");
        dto.setEmail("ana@example.com");
        dto.setDateOfBirth("2003-05-12");
        dto.setProgram("Ingeniería");
        dto.setDocumentNumber("12345");
    }

    @Test
    void shouldCreateStudent() {
        Student saved = new Student();
        saved.setId(1L);
        when(repository.save(any(Student.class))).thenReturn(saved);

        Student result = service.create(dto);

        assertThat(result.getId()).isEqualTo(1L);
        verify(repository).save(any(Student.class));
    }

    @Test
    void shouldThrowWhenStudentNotFound() {
        when(repository.findById(99L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> service.findById(99L))
                .isInstanceOf(StudentNotFoundException.class);
    }

    @Test
    void shouldUpdateStudent() {
        Student existing = new Student();
        existing.setId(10L);
        existing.setDateOfBirth(LocalDate.now());

        when(repository.findById(10L)).thenReturn(Optional.of(existing));
        when(repository.save(existing)).thenReturn(existing);

        Student updated = service.update(10L, dto);

        assertThat(updated.getFirstName()).isEqualTo("Ana");
        verify(repository).save(existing);
    }
}

