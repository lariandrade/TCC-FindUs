package com.findus.service;

import com.findus.models.AvaliacaoPortfolio;
import com.findus.repository.AvaliarPortfolioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AvaliarPortfolioService {

    @Autowired
    private AvaliarPortfolioRepository avaliarPortfolioRepository;


    public AvaliacaoPortfolio save(AvaliacaoPortfolio avaliacaoPortfolio) {

        return avaliarPortfolioRepository.save(avaliacaoPortfolio);

    }
}
