package com.cibersoftcys.canchawebpro.Canchas.dominio.puertos.entrada;

import java.util.List;

import com.cibersoftcys.canchawebpro.Canchas.aplicacion.dtos.CanchaRequest;
import com.cibersoftcys.canchawebpro.Canchas.aplicacion.dtos.CanchaResponse;

public interface CanchaServicioPuerto {

    CanchaResponse crearCancha(CanchaRequest request);
    CanchaResponse obtemerCanchaPorId(Long id);
    List<CanchaResponse> obtenerTodasCanchas();
    CanchaResponse actualizarCancha(Long id, CanchaRequest request);
    void eliminarCancha(Long id);

}
