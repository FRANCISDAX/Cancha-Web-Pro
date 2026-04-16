package com.cibersoftcys.canchawebpro.Usuarios.infraestructura.controladores;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cibersoftcys.canchawebpro.Usuarios.aplicacion.dtos.PasswordUpdateRequest;
import com.cibersoftcys.canchawebpro.Usuarios.aplicacion.dtos.TelefonoUpdateRequest;
import com.cibersoftcys.canchawebpro.Usuarios.aplicacion.dtos.UsuarioRequest;
import com.cibersoftcys.canchawebpro.Usuarios.aplicacion.dtos.UsuarioResponse;
import com.cibersoftcys.canchawebpro.Usuarios.dominio.puertos.entrada.UsuarioServicioPuerto;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("api/v1/usuarios")
@RequiredArgsConstructor
@Tag(name = "Usuarios", description = "Operaciones sobre Usuarios")
public class UsuarioControlador {

    private final UsuarioServicioPuerto usuarioServicioPuerto;

    @Operation(summary = "Lista todos los Usuarios.")
    @GetMapping
    public ResponseEntity<List<UsuarioResponse>> obtenerTodosUsuarios() {
        List<UsuarioResponse> usuarios = usuarioServicioPuerto.obtenerTodosUsuarios();
        return ResponseEntity.ok(usuarios);
    }

    @Operation(summary = "Crear un Nuevo Usuario.")
    @PostMapping
    public ResponseEntity<UsuarioResponse> crearUsuario(@RequestBody UsuarioRequest request) {
        UsuarioResponse response = usuarioServicioPuerto.crearUsuario(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    
    @PatchMapping("/{id}/telefono")
    public ResponseEntity<UsuarioResponse> actualizarTelefono(
            @PathVariable Long id,
            @RequestBody TelefonoUpdateRequest request) {

        return ResponseEntity.ok(
            usuarioServicioPuerto.actualizarTelefono(id, request.getTelefono())
        );
    }

    @PatchMapping("/{id}/password")
    public ResponseEntity<Void> cambiarPassword(
            @PathVariable Long id,
            @Valid @RequestBody PasswordUpdateRequest request) {

        usuarioServicioPuerto.cambiarPassword(
            id,
            request.getPasswordActual(),
            request.getNuevaPassword()
        );

        return ResponseEntity.noContent().build();
    }
    
}