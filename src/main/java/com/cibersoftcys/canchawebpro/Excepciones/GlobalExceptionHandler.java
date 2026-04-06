package com.cibersoftcys.canchawebpro.Excepciones;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    // ===============================================================
    // 0. Validaciones de negocio personalizadas (400 - Bad Request)
    // ===============================================================
    @ExceptionHandler(BusinessValidationException.class)
    public ResponseEntity<?> handleBusinessValidation(BusinessValidationException ex, HttpServletRequest request) {
        
        Map<String, Object> error = new HashMap<>();
        error.put("timestamp", LocalDateTime.now());
        error.put("status", HttpStatus.BAD_REQUEST.value());
        error.put("error", "Error de validación del negocio.");
        error.put("message", ex.getMessage());
        error.put("path", request.getRequestURI());
        
        log.warn("Validación de negocio fallida - Path: {} - Error: {}", request.getRequestURI(), ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    // ===============================================================
    // 1. Error en JSON mal estructurado
    // ===============================================================
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<?> handleNotReadable(HttpMessageNotReadableException ex, HttpServletRequest request) {

        Map<String, Object> error = new HashMap<>();
        error.put("timestamp", LocalDateTime.now());
        error.put("status", HttpStatus.BAD_REQUEST.value());
        error.put("error", "JSON mal formado o tipos de datos incorrectos");
        error.put("details", ex.getMostSpecificCause().getMessage());
        error.put("path", request.getRequestURI());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    // ===============================================================
    // 2. Error de validación @Valid en DTOs (request body)
    // ===============================================================
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidation(MethodArgumentNotValidException ex, HttpServletRequest request) {

        Map<String, String> errors = new HashMap<>();
        for (FieldError err : ex.getBindingResult().getFieldErrors()) {
            errors.put(err.getField(), err.getDefaultMessage());
        }

        Map<String, Object> response = new HashMap<>();
        response.put("timestamp", LocalDateTime.now());
        response.put("status", HttpStatus.BAD_REQUEST.value());
        response.put("errores", errors);
        response.put("path", request.getRequestURI());
        log.warn("Errores de validación : {}",ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    // ===============================================================
    // 3. Validación de parámetros (path, query params...)
    // ===============================================================
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<?> handleConstraint(ConstraintViolationException ex, HttpServletRequest request) {

        Map<String, String> errors = new HashMap<>();
        ex.getConstraintViolations().forEach(v ->
                errors.put(v.getPropertyPath().toString(), v.getMessage())
        );

        Map<String, Object> response = new HashMap<>();
        response.put("timestamp", LocalDateTime.now());
        response.put("status", HttpStatus.BAD_REQUEST.value());
        response.put("errores", errors);
        response.put("path", request.getRequestURI());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    // ===============================================================
    // 4. Recurso no encontrado (404)
    // ===============================================================
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> handleNotFound(ResourceNotFoundException ex, HttpServletRequest request) {

        Map<String, Object> error = new HashMap<>();
        error.put("timestamp", LocalDateTime.now());
        error.put("status", HttpStatus.NOT_FOUND.value());
        error.put("error", ex.getMessage());
        error.put("path", request.getRequestURI());
        log.warn("No Encontrado : {}",ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    // ===============================================================
    // 5. Violación de integridad (FK, UNIQUE...)
    // ===============================================================
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<?> handleDataIntegrity(DataIntegrityViolationException ex, HttpServletRequest request) {

        String mensajeUsuario = extraerMensajeUsuario(ex);

        Map<String, Object> error = new HashMap<>();
        error.put("timestamp", LocalDateTime.now());
        error.put("status", HttpStatus.CONFLICT.value());
        error.put("error", "Violación de integridad de datos");
        error.put("details", mensajeUsuario);
        error.put("path", request.getRequestURI());

        return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
    }

    // ===============================================================
    // 6. Errores de argumentos inválidos (IDs null, etc.)
    // ===============================================================
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<?> handleIllegalArgument(IllegalArgumentException ex, HttpServletRequest request) {

        Map<String, Object> error = new HashMap<>();
        error.put("timestamp", LocalDateTime.now());
        error.put("status", HttpStatus.BAD_REQUEST.value());
        error.put("error", ex.getMessage());
        error.put("path", request.getRequestURI());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    // ===============================================================
    // 7. Fallback general (último recurso)
    // ===============================================================
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleGeneral(Exception ex, HttpServletRequest request) {

        Map<String, Object> error = new HashMap<>();
        error.put("timestamp", LocalDateTime.now());
        error.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
        error.put("error", "Error interno del servidor");
        error.put("details", ex.getMessage());
        error.put("path", request.getRequestURI());
        log.error("Error: {}", ex.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);

    }

    private String extraerMensajeUsuario(DataIntegrityViolationException ex) {
        String mensajeTecnico = ex.getMostSpecificCause().getMessage();
        
        if (mensajeTecnico.contains("foreign key") || mensajeTecnico.contains("FK_")) {
            return "No se puede completar la operación porque existen registros relacionados.";
        } else if (mensajeTecnico.contains("unique constraint") || mensajeTecnico.contains("Duplicate entry")) {
            return "Ya existe un registro con la misma información. Use datos únicos.";
        } else if (mensajeTecnico.contains("not-null")) {
            return "Faltan campos obligatorios para completar la operación.";
        } else {
            return "Error de integridad en los datos. Verifique la información.";
        }
    }

}