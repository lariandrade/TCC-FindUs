package com.findus.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
@DiscriminatorValue("cliente")
@Entity
@Table(name = "cliente")
public class Cliente extends Usuario {

    @Column(name = "userTipo", length = 50, nullable = false, insertable = false, updatable = false)
    private String userTipo;

    @Column(name = "userNomeNegocio", length = 50, nullable = false)
    private String userNomeNegocio;

    @ElementCollection
    private List<String> objetivo;


    public Cliente() {

    }

}
