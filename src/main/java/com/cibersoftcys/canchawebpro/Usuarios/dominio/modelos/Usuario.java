package com.cibersoftcys.canchawebpro.Usuarios.dominio.modelos;

import java.time.LocalDateTime;

import com.cibersoftcys.canchawebpro.Excepciones.BusinessValidationException;
import com.cibersoftcys.canchawebpro.Usuarios.dominio.modelos.enums.TipoUsuario;
import com.cibersoftcys.canchawebpro.Usuarios.dominio.modelos.valueObject.EmailUsuario;
import com.cibersoftcys.canchawebpro.Usuarios.dominio.modelos.valueObject.NombreUsuario;
import com.cibersoftcys.canchawebpro.Usuarios.dominio.modelos.valueObject.PasswordUsuario;

public class Usuario {

    // 📌 Atributos.
    private Long id;
    private NombreUsuario nombre;
    private EmailUsuario email;
    private String telefono;
    private PasswordUsuario passwordHash;
    private TipoUsuario tipo;
    private LocalDateTime fechaRegistro;
    private LocalDateTime fechaActualizacion;

    // 🔥 Constructores.
    public Usuario() {}

    public Usuario(NombreUsuario nombre, EmailUsuario email, String telefono, TipoUsuario tipo) {
        this.nombre = nombre;
        this.email = email;
        this.telefono = telefono;
        this.tipo = (tipo != null) ? tipo : TipoUsuario.CLIENTE;
        this.fechaRegistro = LocalDateTime.now();
        this.fechaActualizacion = fechaRegistro;
    }

    // 🔧 Getters (PROTEGIDOS).
    public Long getId() { return id; }
    public NombreUsuario getNombre() { return nombre; }
    public EmailUsuario getEmail() { return email; }
    public String getTelefono() { return telefono; }
    public TipoUsuario getTipo() { return tipo; }
    public LocalDateTime getFechaRegistro() { return fechaRegistro; }
    public LocalDateTime getFechaActualizacion() { return fechaActualizacion; }
    public PasswordUsuario getPassword() { return passwordHash; }

    // 🔧 Setters CONTROLADOS.
    public void setId(Long id) { this.id = id; }

    // 🧠 Reglas de negocio
    public void actualizarTelefono(String telefono) {
        if (telefono == null || telefono.isBlank()) {
            throw new BusinessValidationException("El teléfono es obligatorio");
        }

        this.telefono = telefono;
        marcarActualizacion();
    }

    public void cambiarTipo(TipoUsuario nuevoTipo) {
        if (nuevoTipo == null) {
            throw new BusinessValidationException("El tipo de usuario es obligatorio");
        }

        if (this.tipo == nuevoTipo) {
            throw new BusinessValidationException("El usuario ya tiene ese tipo");
        }

        this.tipo = nuevoTipo;
        marcarActualizacion();
    }

    public boolean esCliente() {
        return this.tipo == TipoUsuario.CLIENTE;
    }

    public boolean esAdmin() {
        return this.tipo == TipoUsuario.ADMIN;
    }

    public void asignarPassword(PasswordUsuario password) {
        if (password == null) {
            throw new BusinessValidationException("La contraseña es obligatoria");
        }

        this.passwordHash = password;
        marcarActualizacion();
    }

    public void cambiarPassword(PasswordUsuario nuevoPassword) {
        if (nuevoPassword == null) {
            throw new BusinessValidationException("La contraseña es obligatoria.");
        }

        if (this.passwordHash != null && this.passwordHash.equals(nuevoPassword)) {
            throw new BusinessValidationException("La nueva contraseña no puede ser igual a la anterior.");
        }

        this.passwordHash = nuevoPassword;
        marcarActualizacion();
    }

    public boolean tienePassword() {
        return this.passwordHash != null;
    }

    private void marcarActualizacion() {
        this.fechaActualizacion = LocalDateTime.now();
    }

}