package com.cibersoftcys.canchawebpro.Usuarios.aplicacion.mapeadores;

import org.springframework.stereotype.Component;

import com.cibersoftcys.canchawebpro.Usuarios.aplicacion.dtos.UsuarioRequest;
import com.cibersoftcys.canchawebpro.Usuarios.aplicacion.dtos.UsuarioResponse;
import com.cibersoftcys.canchawebpro.Usuarios.dominio.modelos.Usuario;
import com.cibersoftcys.canchawebpro.Usuarios.dominio.modelos.enums.TipoUsuario;
import com.cibersoftcys.canchawebpro.Usuarios.dominio.modelos.valueObject.EmailUsuario;
import com.cibersoftcys.canchawebpro.Usuarios.dominio.modelos.valueObject.NombreUsuario;
import com.cibersoftcys.canchawebpro.Usuarios.dominio.modelos.valueObject.PasswordUsuario;

@Component
public class UsuarioMapperApp {

    // ===== DOMAIN → RESPONSE =====
    public UsuarioResponse toResponse(Usuario usuario) {
        if(usuario == null) return null;

        UsuarioResponse response = new UsuarioResponse();
        response.setId(usuario.getId());
        response.setNombre(usuario.getNombre().getValor());
        response.setEmail(usuario.getEmail().getValor());
        response.setTelefono(usuario.getTelefono());
        response.setTipo(usuario.getTipo().name());
        response.setFechaRegistro(usuario.getFechaRegistro().toString());
        return response;
    }

    // ===== REQUEST → DOMAIN ======
    public Usuario toDomain(UsuarioRequest request) {
        if(request == null) return null;

        Usuario usuario = new Usuario(
            new NombreUsuario(request.getNombre()),
            EmailUsuario.crear(request.getEmail()),
            request.getTelefono(),
            TipoUsuario.CLIENTE
        );

        // 🔐 asignar password
        usuario.asignarPassword(
            PasswordUsuario.crear(request.getPassword())
        );

        return usuario;
    }
    
}
