package com.doacaoong.donation_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing //habilita o sistema de auditoria do Spring JPA globalmente na aplicação
@SpringBootApplication
public class DonationServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(DonationServiceApplication.class, args);
	}

}
