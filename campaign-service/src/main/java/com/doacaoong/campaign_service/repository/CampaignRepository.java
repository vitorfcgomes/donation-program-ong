package com.doacaoong.campaign_service.repository;

import com.doacaoong.campaign_service.entities.Campaign;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CampaignRepository extends JpaRepository<Campaign, Long> {
}
