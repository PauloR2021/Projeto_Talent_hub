package com.prsoftware.talent_hub.service;

import org.springframework.stereotype.Service;

import com.prsoftware.talent_hub.entity.Candidatura;
import com.prsoftware.talent_hub.repository.CandidaturaRepository;

import lombok.RequiredArgsConstructor;



@Service
@RequiredArgsConstructor
public class CandidaturaService {
    private final CandidaturaRepository candidaturaRepository;

    public Candidatura salvar(Candidatura c) { return candidaturaRepository.save(c); }

}
