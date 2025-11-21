# Script para poblar la base de datos con 10 estudiantes
# Ejecutar: .\poblar_base_datos.ps1

Write-Host "========================================" -ForegroundColor Cyan
Write-Host "  POBLANDO BASE DE DATOS" -ForegroundColor Cyan
Write-Host "========================================" -ForegroundColor Cyan
Write-Host ""

$baseUrl = "http://localhost:8080/api/students"

# Array de estudiantes con datos variados
$estudiantes = @(
    @{
        firstName = "Ana"
        lastName = "Gomez"
        email = "ana.gomez@universidad.edu"
        dateOfBirth = "2003-05-12"
        program = "Ingenieria de Sistemas"
        documentNumber = "1234567890"
    },
    @{
        firstName = "Carlos"
        lastName = "Rodriguez"
        email = "carlos.rodriguez@universidad.edu"
        dateOfBirth = "2002-08-20"
        program = "Medicina"
        documentNumber = "2345678901"
    },
    @{
        firstName = "Maria"
        lastName = "Lopez"
        email = "maria.lopez@universidad.edu"
        dateOfBirth = "2004-03-15"
        program = "Derecho"
        documentNumber = "3456789012"
    },
    @{
        firstName = "Juan"
        lastName = "Martinez"
        email = "juan.martinez@universidad.edu"
        dateOfBirth = "2003-11-22"
        program = "Ingenieria Industrial"
        documentNumber = "4567890123"
    },
    @{
        firstName = "Laura"
        lastName = "Fernandez"
        email = "laura.fernandez@universidad.edu"
        dateOfBirth = "2004-07-08"
        program = "Psicologia"
        documentNumber = "5678901234"
    },
    @{
        firstName = "Diego"
        lastName = "Sanchez"
        email = "diego.sanchez@universidad.edu"
        dateOfBirth = "2002-12-30"
        program = "Administracion de Empresas"
        documentNumber = "6789012345"
    },
    @{
        firstName = "Sofia"
        lastName = "Ramirez"
        email = "sofia.ramirez@universidad.edu"
        dateOfBirth = "2005-01-18"
        program = "Arquitectura"
        documentNumber = "7890123456"
    },
    @{
        firstName = "Andres"
        lastName = "Torres"
        email = "andres.torres@universidad.edu"
        dateOfBirth = "2003-09-25"
        program = "Ingenieria Electronica"
        documentNumber = "8901234567"
    },
    @{
        firstName = "Valentina"
        lastName = "Diaz"
        email = "valentina.diaz@universidad.edu"
        dateOfBirth = "2004-06-14"
        program = "Enfermeria"
        documentNumber = "9012345678"
    },
    @{
        firstName = "Sebastian"
        lastName = "Moreno"
        email = "sebastian.moreno@universidad.edu"
        dateOfBirth = "2002-10-05"
        program = "Contaduria Publica"
        documentNumber = "0123456789"
    }
)

$creados = 0
$errores = 0

foreach ($estudiante in $estudiantes) {
    try {
        $body = $estudiante | ConvertTo-Json
        $response = Invoke-RestMethod -Uri $baseUrl -Method POST -Body $body -ContentType "application/json" -ErrorAction Stop
        
        $creados++
        Write-Host "✅ Estudiante $creados creado: $($estudiante.firstName) $($estudiante.lastName) (ID: $($response.id))" -ForegroundColor Green
    }
    catch {
        $errores++
        Write-Host "❌ Error al crear $($estudiante.firstName) $($estudiante.lastName): $_" -ForegroundColor Red
    }
}

Write-Host ""
Write-Host "========================================" -ForegroundColor Cyan
Write-Host "  RESUMEN" -ForegroundColor Cyan
Write-Host "========================================" -ForegroundColor Cyan
Write-Host "✅ Estudiantes creados: $creados" -ForegroundColor Green
Write-Host "❌ Errores: $errores" -ForegroundColor $(if ($errores -gt 0) { "Red" } else { "Green" })
Write-Host ""

if ($creados -gt 0) {
    Write-Host "Listando todos los estudiantes..." -ForegroundColor Cyan
    try {
        $todos = Invoke-RestMethod -Uri $baseUrl -Method GET
        Write-Host "Total en base de datos: $($todos.Count)" -ForegroundColor Green
        Write-Host ""
        $todos | ForEach-Object {
            Write-Host "  - ID $($_.id): $($_.firstName) $($_.lastName) - $($_.program)" -ForegroundColor White
        }
    }
    catch {
        Write-Host "Error al listar estudiantes: $_" -ForegroundColor Red
    }
}

