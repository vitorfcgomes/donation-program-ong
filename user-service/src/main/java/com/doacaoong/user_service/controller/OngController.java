package com.doacaoong.user_service.controller;

import com.doacaoong.user_service.entities.Ong;
import com.doacaoong.user_service.service.OngService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/ongs")
@RequiredArgsConstructor
public class OngController {
    private final OngService ongService;

    @GetMapping
    public List<Ong> list(){
        return ongService.findAll();
    }
    @GetMapping("/{id}")
    public Ong findById(@PathVariable Long id){
        return ongService.findById(id);
    }
    @PutMapping("/{id}")
    public Ong update(@PathVariable Long id, @RequestBody Ong ong){
        return ongService.update(id, ong);
    }
    @PostMapping
    public Ong save(@RequestBody Ong ong){
        return ongService.save(ong);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id){ //usamos path variable para extrair o id do body da request
        ongService.deleteById(id);
    }
}
