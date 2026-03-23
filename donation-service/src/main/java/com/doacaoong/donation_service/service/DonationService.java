package com.doacaoong.donation_service.service;

import com.doacaoong.donation_service.entities.Donation;
import com.doacaoong.donation_service.event.DonationEvent;
import com.doacaoong.donation_service.repository.DonationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DonationService {
    private final DonationRepository donationRepository;
    private final RabbitTemplate rabbitTemplate;

    public List<Donation> findAll(){
        return donationRepository.findAll();
    }
    public Optional<Donation> findById(Long id){
        return donationRepository.findById(id);
    }
    public List<Donation> findByCampaignId(Long campaignId){
        return donationRepository.findByCampaignId(campaignId);
    }
    public List<Donation> findByUserId(Long userId){
        return donationRepository.findByUserId(userId);
    }
    public Donation save(Donation donation){
        Donation savedDonation = donationRepository.save(donation); // salva a donation no banco e retorna o objeto com o id gerado

        DonationEvent event = new DonationEvent( //cria o evento com os dados da donation salva
                savedDonation.getId(),
                savedDonation.getUserId(),
                savedDonation.getCampaignId(),
                savedDonation.getAmount()
        );
        rabbitTemplate.convertAndSend("donation.exchange", "donation.created", event); // publica o event na fila, exchange recebe a mensagem e decide para qual fila encaminhar, donation created define o tipo do evento, event é a mensagem em si
        return savedDonation; // retorna a doação salva com o id gerado
    }
    public void deleteById(Long id){
        donationRepository.deleteById(id);
    }
}
