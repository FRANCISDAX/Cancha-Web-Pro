package com.cibersoftcys.canchawebpro.Usuarios.infraestructura.adaptadores;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.cibersoftcys.canchawebpro.Usuarios.dominio.modelos.Usuario;
import com.cibersoftcys.canchawebpro.Usuarios.dominio.puertos.salida.UsuarioRepositorioPuerto;
import com.cibersoftcys.canchawebpro.Usuarios.infraestructura.entidades.UsuarioEntidad;
import com.cibersoftcys.canchawebpro.Usuarios.infraestructura.mapeadores.UsuarioMapper;
import com.cibersoftcys.canchawebpro.Usuarios.infraestructura.repositorios.PostgreUsuarioRepositorio;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class UsuarioRepositorioAdaptador implements UsuarioRepositorioPuerto{

    private final PostgreUsuarioRepositorio usuarioJpaRepositorio;
    private final UsuarioMapper usuarioMapper;

    @Override
    public Usuario guardar(Usuario usuario) {
        UsuarioEntidad entidad = usuarioMapper.paraEntidad(usuario);
        UsuarioEntidad entidadGrabada = usuarioJpaRepositorio.save(entidad);

        return usuarioMapper.paraDominio(entidadGrabada);
    }

    @Override
    public Optional<Usuario> buscarPorId(Long id) {
        return usuarioJpaRepositorio.findById(id)
            .map(usuarioMapper::paraDominio);
    }

    @Override
    public List<Usuario> buscarTodos() {
        return usuarioJpaRepositorio.findAll()
            .stream()
            .map(usuarioMapper::paraDominio)
            .collect(Collectors.toList());
    }

    @Override
    public void eliminar(Long id) {
        usuarioJpaRepositorio.deleteById(id);
    }

    @Override
    public boolean existePorNombre(String nombre) {
        return usuarioJpaRepositorio.existsByNombre(nombre);
    }

    @Override
    public boolean existePorNombreYIdNot(String nombre, Long id) {
        return usuarioJpaRepositorio.existsByNombreAndIdNot(nombre, id);
    }

    @Override
    public boolean existePorEmail(String email) {
        return usuarioJpaRepositorio.existsByEmail(email);
    }

}