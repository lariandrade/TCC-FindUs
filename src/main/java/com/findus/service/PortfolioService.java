package com.findus.service;

import com.findus.exception.PortfolioNotFoundException;
import com.findus.models.Portfolio;
import com.findus.repository.PortfolioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PortfolioService {

    @Autowired
    private PortfolioRepository portfolioRepository;

    public Portfolio findById(Long id) {
        Optional<Portfolio> portfolio = portfolioRepository.findById(id);
        return portfolio.orElseThrow(() -> new PortfolioNotFoundException(id));
    }


}
