package com.cibersoftcys.canchawebpro.Usuarios.dominio.modelos.valueObject;

public class NombreUsuario {

    private String valor;

    protected NombreUsuario() {}

    // 🔥 Constructor.
    public NombreUsuario(String valor) {
        if (valor == null || valor.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del Usuario es obligatorio.");
        }
        if (valor.length() > 50) {
            throw new IllegalArgumentException("El nombre no puede exceder 50 caracteres.");
        }
        this.valor = valor;
    }

    // 🔧 Get.
    public String getValor() {
        return valor;
    }
    
}