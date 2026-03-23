package com.doacaoong.donation_service.controller;

import com.doacaoong.donation_service.entities.Donation;
import com.doacaoong.donation_service.service.DonationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/donations")
@RequiredArgsConstructor
public class DonationController {
    private final DonationService donationService;

    @GetMapping
    public List<Donation> findAll(){
        return donationService.findAll();
    }
    @GetMapping("/{id}")
    public Optional<Donation> findById(@PathVariable Long id){
        return donationService.findById(id);
    }
    @GetMapping("/campaign/{campaignId}")
    public List<Donation> findByCampaignId(@PathVariable Long campaignId){
        return donationService.findByCampaignId(campaignId);
    }
    @GetMapping("/user/{userId}")
    public List<Donation> findByUserId(@PathVariable Long userId){
        return donationService.findByUserId(userId);
    }
    @PostMapping
    public Donation save(@RequestBody Donation donation){
        return donationService.save(donation);
    }
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id){
        donationService.deleteById(id);
    }

}
