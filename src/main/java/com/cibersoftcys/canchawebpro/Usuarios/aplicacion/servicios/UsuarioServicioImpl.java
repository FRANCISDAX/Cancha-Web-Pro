package com.cibersoftcys.canchawebpro.Usuarios.aplicacion.servicios;

import java.util.List;

import com.cibersoftcys.canchawebpro.Usuarios.aplicacion.dtos.UsuarioRequest;
import com.cibersoftcys.canchawebpro.Usuarios.aplicacion.dtos.UsuarioResponse;
import com.cibersoftcys.canchawebpro.Usuarios.dominio.modelos.Usuario;
import com.cibersoftcys.canchawebpro.Usuarios.dominio.puertos.entrada.UsuarioDominioPuerto;
import com.cibersoftcys.canchawebpro.Usuarios.dominio.puertos.entrada.UsuarioServicioPuerto;

public class UsuarioServicioImpl implements UsuarioServicioPuerto, UsuarioDominioPuerto {

    @Override
    public UsuarioResponse crearUsuario(UsuarioRequest request) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public UsuarioResponse obtenerUsuarioPorId(Long id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<UsuarioResponse> obtenerTodosUsuarios() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public UsuarioResponse actualizarUsuario(Long id, UsuarioRequest request) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void eliminarUsuario(Long id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<UsuarioResponse> obtenerUsuariosActivas() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Usuario obtenerusuario(Long id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }


}