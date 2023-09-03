package com.gsteren.capitolechallenge;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.gsteren.capitolechallenge.controller.PriceController;
import com.gsteren.capitolechallenge.dto.PriceDTO;
import com.gsteren.capitolechallenge.service.PriceService;

@ExtendWith(MockitoExtension.class)
public class PriceControllerUnitTest {

    @Mock
    private PriceService priceService;

    @InjectMocks
    private PriceController priceController;

    @Test
    public void testFindPrice_Success() {
        LocalDateTime date = LocalDateTime.of(2020, 6, 14, 10, 0);
        Long productId = 35455L;
        Long brandId = 1L;
        PriceDTO expectedPrice = new PriceDTO(/* valores esperados */);
        when(priceService.findPriceWithMaxPriorityWhithinDateRange(brandId, productId, date, date))
            .thenReturn(expectedPrice);

        ResponseEntity<PriceDTO> response = priceController.findPrice(date, productId, brandId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedPrice, response.getBody());
    }

    @Test
    public void testFindPrice_PriceNotFound() {
        LocalDateTime date = LocalDateTime.of(2020, 6, 14, 10, 0);
        Long productId = 35455L;
        Long brandId = 1L;
        when(priceService.findPriceWithMaxPriorityWhithinDateRange(brandId, productId, date, date))
            .thenThrow(new NoSuchElementException("No se encontró un precio válido"));

        assertThrows(NoSuchElementException.class, () -> priceController.findPrice(date, productId, brandId));
    }
}
