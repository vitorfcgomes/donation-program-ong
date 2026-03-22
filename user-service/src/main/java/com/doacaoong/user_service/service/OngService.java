package com.doacaoong.user_service.service;

import com.doacaoong.user_service.entities.Ong;
import com.doacaoong.user_service.repository.OngRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OngService {

    private final OngRepository ongRepository; //injeção de dependência

    public List<Ong> findAll(){
        return ongRepository.findAll();
    }
    public Optional<Ong> findById(Long id){
        return ongRepository.findById(id);
    }
    public Ong update(Long id, Ong ong){
        Ong ong1 = ongRepository.findById(id).orElseThrow();
        ong1.setName(ong.getName());
        ong1.setDescription(ong.getDescription());
        return ongRepository.save(ong1);
    }
    public Ong save(Ong ong){
        return ongRepository.save(ong);
    }
    public void deleteById(Long id){
        ongRepository.deleteById(id);
    }
}
