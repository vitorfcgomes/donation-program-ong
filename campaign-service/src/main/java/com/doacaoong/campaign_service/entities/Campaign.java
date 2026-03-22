package com.doacaoong.campaign_service.entities;

import com.doacaoong.campaign_service.entities.enums.CampaignStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name="tb_campaign")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Campaign implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;
    private Double goal;
    private Double raised;
    private LocalDate deadline;

    @Enumerated(EnumType.STRING)
    private CampaignStatus status;
    private Long ongId;
}
