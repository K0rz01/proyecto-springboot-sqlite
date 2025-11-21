# 🔧 Solución: Problema de Conexión con Base de Datos SQLite

## Problema Identificado

El proyecto tenía un desajuste entre:
- **Schema SQL**: Usaba nombres de columnas en `snake_case` (first_name, last_name)
- **Entidad Java**: Usaba nombres en `camelCase` (firstName, lastName) sin mapeo explícito

## Solución Aplicada

### 1. Mapeo Explícito en la Entidad Student

Se agregaron las anotaciones `@Column(name="...")` para mapear correctamente:

```java
@Column(name="first_name", nullable=false, length=50)
private String firstName;

@Column(name="last_name", nullable=false, length=50)
private String lastName;

@Column(name="date_of_birth")
private LocalDate dateOfBirth;

@Column(name="document_number", nullable=false, length=20)
private String documentNumber;
```

### 2. Configuración de application.properties

Se ajustó la configuración para evitar conflictos:

```properties
spring.jpa.hibernate.ddl-auto=update
spring.sql.init.mode=never
spring.jpa.defer-datasource-initialization=false
```

**Explicación:**
- `ddl-auto=update`: Hibernate crea/actualiza las tablas automáticamente
- `sql.init.mode=never`: Desactiva la ejecución de schema.sql (Hibernate lo hace)
- `defer-datasource-initialization=false`: Evita conflictos de inicialización

## Cómo Verificar que Funciona

### Paso 1: Eliminar Base de Datos Antigua (si existe)

```powershell
Remove-Item students.db -ErrorAction SilentlyContinue
```

### Paso 2: Ejecutar la Aplicación

```powershell
$env:JAVA_HOME='C:\Program Files\Eclipse Adoptium\jdk-17.0.17.10-hotspot'
$env:Path="$env:JAVA_HOME\bin;$env:Path"
.\mvnw.cmd spring-boot:run
```

### Paso 3: Verificar en los Logs

Deberías ver en la consola:
```
Hibernate: create table students (
    id integer primary key autoincrement,
    first_name text not null,
    last_name text not null,
    ...
)
```

### Paso 4: Crear un Estudiante

```powershell
curl -X POST http://localhost:8080/api/students `
  -H "Content-Type: application/json" `
  -d '{\"firstName\":\"Ana\",\"lastName\":\"Gomez\",\"email\":\"ana@email.com\",\"dateOfBirth\":\"2003-05-12\",\"program\":\"Ingenieria\",\"documentNumber\":\"123456\"}'
```

### Paso 5: Verificar la Base de Datos

```powershell
# Verificar que el archivo existe y tiene contenido
dir students.db

# Debe mostrar un tamaño mayor a 0 bytes
```

## Estructura de la Tabla Creada

Hibernate creará automáticamente:

```sql
CREATE TABLE students (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    first_name TEXT NOT NULL,
    last_name TEXT NOT NULL,
    email TEXT NOT NULL UNIQUE,
    date_of_birth TEXT,
    program TEXT NOT NULL,
    document_number TEXT NOT NULL
);
```

## Si Aún Hay Problemas

### Error: "Table 'students' doesn't exist"

1. Elimina `students.db`
2. Reinicia la aplicación
3. Hibernate creará la tabla automáticamente

### Error: "Column 'first_name' not found"

1. Verifica que la entidad tiene `@Column(name="first_name")`
2. Recompila: `.\mvnw.cmd clean compile`
3. Reinicia la aplicación

### Error: "Database locked"

1. Cierra todas las conexiones a la base de datos
2. Elimina `students.db`
3. Reinicia la aplicación

## Verificación Final

Después de crear un estudiante, verifica:

```powershell
# Listar estudiantes
curl http://localhost:8080/api/students

# Debe retornar el estudiante creado
```

Si funciona, la base de datos está correctamente configurada ✅

