# Proyecto Spring Boot - Gestión de Estudiantes

Servicio REST con Spring Boot 3 / Java 17 para administrar estudiantes usando SQLite.

## 📚 Documentación

- **[GUIA_EJECUCION.md](GUIA_EJECUCION.md)** - Guía completa paso a paso para ejecutar y demostrar el proyecto
- **[SCRIPT_DEMOSTRACION.ps1](SCRIPT_DEMOSTRACION.ps1)** - Script automatizado para demostración rápida

## Requisitos

- Java 17
- Maven 3.9+ (o usar `.\mvnw.cmd`)
- SQLite (driver incluido)

## Ejecución Rápida

### Windows (PowerShell)
```powershell
# Configurar JAVA_HOME si es necesario
$env:JAVA_HOME='C:\Program Files\Eclipse Adoptium\jdk-17.0.17.10-hotspot'
$env:Path="$env:JAVA_HOME\bin;$env:Path"

# Ejecutar con Maven Wrapper
.\mvnw.cmd spring-boot:run
```

### Linux/Mac
```bash
./mvnw spring-boot:run
```

La API quedará disponible en `http://localhost:8080`.

**💡 Para una guía detallada, consulta [GUIA_EJECUCION.md](GUIA_EJECUCION.md)**

## Endpoints principales

| Método | Ruta | Descripción |
| --- | --- | --- |
| POST | `/api/students` | Crear estudiante |
| GET | `/api/students` | Listar estudiantes |
| GET | `/api/students/{id}` | Buscar por id |
| PUT | `/api/students/{id}` | Actualizar |
| DELETE | `/api/students/{id}` | Eliminar |
| POST | `/api/students/{id}/certificate` | Generar certificado PDF |

## Ejemplos curl

### Crear estudiante
```powershell
curl -X POST http://localhost:8080/api/students `
  -H "Content-Type: application/json" `
  -d '{\"firstName\":\"Ana\",\"lastName\":\"Gomez\",\"email\":\"ana@example.com\",\"dateOfBirth\":\"2003-05-12\",\"program\":\"Ingeniería de Sistemas\",\"documentNumber\":\"123456\"}'
```

### Generar certificado PDF
```powershell
curl -X POST http://localhost:8080/api/students/1/certificate --output certificado.pdf
```

**📖 Más ejemplos en [GUIA_EJECUCION.md](GUIA_EJECUCION.md)**

## Pruebas

```powershell
# Windows
.\mvnw.cmd test

# Linux/Mac
./mvnw test
```

**Resultado esperado:** 5 pruebas, todas pasando ✅

## Persistencia

- Base SQLite `students.db`.
- Script inicial: `src/main/resources/schema.sql`.
- Propiedades configuradas en `src/main/resources/application.properties`.

## Manejo de errores

Respuestas JSON uniformes con `timestamp`, `status`, `error`, `message`, `path`.

## Diagrama y documentación

- `docs/diagrama-clases.png` - Diagrama UML de clases
- `docs/diagrama-clases.puml` - Código fuente del diagrama
- `docs/postman-collection.json` - Colección de Postman con todos los endpoints
- `GUIA_EJECUCION.md` - Guía completa de ejecución y demostración

## Flujo Git recomendado

1. `develop`: desarrollo principal.
2. `feature/*`: trabajo de cada requisito.
3. `test`: pruebas integradas.
4. `production`: versión estable.

Ejemplo:

```bash
git checkout -b feature/crear-student develop
git commit -m "feat: crear endpoint POST /api/students"
git push origin feature/crear-student
```

