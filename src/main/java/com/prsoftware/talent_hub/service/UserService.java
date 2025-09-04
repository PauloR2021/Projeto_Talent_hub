package com.prsoftware.talent_hub.service;

import org.springframework.stereotype.Service;

import com.prsoftware.talent_hub.entity.User;
import com.prsoftware.talent_hub.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public User autenticar(String email, String senha){
        return userRepository.findByEmail(email)
            .filter(u -> u.getSenha().equals(senha))
            .orElse(null);
    }


}
