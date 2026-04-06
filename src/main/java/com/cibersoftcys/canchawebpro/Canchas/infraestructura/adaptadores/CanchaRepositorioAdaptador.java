package com.cibersoftcys.canchawebpro.Canchas.infraestructura.adaptadores;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.cibersoftcys.canchawebpro.Canchas.dominio.modelos.Cancha;
import com.cibersoftcys.canchawebpro.Canchas.dominio.puertos.salida.CanchaRepositorioPuerto;
import com.cibersoftcys.canchawebpro.Canchas.infraestructura.entidades.CanchaEntidad;
import com.cibersoftcys.canchawebpro.Canchas.infraestructura.mapeadores.CanchaMapper;
import com.cibersoftcys.canchawebpro.Canchas.infraestructura.repositorios.PostgreCanchaRepositorio;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class CanchaRepositorioAdaptador implements CanchaRepositorioPuerto {

    private final PostgreCanchaRepositorio canchaJpaRepositorio;
    private final CanchaMapper canchaMapper;
    
    @Override
    public Cancha guardar(Cancha cancha) {
        CanchaEntidad entidad = canchaMapper.paraEntidad(cancha);
        CanchaEntidad entidadGrabada = canchaJpaRepositorio.save(entidad);

        return canchaMapper.paraDominio(entidadGrabada);
    }

    @Override
    public Optional<Cancha> buscarPorId(Long id) {
        return canchaJpaRepositorio.findById(id)
            .map(canchaMapper::paraDominio);
    }

    @Override
    public Optional<Cancha> buscarPorNombre(String nombre) {
        return canchaJpaRepositorio.findByNombre(nombre)
            .map(canchaMapper::paraDominio);
    }

    @Override
    public List<Cancha> buscarTodas() {
        return canchaJpaRepositorio.findAll()
            .stream()
            .map(canchaMapper::paraDominio)
            .collect(Collectors.toList());
    }

    @Override
    public void eliminar(Long id) {
        canchaJpaRepositorio.deleteById(id);
    }

    @Override
    public boolean existePorNombre(String nombre) {
        return canchaJpaRepositorio.existsByNombre(nombre);
    }

    @Override
    public boolean existePorNombreYIdNot(String nombre, Long id) {
        return canchaJpaRepositorio.existsByNombreAndIdNot(nombre, id);
    }

}