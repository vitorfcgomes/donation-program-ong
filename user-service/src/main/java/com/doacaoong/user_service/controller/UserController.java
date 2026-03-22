package com.doacaoong.user_service.controller;

import com.doacaoong.user_service.entities.User;
import com.doacaoong.user_service.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping
    public List<User> list(){
        return userService.findAll(); // busca todos os users
    }

    @GetMapping("/{id}")
    public Optional<User> findById(@PathVariable Long id){ // busca por id
        return userService.findById(id);
    }

    @PutMapping("/{id}")
    public User update(@PathVariable Long id, @RequestBody User user){
        return userService.update(id, user);
    }

    @PostMapping
    public User save(@RequestBody User user){ // salva uma request post no banco
        return userService.save(user);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id){
        userService.deleteById(id);
    }
}
