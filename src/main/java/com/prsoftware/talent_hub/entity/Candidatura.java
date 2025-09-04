package com.prsoftware.talent_hub.entity;

import java.io.ObjectInputFilter.Status;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
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
@Table(name = "candidaturas")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Candidatura {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)@JoinColumn(name = "vaga_id", nullable = false)
    private Vaga vaga;
    @ManyToOne(fetch = FetchType.LAZY)@JoinColumn(name = "user_id", nullable = false)
    private User user;
    @Column(nullable = false) private String curriculo;
    @Enumerated(EnumType.STRING) @Column(nullable = false) private Status status;
    @Column(name = "data_candidaturas", nullable = false) private LocalDateTime dataCandidatura;
    public enum Status {EM_ANALISE,APROVADO,RECUSADO}

    @PrePersist
    public void prePersist(){this.dataCandidatura = LocalDateTime.now(); if(status == null) status=Status.EM_ANALISE; }

}
