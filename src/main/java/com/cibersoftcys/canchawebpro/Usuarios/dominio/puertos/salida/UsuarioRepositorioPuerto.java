package com.cibersoftcys.canchawebpro.Usuarios.dominio.puertos.salida;

import java.util.List;
import java.util.Optional;

import com.cibersoftcys.canchawebpro.Usuarios.dominio.modelos.Usuario;

public interface UsuarioRepositorioPuerto {

    Usuario guardar(Usuario usuario);
    Optional<Usuario> buscarPorId(Long id);
    List<Usuario> buscarTodos();
    void eliminar(Long id);
    boolean existePorNombre(String nombre);
    boolean existePorNombreYIdNot(String nombre, Long id);
    boolean existePorEmail(String email);   
}
