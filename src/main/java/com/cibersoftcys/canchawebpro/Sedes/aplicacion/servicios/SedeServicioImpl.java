package com.cibersoftcys.canchawebpro.Sedes.aplicacion.servicios;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.cibersoftcys.canchawebpro.Excepciones.BusinessValidationException;
import com.cibersoftcys.canchawebpro.Excepciones.ResourceNotFoundException;
import com.cibersoftcys.canchawebpro.Sedes.aplicacion.dtos.SedeRequest;
import com.cibersoftcys.canchawebpro.Sedes.aplicacion.dtos.SedeResponse;
import com.cibersoftcys.canchawebpro.Sedes.aplicacion.mapeadores.SedeMapperApp;
import com.cibersoftcys.canchawebpro.Sedes.dominio.modelos.Sede;
import com.cibersoftcys.canchawebpro.Sedes.dominio.puertos.entrada.SedeDominioPuerto;
import com.cibersoftcys.canchawebpro.Sedes.dominio.puertos.entrada.SedeServicioPuerto;
import com.cibersoftcys.canchawebpro.Sedes.dominio.puertos.salida.SedeRepositorioPuerto;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class SedeServicioImpl implements SedeServicioPuerto, SedeDominioPuerto {

    private final SedeRepositorioPuerto sedeRepositorioPuerto;
    private final SedeMapperApp sedeMapperApp;

    @Override
    public SedeResponse crearSede(SedeRequest request) {
        if (sedeRepositorioPuerto.existePorNombre(request.getNombre())) {
            throw new BusinessValidationException("Ya existe una Sede con el nombre: " + request.getNombre());
        }

        Sede sede = sedeMapperApp.toDomain(request);
        Sede sedeGuardada = sedeRepositorioPuerto.guardar(sede);
        return sedeMapperApp.toResponse(sedeGuardada);
    }

    @Override
    public SedeResponse obtenerSedePorId(Long id) {
        Sede sede = sedeRepositorioPuerto.buscarPorId(id)
            .orElseThrow(()-> new ResourceNotFoundException("Sede no encontrada con id: " + id));
        return sedeMapperApp.toResponse(sede);
    }

    @Override
    public List<SedeResponse> obtenerTodasSedes() {
        return sedeRepositorioPuerto.buscarTodas()
            .stream()
            .map(sedeMapperApp::toResponse)
            .collect(Collectors.toList());
    }

    @Override
    public SedeResponse actualizarSede(Long id, SedeRequest request) {
        Sede sede = sedeRepositorioPuerto.buscarPorId(id)
            .orElseThrow(() -> new ResourceNotFoundException("Sede no encontrada con id: " + id));

        if (sedeRepositorioPuerto.existePorNombreYIdNot(request.getNombre(), id)) {
            throw new BusinessValidationException("Ya existe otra Sede con ese nombre.");
        }

        sede.actualizarDatos(
            request.getNombre(),
            request.getDireccion(),
            request.getDistrito(),
            request.getCiudad()
        );
        Sede sedeActualizada = sedeRepositorioPuerto.guardar(sede);

        return sedeMapperApp.toResponse(sedeActualizada);
    }

    @Override
    public void eliminarSede(Long id) {
        if (sedeRepositorioPuerto.existeCanchasPorSedeId(id)) {
            throw new BusinessValidationException("No se puede eliminar la Sede porque tiene Canchas asociadas.");
        }
        sedeRepositorioPuerto.eliminar(id);
    }

    @Override
    public List<SedeResponse> obtenerSedesActivas() {
        return sedeRepositorioPuerto.buscarSedesActivas()
            .stream()
            .map(sedeMapperApp::toResponse)
            .collect(Collectors.toList());
    }

    @Override
    public SedeResponse activarSede(Long id) {
        Sede sede = obtenerSede(id);
        sede.activar();
        return sedeMapperApp.toResponse(sedeRepositorioPuerto.guardar(sede));
    }

    @Override
    public SedeResponse desactivarSede(Long id) {
        Sede sede = obtenerSede(id);
        sede.desactivar();
        return sedeMapperApp.toResponse(sedeRepositorioPuerto.guardar(sede));
    }

    @Override
    public Sede obtenerSede(Long id) {
        return sedeRepositorioPuerto.buscarPorId(id)
            .orElseThrow(() -> new BusinessValidationException( "Sede no encontrada con id: " + id ));
    }

}