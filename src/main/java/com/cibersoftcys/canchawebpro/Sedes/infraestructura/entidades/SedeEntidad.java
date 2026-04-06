package com.cibersoftcys.canchawebpro.Sedes.infraestructura.entidades;

import java.time.LocalDateTime;

import com.cibersoftcys.canchawebpro.Sedes.dominio.modelos.enums.EstadoSede;
import com.cibersoftcys.canchawebpro.Sedes.dominio.modelos.valueObject.NombreSede;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="tbl_sedes")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class SedeEntidad {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="sede_Id")
    private Long id;

    @Embedded
    @AttributeOverride(name = "valor", column = @Column(name = "nombre", length = 50, nullable = false))
    private NombreSede nombre;

    private String direccion;
    private String distrito;
    private String ciudad;

    @Enumerated(EnumType.STRING)
    @Column(nullable=false)
    private EstadoSede estado;

    @Column(name="fecha_creacion", nullable=false, updatable=false)
    private LocalDateTime fechaCreacion;

    @Column(name="fecha_actualizacion", nullable=false)
    private LocalDateTime fechaActualizacion;

}