package com.gsteren.capitolechallenge.controller;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gsteren.capitolechallenge.dto.PriceDTO;
import com.gsteren.capitolechallenge.service.PriceService;

@RestController
@RequestMapping("/prices")
public class PriceController {
	 
	private final PriceService priceService;

	
    public PriceController(PriceService priceService) {
		super();
		this.priceService = priceService;
	}

	@GetMapping("/query")
    public ResponseEntity<PriceDTO> findPrice(@RequestParam LocalDateTime date, @RequestParam Long productId, @RequestParam Long brandId) {
        PriceDTO price = priceService.findPriceWithMaxPriorityWhithinDateRange(
            brandId, productId, date, date);
            return ResponseEntity.ok(price);
    }
}
