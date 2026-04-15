package com.cibersoftcys.canchawebpro.Usuarios.dominio.modelos.valueObject;

import com.cibersoftcys.canchawebpro.Excepciones.BusinessValidationException;

public class PasswordUsuario {

    private final String valor;

    // 🔥 Constructor privado.
    private PasswordUsuario(String valor) {
        this.valor = valor;
    }

    // 🧠 Factory method.
    public static PasswordUsuario crear(String passwordPlano) {

        if (passwordPlano == null || passwordPlano.isBlank()) {
            throw new BusinessValidationException("La contraseña no puede ser vacía");
        }

        if (passwordPlano.length() < 8) {
            throw new BusinessValidationException("Debe tener mínimo 8 caracteres");
        }

        if (!passwordPlano.matches(".*[A-Z].*")) {
            throw new BusinessValidationException("Debe tener una mayúscula");
        }

        if (!passwordPlano.matches(".*[0-9].*")) {
            throw new BusinessValidationException("Debe tener un número");
        }

        String hash = org.springframework.security.crypto.bcrypt.BCrypt
                .hashpw(passwordPlano, org.springframework.security.crypto.bcrypt.BCrypt.gensalt());

        return new PasswordUsuario(hash);
    }

    // 🔎 Verificar contraseña.
    public boolean verificar(String passwordPlano) {
        return org.springframework.security.crypto.bcrypt.BCrypt
                .checkpw(passwordPlano, this.valor);
    }

    // 📌 Getter protegido.
    public String getValor() {
        return valor;
    }

    public static PasswordUsuario desdeHash(String hash) {

        if (hash == null || hash.isBlank()) {
            throw new BusinessValidationException("El hash no puede ser vacío");
        }

        return new PasswordUsuario(hash);
    }
    
}