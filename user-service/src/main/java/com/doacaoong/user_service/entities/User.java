package com.doacaoong.user_service.entities;

import com.doacaoong.user_service.entities.enums.UserType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.io.Serial;
import java.io.Serializable;

@Entity // diz ao Hibernate que é uma tabela
@Table(name="tb_users") // define o nome da tabela no banco
@Data // gera os getters e setters
@NoArgsConstructor // gera o construtor vazio
@AllArgsConstructor // gera o construtor com todos os campos

public class User implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id // chave primária
    @GeneratedValue(strategy = GenerationType.IDENTITY) // strategy para definir como o banco vai definir o valor do id e identity para o banco gerar automaticamente com autoincremento
    private Long id;

    private String name;
    private String email;
    private String phone;

    @Enumerated(EnumType.STRING) // salva o enum comoo texto no banco
    private UserType type;


}
