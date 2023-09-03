package com.gsteren.capitolechallenge.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.gsteren.capitolechallenge.entity.Price;

@Repository
public interface PriceRepository extends JpaRepository<Price, Long> {
    
	List<Price> findByBrandIdAndProductIdAndStartDateBeforeAndEndDateAfterOrderByPriorityDesc(
        Long brandId, Long productId, LocalDateTime startDate, LocalDateTime endDate);
    
    Optional<Price> findFirstByBrandIdAndProductIdAndStartDateBeforeAndEndDateAfterOrderByPriorityDesc(
    		Long brandId, Long productId, LocalDateTime startDate, LocalDateTime endDate);

    @Query("SELECT p FROM Price p WHERE p.brandId = :brandId AND p.productId = :productId AND p.startDate <= :startDate AND p.endDate >= :endDate ORDER BY p.priority DESC LIMIT 1")
    Optional<Price> findPriceWithMaxPriorityWhithinDateRange(Long brandId, Long productId, LocalDateTime startDate, LocalDateTime endDate);

}