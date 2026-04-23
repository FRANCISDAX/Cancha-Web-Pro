package com.cibersoftcys.canchawebpro.Usuarios.infraestructura.configuracion;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.cibersoftcys.canchawebpro.Usuarios.dominio.modelos.Usuario;
import com.cibersoftcys.canchawebpro.Usuarios.dominio.modelos.enums.TipoUsuario;
import com.cibersoftcys.canchawebpro.Usuarios.dominio.modelos.valueObject.EmailUsuario;
import com.cibersoftcys.canchawebpro.Usuarios.dominio.modelos.valueObject.NombreUsuario;
import com.cibersoftcys.canchawebpro.Usuarios.dominio.modelos.valueObject.PasswordUsuario;
import com.cibersoftcys.canchawebpro.Usuarios.dominio.puertos.salida.UsuarioRepositorioPuerto;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@SuppressWarnings("unused")
@Configuration
@RequiredArgsConstructor
@Slf4j
public class DataInitializer {

    private final UsuarioRepositorioPuerto usuarioRepositorio;

    @Bean
    CommandLineRunner initAdmin() {
        return args -> {

            String emailAdmin = "admin@cancha.com";

            boolean existe = usuarioRepositorio.existePorEmail(emailAdmin);

            if (!existe) {

                // ✅ Value Objects
                NombreUsuario nombre = new NombreUsuario("ADMIN");
                EmailUsuario email = EmailUsuario.crear(emailAdmin);

                // 🔐 IMPORTANTE: sin passwordEncoder (ya se hashea dentro del VO)
                PasswordUsuario password = PasswordUsuario.crear("Admin123");

                // ✅ Entidad
                Usuario admin = new Usuario(
                        nombre,
                        email,
                        null,
                        TipoUsuario.ADMIN
                );

                // ✅ Asignar contraseña
                admin.asignarPassword(password);

                usuarioRepositorio.guardar(admin);

                log.info("✅ ADMIN creado automáticamente");
            } else {
                log.info("⚡ ADMIN ya existe");
            }
        };
    }
}