package com.cibersoftcys.canchawebpro.Usuarios.infraestructura.configuracion;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.cibersoftcys.canchawebpro.Usuarios.dominio.modelos.Usuario;
import com.cibersoftcys.canchawebpro.Usuarios.dominio.modelos.enums.TipoUsuario;
import com.cibersoftcys.canchawebpro.Usuarios.dominio.modelos.valueObject.EmailUsuario;
import com.cibersoftcys.canchawebpro.Usuarios.dominio.modelos.valueObject.NombreUsuario;
import com.cibersoftcys.canchawebpro.Usuarios.dominio.modelos.valueObject.PasswordUsuario;
import com.cibersoftcys.canchawebpro.Usuarios.dominio.puertos.salida.UsuarioRepositorioPuerto;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class DataInitializer {

    private final UsuarioRepositorioPuerto usuarioRepositorio;
    private final PasswordEncoder passwordEncoder;

    @Bean
    @SuppressWarnings("unused")
    CommandLineRunner initAdmin() {
        return args -> {

            String emailAdmin = "admin@cancha.com";

            boolean existe = usuarioRepositorio.existePorEmail(emailAdmin);

            if (!existe) {

                // ✅ Value Objects con factory methods
                NombreUsuario nombre = new NombreUsuario("ADMIN");
                EmailUsuario email = EmailUsuario.crear(emailAdmin);
                PasswordUsuario password = PasswordUsuario.crear(
                        passwordEncoder.encode("123456")
                );

                // ✅ Entidad DDD
                Usuario admin = new Usuario(
                        nombre,
                        email,
                        null,
                        TipoUsuario.ADMIN
                );

                // ✅ Regla de negocio
                admin.asignarPassword(password);

                usuarioRepositorio.guardar(admin);

                log.info("✅ ADMIN creado automáticamente");
            } else {
                log.info("⚡ ADMIN ya existe");
            }
        };
    }
}