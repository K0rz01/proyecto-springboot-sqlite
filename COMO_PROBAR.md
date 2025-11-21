# 🌐 Cómo Probar y Sustentar el Proyecto

## 🚀 Inicio Rápido

1. **Asegúrate que la aplicación esté corriendo:**
   ```powershell
   cd C:\Users\Fix\Desktop\proyecto_springboot_sqlite
   $env:JAVA_HOME='C:\Program Files\Eclipse Adoptium\jdk-17.0.17.10-hotspot'
   $env:Path="$env:JAVA_HOME\bin;$env:Path"
   .\mvnw.cmd spring-boot:run
   ```

2. **Abre demo.html en tu navegador** (doble click)
3. **Deberías ver:** "✅ Servidor conectado"
4. **¡Listo para probar!**

---

## 📋 Opciones Disponibles

### 1. 🌐 Desde el Navegador (Solo GET)
**Ventaja:** Rápido y visual  
**Limitación:** Solo funciona para endpoints GET

#### Probar GET desde el navegador:
```
http://localhost:8080/api/students
http://localhost:8080/api/students/1
```

**⚠️ Nota:** Para POST, PUT, DELETE necesitas otras herramientas.

---

### 2. 📮 Postman (RECOMENDADO para sustentar)
**Ventaja:** Interfaz visual, fácil de usar, guarda historial

#### Pasos:
1. **Descargar Postman:** https://www.postman.com/downloads/
2. **Importar colección:**
   - Abre Postman
   - Click en "Import"
   - Selecciona el archivo: `docs/postman-collection.json`
3. **Probar endpoints:**
   - Selecciona cualquier request
   - Click en "Send"
   - Ver respuesta en la parte inferior

#### Ventajas para sustentar:
- ✅ Interfaz visual profesional
- ✅ Puedes mostrar las respuestas JSON formateadas
- ✅ Historial de peticiones
- ✅ Fácil de explicar

---

### 3. 🖥️ PowerShell (Ya lo hemos usado)
**Ventaja:** Ya está funcionando, comandos listos

#### Comandos rápidos:

```powershell
# Listar todos
Invoke-RestMethod -Uri "http://localhost:8080/api/students" -Method GET

# Buscar por ID
Invoke-RestMethod -Uri "http://localhost:8080/api/students/1" -Method GET

# Crear estudiante
$body = '{\"firstName\":\"Test\",\"lastName\":\"User\",\"email\":\"test@email.com\",\"dateOfBirth\":\"2003-01-01\",\"program\":\"Test\",\"documentNumber\":\"123\"}'
Invoke-RestMethod -Uri "http://localhost:8080/api/students" -Method POST -Body $body -ContentType "application/json"

# Actualizar
$update = '{\"firstName\":\"Test\",\"lastName\":\"User\",\"email\":\"test@email.com\",\"dateOfBirth\":\"2003-01-01\",\"program\":\"Test\",\"documentNumber\":\"123\"}'
Invoke-RestMethod -Uri "http://localhost:8080/api/students/1" -Method PUT -Body $update -ContentType "application/json"

# Eliminar
Invoke-WebRequest -Uri "http://localhost:8080/api/students/1" -Method DELETE

# Generar PDF
Invoke-WebRequest -Uri "http://localhost:8080/api/students/1/certificate" -Method POST -OutFile "certificado.pdf"
```

---

### 4. 🌐 Página HTML Interactiva (RECOMENDADA)
**Ventaja:** Interfaz visual desde el navegador, fácil de usar, perfecta para sustentar

He creado `demo.html` que puedes abrir en tu navegador para probar todos los endpoints visualmente.

#### Cómo usar:
1. **Asegúrate que la aplicación esté corriendo** (ver inicio rápido arriba)
2. Abre `demo.html` en tu navegador (doble click o Live Server)
3. Deberías ver: "✅ Servidor conectado - API funcionando correctamente"
4. Usa los botones para probar cada funcionalidad

**⚠️ Si ves error de conexión:**
- Verifica que la aplicación esté corriendo
- Espera 10-20 segundos y recarga la página (F5)
- La aplicación tiene CORS configurado para permitir peticiones del navegador

---

### 5. 📝 cURL (Línea de comandos)
**Ventaja:** Universal, funciona en cualquier sistema

```powershell
# Listar todos
curl http://localhost:8080/api/students

# Buscar por ID
curl http://localhost:8080/api/students/1

# Crear estudiante
curl -X POST http://localhost:8080/api/students -H "Content-Type: application/json" -d '{\"firstName\":\"Test\",\"lastName\":\"User\",\"email\":\"test@email.com\",\"dateOfBirth\":\"2003-01-01\",\"program\":\"Test\",\"documentNumber\":\"123\"}'

# Actualizar
curl -X PUT http://localhost:8080/api/students/1 -H "Content-Type: application/json" -d '{\"firstName\":\"Test\",\"lastName\":\"User\",\"email\":\"test@email.com\",\"dateOfBirth\":\"2003-01-01\",\"program\":\"Test\",\"documentNumber\":\"123\"}'

# Eliminar
curl -X DELETE http://localhost:8080/api/students/1

# Generar PDF
curl -X POST http://localhost:8080/api/students/1/certificate --output certificado.pdf
```

---

## 🎯 Recomendación para Sustentar

### Opción 1: Postman (Más Profesional)
1. Importa `docs/postman-collection.json`
2. Muestra cada endpoint uno por uno
3. Explica las respuestas JSON
4. Muestra el certificado PDF generado

### Opción 2: Página HTML (Más Visual)
1. Abre `demo.html` en el navegador
2. Muestra la interfaz interactiva
3. Prueba cada funcionalidad en vivo
4. Muestra las respuestas en pantalla

### Opción 3: Combinación
1. Usa el navegador para mostrar GET endpoints
2. Usa Postman para POST/PUT/DELETE
3. Muestra el certificado PDF generado

---

## 📱 Para la Sustentación

### Checklist de lo que mostrar:
- [ ] ✅ Aplicación corriendo (mostrar logs en consola)
- [ ] ✅ GET /api/students - Listar todos (ordenados)
- [ ] ✅ GET /api/students/{id} - Buscar por ID
- [ ] ✅ POST /api/students - Crear estudiante
- [ ] ✅ PUT /api/students/{id} - Actualizar
- [ ] ✅ DELETE /api/students/{id} - Eliminar
- [ ] ✅ POST /api/students/{id}/certificate - Generar PDF
- [ ] ✅ Validaciones (email inválido, campos vacíos)
- [ ] ✅ Manejo de errores (404, 400)
- [ ] ✅ Base de datos SQLite (mostrar archivo)

---

## 🚀 Inicio Rápido

1. **Asegúrate que la aplicación esté corriendo:**
   ```powershell
   .\mvnw.cmd spring-boot:run
   ```

2. **Elige tu método:**
   - Postman: Importa `docs/postman-collection.json`
   - Navegador: Abre `demo.html`
   - PowerShell: Usa los comandos de arriba

3. **¡Listo para sustentar!** 🎉

