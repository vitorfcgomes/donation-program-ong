package com.doacaoong.donation_service.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.time.Instant;

@EntityListeners(AuditingEntityListener.class)// ativa o sistema de auditoria do JPA para verifica se tem campos para serem preenchidos automaticamente
@Entity
@Table(name = "tb_donations")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Donation implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double amount;

    @CreatedDate//diz ao Spring para preencher o campo com a data automaticamente
    private Instant createdAt;
    private String message;

    private Long userId;
    private Long campaignId;

}
