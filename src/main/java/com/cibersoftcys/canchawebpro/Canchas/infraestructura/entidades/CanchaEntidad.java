package com.cibersoftcys.canchawebpro.Canchas.infraestructura.entidades;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.cibersoftcys.canchawebpro.Canchas.dominio.modelos.enums.EstadoCancha;
import com.cibersoftcys.canchawebpro.Canchas.dominio.modelos.enums.TipoCancha;
import com.cibersoftcys.canchawebpro.Sedes.infraestructura.entidades.SedeEntidad;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="tbl_canchas")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class CanchaEntidad {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="cancha_Id")
    private Long id;

    @Column(name="nombre", length=50, nullable=false, unique=true)
    private String nombre;

    @Column(name="imagen_url", length=255)
    private String imagenUrl;

    @Column(name="precio_hora")
    private BigDecimal precioPorHora;

    @Enumerated(EnumType.STRING)
    @Column(name="estado", nullable=false)
    private EstadoCancha estado;

    @Enumerated(EnumType.STRING)
    @Column(name="tipo", nullable=false)
    private TipoCancha tipo;

    @Column(name="fecha_creacion", nullable=true, updatable=false)
    @Builder.Default
    private LocalDateTime fechaCreacion = LocalDateTime.now();

    @Column(name="fecha_actualizacion", nullable=false)
    @Builder.Default
    private LocalDateTime fechaActualizacion = LocalDateTime.now();

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "sede_id")
    private SedeEntidad sede;

    //private List<Reserva> reservas = new ArrayList<>();

    @PrePersist
    public void prePersist() {
        if (estado == null) {
            estado = EstadoCancha.DISPONIBLE;
        }
        fechaCreacion = LocalDateTime.now();
        fechaActualizacion = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        fechaActualizacion = LocalDateTime.now();
    }
    
}