package com.prsoftware.talent_hub.controller;

import java.nio.file.Files;
import java.nio.file.Paths;

import org.aspectj.lang.annotation.RequiredTypes;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonCreator.Mode;
import com.prsoftware.talent_hub.dto.VagaDTO;
import com.prsoftware.talent_hub.entity.Candidatura;
import com.prsoftware.talent_hub.entity.User;
import com.prsoftware.talent_hub.service.CandidaturaService;
import com.prsoftware.talent_hub.service.VagaService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class VagaViewController {

    private final VagaService vagaService;
    private final CandidaturaService candidaturaService;

    @GetMapping("/vagas")
    public String listarVagas(Model model){
        model.addAttribute("vagas", vagaService.listarVagas());
        return "vagas/listar";
    }

    @GetMapping("/vagas/cadastrar")
    public String exibirFormulario(){return "vagas/cadastrar";}

    @PostMapping("/vagas/cadastrar")
    public String salvarVagas(@RequestParam String titulo, @RequestParam String descricao, @RequestParam String requisitos){
        vagaService.criarVagas(new VagaDTO(){{setTitulo(titulo);setDescricao(descricao);setRequisitos(requisitos);}});
        return "redirect:/vagas";
    }

    @GetMapping("/vagas/candidatar/id")
    public String exibirFomularioCandidatura(@PathVariable Long id,Model model, HttpSession session){
        model.addAttribute("vaga", vagaService.buscarPorId(id));
        model.addAttribute("usuario", session.getAttribute("usuario"));
        return "vagas/candidatar";

    }

    @PostMapping("/vagas/candidatar")
    public String salvarCandidatura(@RequestParam Long vagaId, @RequestParam("curriculo") MultipartFile file, HttpSession session) throws Exception {
        User usuario = (User) session.getAttribute("usuario");
        if(usuario==null) return "redirect:/login";

        String pasta="uploads/";
        Files.createDirectories(Paths.get(pasta));
        String caminho=pasta+usuario.getId()+"_"+file.getOriginalFilename();
        file.transferTo(Paths.get(caminho));

        Candidatura c = new Candidatura();
        c.setUser(usuario); c.setVaga(vagaService.buscarPorId(vagaId)); c.setCurriculo(caminho);
        candidaturaService.salvar(c);

        return "redirect:/vagas";
    }


}
