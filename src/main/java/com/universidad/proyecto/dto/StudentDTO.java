package com.universidad.proyecto.dto;
import jakarta.validation.constraints.*; import lombok.Data;
@Data
public class StudentDTO {
 @NotBlank(message="El nombre es obligatorio")
 @Size(max=50,message="El nombre debe tener maximo 50 caracteres")
 private String firstName;

 @NotBlank(message="El apellido es obligatorio")
 @Size(max=50,message="El apellido debe tener maximo 50 caracteres")
 private String lastName;

 @NotBlank(message="El email es obligatorio")
 @Email(message="El email debe tener un formato valido")
 private String email;

 @NotBlank(message="La fecha de nacimiento es obligatoria (yyyy-MM-dd)")
 private String dateOfBirth;

 @NotBlank(message="El programa es obligatorio")
 @Size(max=80,message="El programa debe tener maximo 80 caracteres")
 private String program;

 @NotBlank(message="El numero de documento es obligatorio")
 @Size(max=20,message="El numero de documento debe tener maximo 20 caracteres")
 private String documentNumber;
}
