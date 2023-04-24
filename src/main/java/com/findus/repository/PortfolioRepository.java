package com.findus.repository;

import com.findus.models.Portfolio;
import com.findus.models.Prestador;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PortfolioRepository extends JpaRepository<Portfolio,Long> {
    List<Portfolio> findByPrestador(Prestador prestador);

}
