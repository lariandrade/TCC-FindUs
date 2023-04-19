package com.findus.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@DiscriminatorValue("prestador")
@Entity
@Table(name="prestador")
public class Prestador extends Usuario{


    @Column(name = "userTipo", length = 50, nullable = false, insertable=false, updatable=false)
    private String userTipo;

    @OneToMany(mappedBy = "prestador")
    private List<Portfolio> projetos;

    public Prestador() {

    }
}
