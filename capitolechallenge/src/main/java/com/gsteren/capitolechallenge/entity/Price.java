package com.gsteren.capitolechallenge.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "prices")
public class Price {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "brand_id")
	private Long brandId;
	
	@Column(name = "start_date")
	private LocalDateTime startDate;
	
	@Column(name = "end_date")
	private LocalDateTime endDate;
	
	@Column(name = "price_list")
	private Long priceList;
	
	@Column(name = "product_id")
	private Long productId;
	
	@Column(name = "priority")
	private Integer priority;
	
	@Column(name = "price")
	private Double price;
	
	@Column(name = "curr")
	private String curr;

}
