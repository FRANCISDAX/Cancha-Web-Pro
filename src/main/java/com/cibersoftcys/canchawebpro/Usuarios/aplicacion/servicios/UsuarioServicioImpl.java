package com.cibersoftcys.canchawebpro.Usuarios.aplicacion.servicios;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.cibersoftcys.canchawebpro.Excepciones.BusinessValidationException;
import com.cibersoftcys.canchawebpro.Excepciones.ResourceNotFoundException;
import com.cibersoftcys.canchawebpro.Usuarios.aplicacion.dtos.UsuarioRequest;
import com.cibersoftcys.canchawebpro.Usuarios.aplicacion.dtos.UsuarioResponse;
import com.cibersoftcys.canchawebpro.Usuarios.aplicacion.mapeadores.UsuarioMapperApp;
import com.cibersoftcys.canchawebpro.Usuarios.dominio.modelos.Usuario;
import com.cibersoftcys.canchawebpro.Usuarios.dominio.modelos.enums.TipoUsuario;
import com.cibersoftcys.canchawebpro.Usuarios.dominio.modelos.valueObject.PasswordUsuario;
import com.cibersoftcys.canchawebpro.Usuarios.dominio.puertos.entrada.UsuarioDominioPuerto;
import com.cibersoftcys.canchawebpro.Usuarios.dominio.puertos.entrada.UsuarioServicioPuerto;
import com.cibersoftcys.canchawebpro.Usuarios.dominio.puertos.salida.UsuarioRepositorioPuerto;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class UsuarioServicioImpl implements UsuarioServicioPuerto, UsuarioDominioPuerto {

    private final UsuarioRepositorioPuerto usuarioRepositorioPuerto;
    private final UsuarioMapperApp usuarioMapperApp;

    @Override
    public UsuarioResponse crearUsuario(UsuarioRequest request) {
        if (usuarioRepositorioPuerto.existePorNombre(request.getNombre())) {
             throw new BusinessValidationException("Ya existe un Usuario con el nombre: " + request.getNombre());
        }
        
        Usuario usuario = usuarioMapperApp.toDomain(request);
        if (usuarioRepositorioPuerto.existePorEmail(usuario.getEmail().getValor())) {
            throw new BusinessValidationException(
                "Ya existe un usuario con el email: " + usuario.getEmail().getValor()
            );
        }

        Usuario usuarioGuardado = usuarioRepositorioPuerto.guardar(usuario);
        return usuarioMapperApp.toResponse(usuarioGuardado);
    }

    @Override
    public UsuarioResponse obtenerUsuarioPorId(Long id) {
        Usuario usuario = usuarioRepositorioPuerto.buscarPorId(id)
            .orElseThrow(()-> new ResourceNotFoundException("Usuario no encontrado con id: " + id));
        return usuarioMapperApp.toResponse(usuario);
    }

    @Override
    public List<UsuarioResponse> obtenerTodosUsuarios() {
        return usuarioRepositorioPuerto.buscarTodos()
            .stream()
            .map(usuarioMapperApp::toResponse)
            .collect(Collectors.toList());
    }

    @Override
    public UsuarioResponse actualizarUsuario(Long id, UsuarioRequest request) {
        Usuario usuario = usuarioRepositorioPuerto.buscarPorId(id)
            .orElseThrow(() -> new ResourceNotFoundException(
                "Usuario no encontrado con id: " + id
            ));

        usuario.actualizarTelefono(request.getTelefono());

        Usuario actualizado = usuarioRepositorioPuerto.guardar(usuario);

        return usuarioMapperApp.toResponse(actualizado);
    }

    @Override
    public void eliminarUsuario(Long id) {
        if (!usuarioRepositorioPuerto.buscarPorId(id).isPresent()) {
            throw new ResourceNotFoundException(
                "Usuario no encontrado con id: " + id
            );
        }
        
        usuarioRepositorioPuerto.eliminar(id);
    }

    @Override
    public List<UsuarioResponse> obtenerUsuariosActivas() {
        throw new UnsupportedOperationException("Esto es Usuario Activo Not supported yet.");
    }

    @Override
    public Usuario obtenerusuario(Long id) {
        return usuarioRepositorioPuerto.buscarPorId(id)
            .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado con id: " + id));
    }

    @Override
    public UsuarioResponse actualizarTelefono(Long id, String telefono) {
         Usuario usuario = usuarioRepositorioPuerto.buscarPorId(id)
            .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado."));

        usuario.actualizarTelefono(telefono);

        Usuario actualizado = usuarioRepositorioPuerto.guardar(usuario);

        return usuarioMapperApp.toResponse(actualizado);
    }

    @Override
    public void cambiarPassword(Long id, String passwordActual, String nuevaPassword) {
        Usuario usuario = usuarioRepositorioPuerto.buscarPorId(id)
        .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado"));

        if (!usuario.tienePassword()) {
            throw new BusinessValidationException("Usuario no tiene password configurado.");
        }

        if (!usuario.getPassword().verificar(passwordActual)) {
            throw new BusinessValidationException("La contraseña actual es incorrecta");
        }

        if (passwordActual.equals(nuevaPassword)) {
            throw new BusinessValidationException("La nueva contraseña no puede ser igual.");
        }

        usuario.cambiarPassword(PasswordUsuario.crear(nuevaPassword));

        usuarioRepositorioPuerto.guardar(usuario);
    }

    @Override
    public UsuarioResponse cambiarTipo(Long id, String tipo) {
        Usuario usuario = usuarioRepositorioPuerto.buscarPorId(id)
        .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado"));

        TipoUsuario nuevoTipo = TipoUsuario.valueOf(tipo);

        usuario.cambiarTipo(nuevoTipo);

        Usuario actualizado = usuarioRepositorioPuerto.guardar(usuario);

        return usuarioMapperApp.toResponse(actualizado);
    }

    @Override
    public Usuario login(String email, String password) {
        email = email.trim().toLowerCase();

        Usuario usuario = usuarioRepositorioPuerto.buscarPorEmail(email)
            .orElseThrow(() -> new BusinessValidationException("Credenciales incorrectas."));

        // 🔐 Verificar password (bcrypt)
        if (!usuario.getPassword().verificar(password)) {
            throw new BusinessValidationException("Credenciales incorrectas.");
        }

        return usuario;
    }
    
}