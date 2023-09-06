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
	
	 private final PriceRepository priceRepository;
	 
	 private final ObjectMapper objectMapper;
	 
	 
 	public PriceServiceImpl(PriceRepository priceRepository, ObjectMapper objectMapper) {
		super();
		this.priceRepository = priceRepository;
		this.objectMapper = objectMapper;
	}

	@Override
    public PriceDTO findPriceWithMaxPriorityWhithinDateRange(Long brandId, Long productId, LocalDateTime startDate, LocalDateTime endDate) {
        Optional<Price> oprice = priceRepository.findPriceWithMaxPriorityWhithinDateRange(
            brandId, productId, startDate, endDate);
        
        PriceDTO priceDTO = oprice.map(price -> objectMapper.convertValue(price, PriceDTO.class))
                .orElseThrow(() -> new NoSuchElementException("No valid price found")); //TODO use messages and internationalization (out of scope)

        
        return priceDTO;
       
    }

}
