package com.cibersoftcys.canchawebpro.Sedes.infraestructura.proyecciones;

public interface SedeCanchaFlat {

    Long getSedeId();
    String getSedeNombre();
    String getDireccion();
    String getDistrito();
    String getCiudad();
    String getEstado();

    Long getCanchaId();
    String getCanchaNombre();
    String getCanchaTipo();
    
}
