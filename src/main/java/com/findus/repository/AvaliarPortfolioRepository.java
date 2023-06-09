package com.findus.repository;

import com.findus.models.AvaliacaoPortfolio;
import com.findus.models.Portfolio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AvaliarPortfolioRepository extends JpaRepository<AvaliacaoPortfolio, Long> {
    List<AvaliacaoPortfolio> findByAvaIdProjeto(Long projetoId);

    List<AvaliacaoPortfolio> findByPortfolio(Portfolio portfolio);

}
