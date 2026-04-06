package com.cibersoftcys.canchawebpro.Sedes.dominio.puertos.entrada;

import java.util.List;

import com.cibersoftcys.canchawebpro.Sedes.aplicacion.dtos.SedeRequest;
import com.cibersoftcys.canchawebpro.Sedes.aplicacion.dtos.SedeResponse;

public interface SedeServicioPuerto {

    SedeResponse crearSede(SedeRequest request);
    SedeResponse obtenerSedePorId(Long id);
    List<SedeResponse> obtenerTodasSedes();
    SedeResponse actualizarSede(Long id, SedeRequest request);
    void eliminarSede(Long id);
    List<SedeResponse> obtenerSedesActivas();
    SedeResponse activarSede(Long id);
    SedeResponse desactivarSede(Long id);

}