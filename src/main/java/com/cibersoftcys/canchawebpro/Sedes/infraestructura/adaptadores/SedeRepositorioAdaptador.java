package com.cibersoftcys.canchawebpro.Sedes.infraestructura.adaptadores;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.cibersoftcys.canchawebpro.Canchas.infraestructura.repositorios.PostgreCanchaRepositorio;
import com.cibersoftcys.canchawebpro.Sedes.dominio.modelos.Sede;
import com.cibersoftcys.canchawebpro.Sedes.dominio.modelos.enums.EstadoSede;
import com.cibersoftcys.canchawebpro.Sedes.dominio.puertos.salida.SedeRepositorioPuerto;
import com.cibersoftcys.canchawebpro.Sedes.infraestructura.entidades.SedeEntidad;
import com.cibersoftcys.canchawebpro.Sedes.infraestructura.mapeadores.SedeMapper;
import com.cibersoftcys.canchawebpro.Sedes.infraestructura.repositorios.PostgreSedeRepositorio;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class SedeRepositorioAdaptador implements SedeRepositorioPuerto {

    private final PostgreSedeRepositorio sedeJpaRepositorio;
    private final PostgreCanchaRepositorio canchaJpaRepositorio;
    private final SedeMapper sedeMapper;

    @Override
    public Sede guardar(Sede sede) {
        SedeEntidad entidad = sedeMapper.paraEntidad(sede);
        SedeEntidad entidadGrabada = sedeJpaRepositorio.save(entidad);

        return sedeMapper.paraDominio(entidadGrabada);
    }

    @Override
    public Optional<Sede> buscarPorId(Long id) {
        return sedeJpaRepositorio.findById(id)
            .map(sedeMapper::paraDominio);
    }

    @Override
    public List<Sede> buscarTodas() {
        return sedeJpaRepositorio.findAll()
            .stream()
            .map(sedeMapper::paraDominio)
            .collect(Collectors.toList());
    }

    @Override
    public void eliminar(Long id) {
        sedeJpaRepositorio.findById(id).ifPresent(entidad->{
            entidad.setEstado(EstadoSede.INACTIVA);
            entidad.setFechaActualizacion(LocalDateTime.now());
            sedeJpaRepositorio.save(entidad);
        });
    }

    @Override
    public boolean existePorNombre(String nombre) {
        return sedeJpaRepositorio.existsByNombreValor(nombre);
    }

    @Override
    public boolean existePorNombreYIdNot(String nombre, Long id) {
        return sedeJpaRepositorio.existsByNombreValorAndIdNot(nombre, id);
    }

    @Override
    public List<Sede> buscarSedesActivas() {
        return sedeJpaRepositorio.findByEstado(EstadoSede.ACTIVA)
            .stream()
            .map(sedeMapper::paraDominio)
            .collect(Collectors.toList());
    }

    @Override
    public Optional<Sede> buscarPorNombre(String nombre) {
        return sedeJpaRepositorio.findByNombreValor(nombre)
            .map(sedeMapper::paraDominio);
    }

    @Override
    public boolean existeCanchasPorSedeId(Long sedeId) {
        return canchaJpaRepositorio.existsBySedeId(sedeId);
    }

}