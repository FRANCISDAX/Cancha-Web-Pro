package com.cibersoftcys.canchawebpro.Sedes.dominio.modelos.valueObject;

import com.cibersoftcys.canchawebpro.Excepciones.BusinessValidationException;

public class NombreSede {

    private String valor;

    protected NombreSede() {}

    public NombreSede(String valor) {
        if (valor == null || valor.trim().isEmpty()) {
            throw new BusinessValidationException("El nombre de la Sede es obligatorio.");
        }
        if (valor.length() > 50) {
            throw new BusinessValidationException("El nombre no puede exceder 50 caracteres.");
        }
        this.valor = valor;
    }

    public String getValor() {
        return valor;
    }

}
