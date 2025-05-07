package com.bdb.api.katas.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "calificaciones")
public class RatingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "jurado_id", nullable = false)
    private JuryEntity jury;

    @ManyToOne
    @JoinColumn(name = "participante_id", nullable = false)
    private ParticipantEntity participants;

    @Column(name = "perfil_candidato")
    private Integer profile;

    @Column(name = "comunicacion")
    private Integer communication;

    @Column(name = "tecnica")
    private Integer technique;

    @Column(name = "puntos_extra")
    private Integer pointsExtra;

    @Column(name = "estado")
    private Boolean state;

    @Column(name = "fecha_registro")
    @CreationTimestamp
    private ZonedDateTime createdOn;

    @Column(name = "fecha_actualizacion")
    @UpdateTimestamp
    private ZonedDateTime lastUpdatedOn;

    @PrePersist
    @PreUpdate
    public void setFecha() {
        ZoneId bogotaZone = ZoneId.of("America/Bogota");
        this.createdOn = ZonedDateTime.now(bogotaZone);
        this.lastUpdatedOn = ZonedDateTime.now(bogotaZone);
    }
}
