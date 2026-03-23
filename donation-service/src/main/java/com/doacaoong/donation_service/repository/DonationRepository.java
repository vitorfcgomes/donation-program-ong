package com.doacaoong.donation_service.repository;

import com.doacaoong.donation_service.entities.Donation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DonationRepository extends JpaRepository<Donation, Long> {
    List<Donation> findByCampaignId(Long campaignId);
    List<Donation> findByUserId(Long userId);
}
