package com.findus.repository;

import com.findus.models.AvaliacaoPortfolio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AvaliarPortfolioRepository extends JpaRepository<AvaliacaoPortfolio, Long> {
}
