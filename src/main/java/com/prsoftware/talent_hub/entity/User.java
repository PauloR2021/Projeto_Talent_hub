package com.prsoftware.talent_hub.entity;

import javax.management.relation.Role;

import com.fasterxml.jackson.databind.annotation.EnumNaming;

import jakarta.persistence.Column;
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
@Table(name = "users")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder

public class User {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false) private String nome;
    @Column(nullable = false, unique = true) private String email;
    @Column(nullable = false) private String senha;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false) private Role role;
    public enum Role {COLABORADOR, GESTOR}
}
