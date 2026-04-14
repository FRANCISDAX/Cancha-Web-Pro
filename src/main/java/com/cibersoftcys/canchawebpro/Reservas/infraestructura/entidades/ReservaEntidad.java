package com.cibersoftcys.canchawebpro.Reservas.infraestructura.entidades;

import java.time.LocalDateTime;

import com.cibersoftcys.canchawebpro.Canchas.dominio.modelos.enums.EstadoCancha;
import com.cibersoftcys.canchawebpro.Canchas.infraestructura.entidades.CanchaEntidad;
import com.cibersoftcys.canchawebpro.Reservas.dominio.modelos.enums.EstadoReserva;
import com.cibersoftcys.canchawebpro.Usuarios.infraestructura.entidades.UsuarioEntidad;

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
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="tbl_reservas")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class ReservaEntidad {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="reserva_Id")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EstadoReserva estado;

    // Rango horario
    @Column(name = "fecha_inicio", nullable = false)
    private LocalDateTime fechaInicio;

    @Column(name = "fecha_fin", nullable = false)
    private LocalDateTime fechaFin;

    @Column(name = "fecha_creacion", nullable = false)
    private LocalDateTime fechaCreacion;

    // 🔗 Relación con Usuario
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", nullable = false)
    private UsuarioEntidad usuario;

    // 🔗 Relación con Cancha
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cancha_id", nullable = false)
    private CanchaEntidad cancha;

    @PrePersist
    public void prePersist() {
        fechaCreacion = LocalDateTime.now();
    }

}