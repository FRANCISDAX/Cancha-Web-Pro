package com.cibersoftcys.canchawebpro.Usuarios.dominio.puertos.entrada;

import java.util.List;

import com.cibersoftcys.canchawebpro.Usuarios.aplicacion.dtos.UsuarioRequest;
import com.cibersoftcys.canchawebpro.Usuarios.aplicacion.dtos.UsuarioResponse;

public interface UsuarioServicioPuerto {

    UsuarioResponse crearUsuario(UsuarioRequest request);
    UsuarioResponse obtenerUsuarioPorId(Long id);
    List<UsuarioResponse> obtenerTodosUsuarios();
    UsuarioResponse actualizarUsuario(Long id, UsuarioRequest request);
    void eliminarUsuario(Long id);
    List<UsuarioResponse> obtenerUsuariosActivas();

}
