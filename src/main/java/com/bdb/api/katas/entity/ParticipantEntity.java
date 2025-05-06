package com.bdb.api.katas.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "participantes")
public class ParticipantEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "nombre")
    private String name;

    @Column(name = "correo")
    private String email;

    @Column(name = "fecha_registro")
    @CreationTimestamp
    private ZonedDateTime createdOn;

    @PrePersist
    @PreUpdate
    public void setFecha() {
        ZoneId bogotaZone = ZoneId.of("America/Bogota");
        this.createdOn = ZonedDateTime.now(bogotaZone);
    }
}
