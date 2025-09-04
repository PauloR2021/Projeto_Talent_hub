package com.prsoftware.talent_hub.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.prsoftware.talent_hub.dto.VagaDTO;
import com.prsoftware.talent_hub.entity.Vaga;
import com.prsoftware.talent_hub.repository.VagaRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class VagaService {

    private final VagaRepository vagaRepository;

    public Vaga criarVagas(VagaDTO dto){
        return vagaRepository.save(Vaga.builder()
            .titulo(dto.getTitulo())
            .descricao(dto.getDescricao())
            .requisitos(dto.getRequisitos())
            .build());

    }

    public List<Vaga> listarVagas(){return vagaRepository.findAll();}

    public Vaga buscarPorId(Long id){
        return vagaRepository.findById(id).orElseThrow(() -> new RuntimeException("Vaga não encontrada"));
    }

}
