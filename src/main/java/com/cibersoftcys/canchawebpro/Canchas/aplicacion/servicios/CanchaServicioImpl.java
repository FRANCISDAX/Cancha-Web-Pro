package com.cibersoftcys.canchawebpro.Canchas.aplicacion.servicios;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cibersoftcys.canchawebpro.Canchas.aplicacion.dtos.CanchaRequest;
import com.cibersoftcys.canchawebpro.Canchas.aplicacion.dtos.CanchaResponse;
import com.cibersoftcys.canchawebpro.Canchas.aplicacion.mapeadores.CanchaMapperApli;
import com.cibersoftcys.canchawebpro.Canchas.dominio.modelos.Cancha;
import com.cibersoftcys.canchawebpro.Canchas.dominio.modelos.enums.TipoCancha;
import com.cibersoftcys.canchawebpro.Canchas.dominio.modelos.valueObject.NombreCancha;
import com.cibersoftcys.canchawebpro.Canchas.dominio.puertos.entrada.CanchaDominioPuerto;
import com.cibersoftcys.canchawebpro.Canchas.dominio.puertos.entrada.CanchaServicioPuerto;
import com.cibersoftcys.canchawebpro.Canchas.dominio.puertos.salida.CanchaRepositorioPuerto;
import com.cibersoftcys.canchawebpro.Excepciones.BusinessValidationException;
import com.cibersoftcys.canchawebpro.Excepciones.ResourceNotFoundException;
import com.cibersoftcys.canchawebpro.Sedes.dominio.modelos.Sede;
import com.cibersoftcys.canchawebpro.Sedes.dominio.puertos.salida.SedeRepositorioPuerto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class CanchaServicioImpl implements CanchaServicioPuerto, CanchaDominioPuerto{

    private final CanchaRepositorioPuerto canchaRepositorioPuerto;
    private final SedeRepositorioPuerto sedeRepositorioPuerto;
    private final CanchaMapperApli canchaMapperApp;

    @Override
    public Cancha obtenerCancha(Long id) {
        return canchaRepositorioPuerto.buscarPorId(id)
            .orElseThrow(() -> new BusinessValidationException( "Cancha no encontrada con id: " + id ));
    }

    @Override
    public CanchaResponse crearCancha(CanchaRequest request) {
        if (canchaRepositorioPuerto.existePorNombre(request.getNombre())) {
            throw new BusinessValidationException("Ya existe una Cancha con el nombre: " + request.getNombre());
        }

        Sede sede = sedeRepositorioPuerto.buscarPorId(request.getSedeId())
            .orElseThrow(() -> new ResourceNotFoundException("Sede no encontrada."));
        Cancha cancha = canchaMapperApp.toDomain(request);
        sede.agregarCancha(cancha);
        Cancha canchaGuardada = canchaRepositorioPuerto.guardar(cancha);
        return canchaMapperApp.toResponse(canchaGuardada);
    }

    @Override
    public CanchaResponse obtemerCanchaPorId(Long id) {
        Cancha cancha = canchaRepositorioPuerto.buscarPorId(id)
            .orElseThrow(()-> new ResourceNotFoundException("Cancha no encontrada con id: " + id));
        return canchaMapperApp.toResponse(cancha);
    }

    @Override
    public List<CanchaResponse> obtenerTodasCanchas() {
        return canchaRepositorioPuerto.buscarTodas()
            .stream()
            .map(canchaMapperApp::toResponse)
            .collect(Collectors.toList());
    }

    @Override
    public CanchaResponse actualizarCancha(Long id, CanchaRequest request) {
        Cancha cancha = canchaRepositorioPuerto.buscarPorId(id)
            .orElseThrow(() -> new ResourceNotFoundException("Cancha no encontrada con id: " + id));
        
        if (!cancha.getNombre().getValor().equalsIgnoreCase(request.getNombre()) &&
            canchaRepositorioPuerto.existePorNombre(request.getNombre())) {
            throw new BusinessValidationException("Ya existe una Cancha con el nombre: " + request.getNombre());
        }

        //Sede sede = sedeRepositorioPuerto.buscarPorId(request.getSedeId())
        //.orElseThrow(() -> new ResourceNotFoundException("Sede no encontrada"));

        NombreCancha nombre = new NombreCancha(request.getNombre());
        TipoCancha tipo = request.getTipo();

        cancha.actualizarDatos(nombre, tipo, request.getImagenUrl());
        Cancha actualizada = canchaRepositorioPuerto.guardar(cancha);
        return canchaMapperApp.toResponse(actualizada);
    }

    @Override
    public void eliminarCancha(Long id) {
        obtenerCancha(id);
        canchaRepositorioPuerto.eliminar(id);
    }

    @Override
    public void ponerEnMantenimiento(Long id) {
        Cancha cancha = canchaRepositorioPuerto.buscarPorId(id)
            .orElseThrow(() -> new ResourceNotFoundException("Cancha no encontrada con id: " + id));
        cancha.ponerEnMantenimientoCancha();
        canchaRepositorioPuerto.guardar(cancha);
    }

    @Override
    public void activarCancha(Long id) {
        Cancha cancha = canchaRepositorioPuerto.buscarPorId(id)
            .orElseThrow(() -> new ResourceNotFoundException("Cancha no encontrada con id: " + id));
        cancha.ponerEnDisponibleCancha();
        canchaRepositorioPuerto.guardar(cancha);
    }

}