package com.doacaoong.campaign_service.service;

import com.doacaoong.campaign_service.entities.Campaign;
import com.doacaoong.campaign_service.entities.enums.CampaignStatus;
import com.doacaoong.campaign_service.exceptions.ResourceNotFoundException;
import com.doacaoong.campaign_service.repository.CampaignRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class CampaignService {
    private final CampaignRepository campaignRepository;

    public List<Campaign> findAll(){
        return campaignRepository.findAll();
    }

    public Campaign findById(Long id){
        return campaignRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Campanha não encontrada com id " + id));
    }

    public Campaign update(Long id, Campaign campaign){
        Campaign campaignToUpdate = campaignRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Campanha não encontrada com id " + id));
        campaignToUpdate.setTitle(campaign.getTitle());
        campaignToUpdate.setDescription(campaign.getDescription());
        return campaignRepository.save(campaignToUpdate);
    }

    public Campaign updateRaised(Long id, Double amount){
        Campaign campaign =  campaignRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Campanha não encontrada com id " + id)); // busca o id da campanha e como o id retorna um optional usa-se o else throw para lançar uma exception se tiver vazio
        Double raised = campaign.getRaised() + amount; // calcula o valor arrecadado
        campaign.setRaised(raised); // salva
        if(raised >= campaign.getGoal()){ // verifica se o valor foi atingido
            campaign.setStatus(CampaignStatus.COMPLETED); // se foi atingido muda o valor para completo
        }
        return campaignRepository.save(campaign); // salva a campanha atualizada e retorna o objeto campaign salvo e atualizado
    }

    public Campaign save(Campaign campaign){
        return campaignRepository.save(campaign);
    }

    public void deleteById(Long id){
        campaignRepository.deleteById(id);
    }
}
