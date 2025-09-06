package com.prsoftware.talent_hub.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.prsoftware.talent_hub.entity.User;
import com.prsoftware.talent_hub.service.UserService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;

    @GetMapping("/login")
    public String loginForm(){return "login";}

    @PostMapping("/login")
    public String login(@RequestParam String email, @RequestParam String senha, HttpSession session){
        User user = userService.autenticar(email, senha);
        if(user!=null){session.setAttribute("usuario", user); return "redirect:/vagas";}
        return "login";
    }

    @GetMapping("/logout")
    public String logout (HttpSession session){session.invalidate(); return "redirect:/login";}

}
