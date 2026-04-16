package com.cibersoftcys.canchawebpro.Sedes.infraestructura.controladores;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cibersoftcys.canchawebpro.Sedes.aplicacion.dtos.SedeRequest;
import com.cibersoftcys.canchawebpro.Sedes.aplicacion.dtos.SedeResponse;
import com.cibersoftcys.canchawebpro.Sedes.aplicacion.dtos.SedeResponseDetalle;
import com.cibersoftcys.canchawebpro.Sedes.aplicacion.servicios.SedeQueryService;
import com.cibersoftcys.canchawebpro.Sedes.dominio.puertos.entrada.SedeServicioPuerto;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("api/v1/sedes")
@RequiredArgsConstructor
@Tag(name = "Sedes", description = "Operaciones sobre Sedes")
public class SedeControlador {

    private final SedeServicioPuerto sedeServicioPuerto;
    private final SedeQueryService sedeQueryService;

    @Operation(summary = "Lista todas las Sedes.")
    @GetMapping
    public ResponseEntity<List<SedeResponse>> obtenerTodasSedes() {
        List<SedeResponse> sedes = sedeServicioPuerto.obtenerTodasSedes();
        return ResponseEntity.ok(sedes);
    }

    @Operation(summary = "Lista Sedes Activas.")
    @GetMapping("/activas")
    public ResponseEntity<List<SedeResponse>> obtenerSedesActivas() {
        return ResponseEntity.ok(sedeServicioPuerto.obtenerSedesActivas());
    }

    @Operation(summary = "Obtener una Sede por ID.")
    @GetMapping("/{id}")
    public ResponseEntity<SedeResponse> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(sedeServicioPuerto.obtenerSedePorId(id));
    }

    @Operation(summary = "Crear una Nueva Sede.")
    @PostMapping
    public ResponseEntity<SedeResponse> crearSede(@RequestBody SedeRequest request) {
        SedeResponse response = sedeServicioPuerto.crearSede(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Operation(summary = "Actualizar Sede.")
    @PutMapping("/{id}")
    public ResponseEntity<SedeResponse> actualizarSede(
        @PathVariable Long id,
        @RequestBody SedeRequest request) {

        return ResponseEntity.ok(
                sedeServicioPuerto.actualizarSede(id, request)
        );
    }

    @Operation(summary = "Eliminar Sede (Inactivar).")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarSede(@PathVariable Long id) {
        sedeServicioPuerto.eliminarSede(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Activar Sede.")
    @PatchMapping("/{id}/activar")
    public ResponseEntity<SedeResponse> activarSede(@PathVariable Long id) {
        return ResponseEntity.ok(sedeServicioPuerto.activarSede(id));
    }

    @Operation(summary = "Desactivar Sede.")
    @PatchMapping("/{id}/desactivar")
    public ResponseEntity<SedeResponse> desactivarSede(@PathVariable Long id) {
        return ResponseEntity.ok(sedeServicioPuerto.desactivarSede(id));
    }

    @Operation(summary = "Listar Sedes con Canchas.")
    @GetMapping("/detalle")
    public ResponseEntity<List<SedeResponseDetalle>> listarSedesConCanchas() {
        return ResponseEntity.ok(sedeQueryService.listarSedesConCanchas());
    }

}