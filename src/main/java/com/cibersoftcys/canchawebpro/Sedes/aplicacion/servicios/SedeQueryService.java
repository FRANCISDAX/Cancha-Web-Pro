package com.cibersoftcys.canchawebpro.Sedes.aplicacion.servicios;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.cibersoftcys.canchawebpro.Sedes.aplicacion.dtos.CanchaResponse;
import com.cibersoftcys.canchawebpro.Sedes.aplicacion.dtos.SedeResponseDetalle;
import com.cibersoftcys.canchawebpro.Sedes.infraestructura.proyecciones.SedeCanchaFlat;
import com.cibersoftcys.canchawebpro.Sedes.infraestructura.repositorios.PostgreSedeRepositorio;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SedeQueryService {

    private final PostgreSedeRepositorio repositorio;

    public List<SedeResponseDetalle> listarSedesConCanchas() {

        List<SedeCanchaFlat> data = repositorio.obtenerSedesConCanchas();

        Map<Long, SedeResponseDetalle> map = new LinkedHashMap<>();

        for (SedeCanchaFlat row : data) {

            SedeResponseDetalle sede = map.computeIfAbsent(row.getSedeId(), id -> {
                SedeResponseDetalle s = new SedeResponseDetalle();
                s.setId(row.getSedeId());
                s.setNombre(row.getSedeNombre());
                s.setCanchas(new ArrayList<>());
                return s;
            });

            if (row.getCanchaId() != null) {
                CanchaResponse cancha = new CanchaResponse();
                cancha.setId(row.getCanchaId());
                cancha.setNombre(row.getCanchaNombre());
                cancha.setTipo(row.getCanchaTipo());

                sede.getCanchas().add(cancha);
            }
        }

        // 🔥 total
        map.values().forEach(s -> 
            s.setTotalCanchas(s.getCanchas().size())
        );

        return new ArrayList<>(map.values());
    }

}