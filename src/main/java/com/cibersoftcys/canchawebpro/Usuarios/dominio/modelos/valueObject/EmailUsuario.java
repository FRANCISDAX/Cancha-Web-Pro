package com.cibersoftcys.canchawebpro.Usuarios.dominio.modelos.valueObject;

import java.util.Objects;

import com.cibersoftcys.canchawebpro.Excepciones.BusinessValidationException;

public class EmailUsuario {

    private final String valor;

    // 🔒 Constructor privado
    private EmailUsuario(String valor) {
        this.valor = valor;
    }

    // 🧠 Factory method (creación controlada)
    public static EmailUsuario crear(String email) {

        if (email == null || email.isBlank()) {
            throw new BusinessValidationException("El Email no puede estar vacío.");
        }

        email = email.trim().toLowerCase();

        if (!esFormatoValido(email)) {
            throw new BusinessValidationException("Formato de Email inválido.");
        }

        return new EmailUsuario(email);
    }

    // 🔍 Validación de formato
    private static boolean esFormatoValido(String email) {
        return email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");
    }

    // 📌 Getter
    public String getValor() {
        return valor;
    }

    // ⚖️ Igualdad por valor (clave en VO)
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EmailUsuario)) return false;
        EmailUsuario that = (EmailUsuario) o;
        return valor.equals(that.valor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(valor);
    }

    @Override
    public String toString() {
        return valor;
    }

}