package com.cibersoftcys.canchawebpro.Canchas.dominio.modelos.valueObject;

public class NombreCancha {

    private String valor;

    protected NombreCancha() {}

    // 🔥 Constructor.
    public NombreCancha(String valor) {
        if (valor == null || valor.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre de la cancha es obligatorio.");
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
