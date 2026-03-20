package com.doacaoong.user_service.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Entity
@Table(name="tb_ong")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ong implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id // chave primária
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String cnpj;
    private String description;
    private String category;
}
