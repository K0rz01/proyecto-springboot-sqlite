# 📘 Guía Completa de Ejecución y Demostración del Proyecto

## 🎯 Índice
1. [Requisitos Previos](#requisitos-previos)
2. [Preparación del Entorno](#preparación-del-entorno)
3. [Ejecutar la Aplicación](#ejecutar-la-aplicación)
4. [Probar los Endpoints](#probar-los-endpoints)
5. [Generar Certificado PDF](#generar-certificado-pdf)
6. [Verificar Base de Datos](#verificar-base-de-datos)
7. [Ejecutar Pruebas Unitarias](#ejecutar-pruebas-unitarias)
8. [Demostración Completa](#demostración-completa)

---

## 📋 Requisitos Previos

### Verificar Instalaciones

**1. Java 17:**
```powershell
java -version
```
Debe mostrar: `openjdk version "17.x.x"` o similar

**2. Maven (opcional, se usa el wrapper):**
```powershell
.\mvnw.cmd --version
```

**3. Herramientas opcionales:**
- **Postman** (recomendado para pruebas visuales)
- **SQLite Browser** (para ver la base de datos)
- **cURL** (incluido en Windows 10/11)

---

## 🔧 Preparación del Entorno

### Paso 1: Navegar al Directorio del Proyecto

```powershell
cd C:\Users\Fix\Desktop\proyecto_springboot_sqlite
```

### Paso 2: Verificar Estructura del Proyecto

```powershell
# Ver estructura principal
dir src\main\java\com\universidad\proyecto

# Verificar que existe el archivo de configuración
dir src\main\resources\application.properties
```

### Paso 3: Limpiar y Compilar (Opcional)

```powershell
# Configurar JAVA_HOME si es necesario
$env:JAVA_HOME='C:\Program Files\Eclipse Adoptium\jdk-17.0.17.10-hotspot'
$env:Path="$env:JAVA_HOME\bin;$env:Path"

# Limpiar y compilar
.\mvnw.cmd clean compile
```

---

## 🚀 Ejecutar la Aplicación

### Opción 1: Con Maven Wrapper (Recomendado)

```powershell
# Asegurar que JAVA_HOME está configurado
$env:JAVA_HOME='C:\Program Files\Eclipse Adoptium\jdk-17.0.17.10-hotspot'
$env:Path="$env:JAVA_HOME\bin;$env:Path"

# Ejecutar la aplicación
.\mvnw.cmd spring-boot:run
```

### Opción 2: Ejecutar el JAR Compilado

```powershell
# Primero compilar el proyecto completo
.\mvnw.cmd clean package

# Luego ejecutar el JAR
java -jar target\proyecto-1.0.0.jar
```

### ✅ Verificar que la Aplicación Está Corriendo

Deberías ver en la consola:
```
Started ProyectoApplication in X.XXX seconds
```

Y la aplicación estará disponible en: **http://localhost:8080**

**⚠️ IMPORTANTE:** Mantén esta ventana de terminal abierta mientras pruebas los endpoints.

---

## 🧪 Probar los Endpoints

### 1️⃣ Crear un Estudiante (POST)

**Con cURL:**
```powershell
curl -X POST http://localhost:8080/api/students `
  -H "Content-Type: application/json" `
  -d '{\"firstName\":\"Ana\",\"lastName\":\"Gomez\",\"email\":\"ana.gomez@email.com\",\"dateOfBirth\":\"2003-05-12\",\"program\":\"Ingenieria de Sistemas\",\"documentNumber\":\"123456789\"}'
```

**Respuesta esperada (201 Created):**
```json
{
  "id": 1,
  "firstName": "Ana",
  "lastName": "Gomez",
  "email": "ana.gomez@email.com",
  "dateOfBirth": "2003-05-12",
  "program": "Ingenieria de Sistemas",
  "documentNumber": "123456789"
}
```

**Con Postman:**
1. Método: `POST`
2. URL: `http://localhost:8080/api/students`
3. Headers: `Content-Type: application/json`
4. Body (raw JSON):
```json
{
  "firstName": "Ana",
  "lastName": "Gomez",
  "email": "ana.gomez@email.com",
  "dateOfBirth": "2003-05-12",
  "program": "Ingenieria de Sistemas",
  "documentNumber": "123456789"
}
```

### 2️⃣ Listar Todos los Estudiantes (GET)

**Con cURL:**
```powershell
curl http://localhost:8080/api/students
```

**Con Postman:**
1. Método: `GET`
2. URL: `http://localhost:8080/api/students`
3. Send

**Respuesta esperada (200 OK):**
```json
[
  {
    "id": 1,
    "firstName": "Ana",
    "lastName": "Gomez",
    "email": "ana.gomez@email.com",
    "dateOfBirth": "2003-05-12",
    "program": "Ingenieria de Sistemas",
    "documentNumber": "123456789"
  }
]
```

### 3️⃣ Buscar Estudiante por ID (GET)

**Con cURL:**
```powershell
curl http://localhost:8080/api/students/1
```

**Con Postman:**
1. Método: `GET`
2. URL: `http://localhost:8080/api/students/1`
3. Send

### 4️⃣ Actualizar Estudiante (PUT)

**Con cURL:**
```powershell
curl -X PUT http://localhost:8080/api/students/1 `
  -H "Content-Type: application/json" `
  -d '{\"firstName\":\"Ana Maria\",\"lastName\":\"Gomez\",\"email\":\"ana.gomez@email.com\",\"dateOfBirth\":\"2003-05-12\",\"program\":\"Ingenieria de Sistemas\",\"documentNumber\":\"123456789\"}'
```

**Con Postman:**
1. Método: `PUT`
2. URL: `http://localhost:8080/api/students/1`
3. Headers: `Content-Type: application/json`
4. Body (raw JSON):
```json
{
  "firstName": "Ana Maria",
  "lastName": "Gomez",
  "email": "ana.gomez@email.com",
  "dateOfBirth": "2003-05-12",
  "program": "Ingenieria de Sistemas",
  "documentNumber": "123456789"
}
```

### 5️⃣ Eliminar Estudiante (DELETE)

**Con cURL:**
```powershell
curl -X DELETE http://localhost:8080/api/students/1
```

**Con Postman:**
1. Método: `DELETE`
2. URL: `http://localhost:8080/api/students/1`
3. Send

**Respuesta esperada:** 204 No Content (sin cuerpo)

### 6️⃣ Probar Validaciones (Errores)

**Email inválido:**
```powershell
curl -X POST http://localhost:8080/api/students `
  -H "Content-Type: application/json" `
  -d '{\"firstName\":\"Test\",\"lastName\":\"User\",\"email\":\"email-invalido\",\"dateOfBirth\":\"2003-05-12\",\"program\":\"Test\",\"documentNumber\":\"123\"}'
```

**Respuesta esperada (400 Bad Request):**
```json
{
  "timestamp": "2025-11-21T12:00:00",
  "status": 400,
  "error": "Bad Request",
  "message": "El email debe tener un formato valido",
  "path": "/api/students"
}
```

**Estudiante no encontrado:**
```powershell
curl http://localhost:8080/api/students/999
```

**Respuesta esperada (404 Not Found):**
```json
{
  "timestamp": "2025-11-21T12:00:00",
  "status": 404,
  "error": "Not Found",
  "message": "Estudiante no encontrado con id: 999",
  "path": "/api/students/999"
}
```

---

## 📄 Generar Certificado PDF

### Paso 1: Crear un Estudiante (si no existe)

```powershell
curl -X POST http://localhost:8080/api/students `
  -H "Content-Type: application/json" `
  -d '{\"firstName\":\"Ana\",\"lastName\":\"Gomez\",\"email\":\"ana.gomez@email.com\",\"dateOfBirth\":\"2003-05-12\",\"program\":\"Ingenieria de Sistemas\",\"documentNumber\":\"123456789\"}'
```

**Anota el ID del estudiante creado** (ejemplo: `id: 1`)

### Paso 2: Generar el Certificado

**Con cURL:**
```powershell
curl -X POST http://localhost:8080/api/students/1/certificate --output certificado.pdf
```

**Con Postman:**
1. Método: `POST`
2. URL: `http://localhost:8080/api/students/1/certificate`
3. Headers: `Accept: application/pdf`
4. Send and Download

### Paso 3: Verificar el PDF

Abre el archivo `certificado.pdf` en tu navegador o lector de PDF. Debe contener:
- Título: "Certificado de Estudios"
- Nombre del estudiante
- Programa
- Documento
- Fecha de nacimiento

---

## 💾 Verificar Base de Datos

### Opción 1: Con SQLite Command Line

```powershell
# Instalar SQLite si no está disponible
# O usar el driver JDBC directamente

# Verificar que existe el archivo
dir students.db

# Abrir con SQLite (si está instalado)
sqlite3 students.db

# Dentro de SQLite:
.tables
SELECT * FROM students;
.quit
```

### Opción 2: Con SQLite Browser (Recomendado)

1. Descargar **DB Browser for SQLite** desde: https://sqlitebrowser.org/
2. Abrir el archivo `students.db` en la raíz del proyecto
3. Ir a la pestaña "Browse Data"
4. Seleccionar la tabla `students`
5. Ver los registros creados

### Opción 3: Verificar desde la Aplicación

Los logs de Spring Boot mostrarán las consultas SQL si `spring.jpa.show-sql=true` está activo.

---

## 🧪 Ejecutar Pruebas Unitarias

### Ejecutar Todas las Pruebas

```powershell
# Configurar JAVA_HOME
$env:JAVA_HOME='C:\Program Files\Eclipse Adoptium\jdk-17.0.17.10-hotspot'
$env:Path="$env:JAVA_HOME\bin;$env:Path"

# Ejecutar pruebas
.\mvnw.cmd test
```

### Resultado Esperado

```
[INFO] Tests run: 5, Failures: 0, Errors: 0, Skipped: 0
[INFO] BUILD SUCCESS
```

### Ver Reportes Detallados

```powershell
# Los reportes se generan en:
dir target\surefire-reports
```

---

## 🎬 Demostración Completa (Script de Sustentación)

### Secuencia Recomendada para la Presentación:

**1. Mostrar la Estructura del Proyecto**
```powershell
tree /F src\main\java\com\universidad\proyecto
```

**2. Ejecutar la Aplicación**
```powershell
.\mvnw.cmd spring-boot:run
```

**3. Crear Varios Estudiantes**
```powershell
# Estudiante 1
curl -X POST http://localhost:8080/api/students -H "Content-Type: application/json" -d '{\"firstName\":\"Ana\",\"lastName\":\"Gomez\",\"email\":\"ana@email.com\",\"dateOfBirth\":\"2003-05-12\",\"program\":\"Ingenieria\",\"documentNumber\":\"123456\"}'

# Estudiante 2
curl -X POST http://localhost:8080/api/students -H "Content-Type: application/json" -d '{\"firstName\":\"Carlos\",\"lastName\":\"Rodriguez\",\"email\":\"carlos@email.com\",\"dateOfBirth\":\"2002-08-20\",\"program\":\"Medicina\",\"documentNumber\":\"789012\"}'

# Estudiante 3
curl -X POST http://localhost:8080/api/students -H "Content-Type: application/json" -d '{\"firstName\":\"Maria\",\"lastName\":\"Lopez\",\"email\":\"maria@email.com\",\"dateOfBirth\":\"2004-03-15\",\"program\":\"Derecho\",\"documentNumber\":\"345678\"}'
```

**4. Listar Estudiantes (mostrar ordenamiento con Stream API)**
```powershell
curl http://localhost:8080/api/students
```
*Nota: Deben aparecer ordenados por apellido y luego por nombre*

**5. Buscar un Estudiante Específico**
```powershell
curl http://localhost:8080/api/students/1
```

**6. Actualizar un Estudiante**
```powershell
curl -X PUT http://localhost:8080/api/students/1 -H "Content-Type: application/json" -d '{\"firstName\":\"Ana Maria\",\"lastName\":\"Gomez\",\"email\":\"ana@email.com\",\"dateOfBirth\":\"2003-05-12\",\"program\":\"Ingenieria de Sistemas\",\"documentNumber\":\"123456\"}'
```

**7. Generar Certificado PDF**
```powershell
curl -X POST http://localhost:8080/api/students/1/certificate --output certificado-ana.pdf
```
*Abrir el PDF generado para mostrar*

**8. Probar Manejo de Errores**
```powershell
# Estudiante no encontrado
curl http://localhost:8080/api/students/999

# Email inválido
curl -X POST http://localhost:8080/api/students -H "Content-Type: application/json" -d '{\"firstName\":\"Test\",\"lastName\":\"User\",\"email\":\"email-malo\",\"dateOfBirth\":\"2003-05-12\",\"program\":\"Test\",\"documentNumber\":\"123\"}'
```

**9. Ejecutar Pruebas Unitarias**
```powershell
.\mvnw.cmd test
```

**10. Mostrar Diagrama de Clases**
- Abrir `docs\diagrama-clases.png` en un visor de imágenes

---

## 📝 Checklist para la Sustentación

- [ ] ✅ Aplicación ejecutándose correctamente
- [ ] ✅ Endpoint POST (crear estudiante) funcionando
- [ ] ✅ Endpoint GET (listar todos) funcionando y ordenado
- [ ] ✅ Endpoint GET por ID funcionando
- [ ] ✅ Endpoint PUT (actualizar) funcionando
- [ ] ✅ Endpoint DELETE funcionando
- [ ] ✅ Endpoint de certificado PDF generando archivo
- [ ] ✅ Validaciones funcionando (email, campos obligatorios)
- [ ] ✅ Manejo de errores mostrando JSON estructurado
- [ ] ✅ Pruebas unitarias ejecutándose correctamente
- [ ] ✅ Base de datos SQLite con registros
- [ ] ✅ Diagrama de clases visible

---

## 🆘 Solución de Problemas Comunes

### Error: "java no se reconoce como comando"
```powershell
# Configurar JAVA_HOME
$env:JAVA_HOME='C:\Program Files\Eclipse Adoptium\jdk-17.0.17.10-hotspot'
$env:Path="$env:JAVA_HOME\bin;$env:Path"
```

### Error: "Puerto 8080 ya está en uso"
```powershell
# Cambiar puerto en application.properties
# Agregar: server.port=8081
```

### Error: "No se puede conectar a la base de datos" o "Table 'students' doesn't exist"

**Solución:**
```powershell
# 1. Eliminar la base de datos antigua (si existe)
Remove-Item students.db -ErrorAction SilentlyContinue

# 2. Limpiar y recompilar
.\mvnw.cmd clean compile

# 3. Ejecutar la aplicación (Hibernate creará la tabla automáticamente)
.\mvnw.cmd spring-boot:run
```

**Nota:** La base de datos se crea automáticamente al iniciar la aplicación. Hibernate crea las tablas usando `ddl-auto=update`.

### Error: "Column 'first_name' not found"
```powershell
# Verificar que la entidad Student tiene los mapeos correctos
# Debe tener @Column(name="first_name") para cada campo

# Si persiste, limpiar y recompilar:
.\mvnw.cmd clean compile
.\mvnw.cmd spring-boot:run
```

### Error: "Database locked"
```powershell
# 1. Cerrar todas las conexiones a la base de datos
# 2. Detener la aplicación Spring Boot
# 3. Eliminar students.db
Remove-Item students.db -ErrorAction SilentlyContinue
# 4. Reiniciar la aplicación
```

### La aplicación no inicia
```powershell
# Limpiar y recompilar
.\mvnw.cmd clean package
.\mvnw.cmd spring-boot:run
```

**📖 Para más detalles sobre problemas de base de datos, consulta [SOLUCION_BASE_DATOS.md](SOLUCION_BASE_DATOS.md)**

---

## 📚 Recursos Adicionales

- **README.md**: Documentación principal del proyecto
- **docs/postman-collection.json**: Colección de Postman con todos los endpoints
- **docs/diagrama-clases.png**: Diagrama UML del proyecto

---

## ✨ Tips para una Buena Presentación

1. **Prepara datos de ejemplo** antes de la sustentación
2. **Ten Postman abierto** con la colección importada
3. **Muestra el código** de las pruebas unitarias
4. **Explica el uso de Stream API** en el método `findAll()`
5. **Destaca el manejo de errores** con ejemplos
6. **Muestra el certificado PDF** generado
7. **Explica la arquitectura MVC** del proyecto

---

**¡Éxito en tu sustentación! 🎓**

