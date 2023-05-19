package com.findus.repository;

import com.findus.models.ContatoPrestador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContatoPrestadorRepository extends JpaRepository<ContatoPrestador,Long> {

}
