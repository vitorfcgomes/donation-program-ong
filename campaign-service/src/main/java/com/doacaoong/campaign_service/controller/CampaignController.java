package com.doacaoong.campaign_service.controller;

import com.doacaoong.campaign_service.entities.Campaign;
import com.doacaoong.campaign_service.service.CampaignService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/campaigns")
@RequiredArgsConstructor
public class CampaignController {
    private final CampaignService campaignService;

    @GetMapping
    public List<Campaign> list(){
        return  campaignService.findAll();
    }
    @GetMapping("/{id}")
    public Optional<Campaign> findById(@PathVariable Long id){
        return campaignService.findById(id);
    }
    @PutMapping("/{id}")
    public Campaign update(@PathVariable Long id, @RequestBody Campaign campaign){
        return campaignService.update(id, campaign);
    }
    @PatchMapping("/{id}/raised")
    public Campaign updateRaised(@PathVariable Long id, @RequestParam Double amount){
        return campaignService.updateRaised(id, amount);
    }
    @PostMapping
    public Campaign save(@RequestBody Campaign campaign){
        return campaignService.save(campaign);
    }
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id){
        campaignService.deleteById(id);
    }

}
