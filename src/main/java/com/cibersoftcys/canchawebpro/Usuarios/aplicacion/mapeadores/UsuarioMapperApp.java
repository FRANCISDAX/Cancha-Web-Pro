package com.cibersoftcys.canchawebpro.Usuarios.aplicacion.mapeadores;

import org.springframework.stereotype.Component;

import com.cibersoftcys.canchawebpro.Usuarios.aplicacion.dtos.UsuarioRequest;
import com.cibersoftcys.canchawebpro.Usuarios.aplicacion.dtos.UsuarioResponse;
import com.cibersoftcys.canchawebpro.Usuarios.dominio.modelos.Usuario;
import com.cibersoftcys.canchawebpro.Usuarios.dominio.modelos.valueObject.NombreUsuario;

@Component
public class UsuarioMapperApp {

    // ===== DOMAIN → RESPONSE =====
    public UsuarioResponse toResponse(Usuario usuario) {
        if(usuario == null) return null;

        UsuarioResponse response = new UsuarioResponse();
        response.setId(usuario.getId());
        response.setNombre(usuario.getNombre().getValor());
        response.setEmail(usuario.getEmail());
        response.setTelefono(usuario.getTelefono());
        response.setTipo(usuario.getTipo().name());
        return response;
    }

    // ===== REQUEST → DOMAIN ======
    public Usuario toDomain(UsuarioRequest request) {
        if(request == null) return null;

        NombreUsuario nombre = new NombreUsuario(request.getNombre());
        return new Usuario(
            nombre,
            request.getEmail(),
            request.getTelefono(),
            request.getTipo()
        );
    }
    
}
