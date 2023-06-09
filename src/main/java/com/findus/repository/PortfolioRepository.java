package com.findus.repository;

import com.findus.models.Portfolio;
import com.findus.models.Prestador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PortfolioRepository extends JpaRepository<Portfolio, Long> {
    List<Portfolio> findByPrestador(Prestador prestador);
    List<Portfolio> deleteByPrestador(Prestador prestador);

}
