package com.gsteren.capitolechallenge.service.impl;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gsteren.capitolechallenge.dto.PriceDTO;
import com.gsteren.capitolechallenge.entity.Price;
import com.gsteren.capitolechallenge.repository.PriceRepository;
import com.gsteren.capitolechallenge.service.PriceService;

@Service
public class PriceServiceImpl implements PriceService {
	
	 @Autowired
	 private PriceRepository priceRepository;
	 
	 @Autowired
	 private ObjectMapper objectMapper;
	 
 	@Override
    public PriceDTO findPriceWithMaxPriorityWhithinDateRange(Long brandId, Long productId, LocalDateTime startDate, LocalDateTime endDate) {
        Optional<Price> oprice = priceRepository.findPriceWithMaxPriorityWhithinDateRange(
            brandId, productId, startDate, endDate);
        
        PriceDTO priceDTO = oprice.map(price -> objectMapper.convertValue(price, PriceDTO.class))
                .orElseThrow(() -> new NoSuchElementException("No se encontró un precio válido"));

        
        return priceDTO;
       
    }

}
