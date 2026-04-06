package com.cibersoftcys.canchawebpro.Canchas.infraestructura.controladores;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cibersoftcys.canchawebpro.Canchas.aplicacion.dtos.CanchaRequest;
import com.cibersoftcys.canchawebpro.Canchas.aplicacion.dtos.CanchaResponse;
import com.cibersoftcys.canchawebpro.Canchas.dominio.puertos.entrada.CanchaServicioPuerto;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/canchas")
@RequiredArgsConstructor
@Tag(name = "Canchas", description = "Operaciones sobre Canchas Deportivas")
public class CanchaContrtolador {

    private final CanchaServicioPuerto canchaServicioPuerto;

    @Operation(summary = "Crear una Nueva Cancha Deportiva.")
    @PostMapping
    public ResponseEntity<CanchaResponse> crear(@RequestBody CanchaRequest request) {
        CanchaResponse response = canchaServicioPuerto.crearCancha(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Operation(summary = "Lista todas las Canchas Deportivas.")
    @GetMapping
    public ResponseEntity<List<CanchaResponse>> listar() {
        List<CanchaResponse> canchas = canchaServicioPuerto.obtenerTodasCanchas();
        return ResponseEntity.ok(canchas);
    }

    @Operation(summary = "Obtener una Cancha Deportiva por ID.")
    @GetMapping("/{id}")
    public ResponseEntity<CanchaResponse> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(canchaServicioPuerto.obtemerCanchaPorId(id));
    }

    @Operation(summary = "Actualizar Cancha Deportiva.")
    @PutMapping("/{id}")
    public ResponseEntity<CanchaResponse> actualizarCancha(
        @PathVariable Long id,
        @RequestBody CanchaRequest request) {
        
        return ResponseEntity.ok(
            canchaServicioPuerto.actualizarCancha(id, request)
        );
    }

    @Operation(summary = "Eliminar Cancha Deportiva).")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        canchaServicioPuerto.eliminarCancha(id);
        return ResponseEntity.noContent().build();
    }

}