package com.cibersoftcys.canchawebpro.Usuarios.infraestructura.mapeadores;

import org.springframework.stereotype.Component;

import com.cibersoftcys.canchawebpro.Usuarios.dominio.modelos.Usuario;
import com.cibersoftcys.canchawebpro.Usuarios.dominio.modelos.valueObject.EmailUsuario;
import com.cibersoftcys.canchawebpro.Usuarios.dominio.modelos.valueObject.NombreUsuario;
import com.cibersoftcys.canchawebpro.Usuarios.dominio.modelos.valueObject.PasswordUsuario;
import com.cibersoftcys.canchawebpro.Usuarios.infraestructura.entidades.UsuarioEntidad;

@Component
public class UsuarioMapper {

    // ✅ Mapper Dominio → Entidad (Infraestructura)
    public UsuarioEntidad paraEntidad(Usuario usuario) {
        if (usuario == null) return  null;

        return UsuarioEntidad.builder()
            .id(usuario.getId())
            .nombre(usuario.getNombre().getValor())
            .email(usuario.getEmail().getValor())
            .telefono(usuario.getTelefono())
            .password(usuario.getPassword() != null 
                ? usuario.getPassword().getValor() 
                : null)
            .tipo(usuario.getTipo())
            .fechaRegistro(usuario.getFechaRegistro())
            .fechaActualizacion(usuario.getFechaActualizacion())
            .build();
    }
    
    // ✅ Mapper Entidad → Dominio
    public Usuario paraDominio(UsuarioEntidad entidad) {
        if (entidad == null) return null;
         Usuario usuario = new Usuario(
            new NombreUsuario(entidad.getNombre()),
            EmailUsuario.crear(entidad.getEmail()),
            entidad.getTelefono(),
            entidad.getTipo()
        );

        usuario.setId(entidad.getId());

        // 🔐 reconstruir password.
        if (entidad.getPassword() != null) {
            usuario.asignarPassword(
                PasswordUsuario.desdeHash(entidad.getPassword())
            );
        }

        return usuario;
    }

}