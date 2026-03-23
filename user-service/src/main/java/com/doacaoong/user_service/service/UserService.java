package com.doacaoong.user_service.service;

import com.doacaoong.user_service.entities.User;
import com.doacaoong.user_service.exceptions.ResourceNotFoundException;
import com.doacaoong.user_service.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public List<User> findAll(){ // metodo que recebe uma lista de users e coloca todos nessa lista
        return userRepository.findAll();
    }
    public User findById(Long id){ // Optional para dizer que pode ou não receber um valor
        return userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado com id " + id));
    }
    public User update(Long id, User user){
        User user1 = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado com id: " + id));
        user1.setName(user.getName());
        user1.setEmail(user.getEmail());
        user1.setPhone(user.getPhone());
        return userRepository.save(user1);
    }
    public User save(User user){ // salva e retorna o user com id gerado
        return userRepository.save(user);
    }
    public void deleteById(Long id){ // deleta pelo id
        userRepository.deleteById(id);
    }
}
