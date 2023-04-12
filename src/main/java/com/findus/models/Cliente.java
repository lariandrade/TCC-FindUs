package com.findus.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@DiscriminatorValue("cliente")
@Entity
@Table(name="cliente")
public class Cliente extends Usuario{


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userID", nullable = false)
    private Long id_Usuario;

    @Column(name = "teste", length = 50)
    private String teste;

    public Cliente() {

    }

}