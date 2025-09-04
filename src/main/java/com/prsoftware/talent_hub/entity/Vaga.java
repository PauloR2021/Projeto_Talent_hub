package com.prsoftware.talent_hub.entity;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "vagas")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder

public class Vaga {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false) private String titulo;
    @Column(columnDefinition = "TEXT", nullable = false) private String descricao;
    @Column(columnDefinition = "TEXT", nullable = false) private String requisitos;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false) private Status status;
    @Column(name="data_criacao", nullable = false) private LocalDateTime dataCriacao;
    @OneToMany(mappedBy="vaga", cascade=CascadeType.ALL, orphanRemoval=true)
    private Set<Candidatura> candidaturas = new HashSet<>();
    public enum Status { ABERTA, FECHADA }

    @PrePersist
    public void prePersist() { this.dataCriacao = LocalDateTime.now(); if(status==null) status=Status.ABERTA; }
}
