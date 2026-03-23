package com.doacaoong.donation_service.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DonationEvent implements Serializable {

    private Long donationId;
    private Long userId;
    private Long campaignId;
    private Double amount;
}
