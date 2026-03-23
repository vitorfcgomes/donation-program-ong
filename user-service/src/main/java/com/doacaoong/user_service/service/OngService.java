package com.doacaoong.user_service.service;

import com.doacaoong.user_service.entities.Ong;
import com.doacaoong.user_service.exceptions.ResourceNotFoundException;
import com.doacaoong.user_service.repository.OngRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OngService {

    private final OngRepository ongRepository; //injeção de dependência

    public List<Ong> findAll(){
        return ongRepository.findAll();
    }
    public Ong findById(Long id){
        return ongRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Ong não encontrado com id: " + id));
    }
    public Ong update(Long id, Ong ong){
        Ong ong1 = ongRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Ong não encontrado com id: " + id));
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
