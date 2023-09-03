package com.gsteren.capitolechallenge.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class PriceDTO {
	private Long id;
	private Long brandId;
	private LocalDateTime startDate;
	private LocalDateTime endDate;
	private Long priceList;
	private Long productId;
	private Integer priority;
	private Double price;
	private String curr;
}