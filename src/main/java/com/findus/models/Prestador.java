package com.findus.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@DiscriminatorValue("prestador")
@Entity
@Table(name="prestador")
public class Prestador extends Usuario{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userID", nullable = false)
    private Long id_Usuario;

    @Column(name = "userTipo", length = 50, nullable = false, insertable=false, updatable=false)
    private String userTipo;

    public Prestador() {

    }
}
