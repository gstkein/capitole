package com.gsteren.capitolechallenge.service;

import java.time.LocalDateTime;

import com.gsteren.capitolechallenge.dto.PriceDTO;
import com.gsteren.capitolechallenge.entity.Price;

public interface PriceService {

	PriceDTO findPriceWithMaxPriorityWhithinDateRange(Long brandId, Long productId, LocalDateTime startDate, LocalDateTime endDate);

}
