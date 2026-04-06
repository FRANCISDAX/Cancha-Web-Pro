package com.cibersoftcys.canchawebpro.Canchas.dominio.modelos.valueObject;

import java.math.BigDecimal;

import com.cibersoftcys.canchawebpro.Excepciones.BusinessValidationException;

public class Precio {
    private final BigDecimal valor;

    // 🔥 Constructor.
    private Precio(BigDecimal valor) {
        this.valor = valor;
    }

    // 🧩 Factory método público.
    public static Precio of(BigDecimal valor) {
        validarPrecio(valor);
        return new Precio(valor);
    }

    // 🔧 Get.
    public BigDecimal getValor() {
        return valor;
    }

    // ✅ Validación Encapsulada.
    private static void validarPrecio(BigDecimal precio) {
        if (precio == null) {
            throw new BusinessValidationException("El precio es Obligatorio.");
        }
        if (precio.compareTo(BigDecimal.ZERO) <= 0) {
            throw new BusinessValidationException("El precio debe ser Mayor a 0.");
        }
        if (precio.scale() > 2) {
            throw new BusinessValidationException("El precio solo puede tener hasta 2 Decimales.");
        }
        if (precio.compareTo(new BigDecimal("1000")) > 0) {
            throw new BusinessValidationException("El precio Excede el límite permitido.");
        }
    }

    // 🔁 Operaciones útiles (opcional).
    public Precio multiplicar(BigDecimal factor) {
        return Precio.of(valor.multiply(factor));
    }

    public Precio sumar(Precio otro) {
        return Precio.of(this.valor.add(otro.valor));
    }

    // ✅ equals y hashCode para Value Object.
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Precio)) return false;
        Precio otro = (Precio) o;
        return valor.equals(otro.valor);
    }

    @Override
    public int hashCode() {
        return valor.hashCode();
    }

    @Override
    public String toString() {
        return valor.toString();
    }

}