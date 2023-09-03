package com.gsteren.capitolechallenge;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import com.gsteren.capitolechallenge.dto.ErrorDTO;
import com.gsteren.capitolechallenge.dto.PriceDTO;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase(replace = Replace.NONE) // Use test H2
@ActiveProfiles("test") // Use test profile
//@Transactional //Not really neaded since we are using read only operations, but would be necessary otherwise.
public class PriceControllerIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testFindPrice_Success() {
        LocalDateTime date = LocalDateTime.of(2020, 6, 14, 10, 0);
        Long productId = 35455L;
        Long brandId = 1L;

        ResponseEntity<PriceDTO> responseEntity = restTemplate.getForEntity(
            "/prices/query?date={date}&productId={productId}&brandId={brandId}",
            PriceDTO.class, date, productId, brandId);

        assertEquals(200, responseEntity.getStatusCode().value());
        assertNotNull(responseEntity.getBody());
        assertEquals(brandId, responseEntity.getBody().getBrandId());
        assertEquals(productId, responseEntity.getBody().getProductId());
       
    }

    @Test
    public void testFindPrice_PriceNotFound() {
        LocalDateTime date = LocalDateTime.of(2020, 6, 14, 10, 0);
        Long productId = 12345L; // Non existing Product
        Long brandId = 1L;

        ResponseEntity<ErrorDTO> responseEntity = restTemplate.getForEntity(
            "/prices/query?date={date}&productId={productId}&brandId={brandId}",
            ErrorDTO.class, date, productId, brandId);

        assertEquals(404, responseEntity.getStatusCode().value());
        assertEquals("No valid price found", responseEntity.getBody().getError().get(0).getDetail());
        
    }

    @Test
    public void testRequestAt10AMOnDay14() {
        LocalDateTime date = LocalDateTime.of(2020, 6, 14, 10, 0);
        Long productId = 35455L;
        Long brandId = 1L;

        ResponseEntity<PriceDTO> responseEntity = restTemplate.getForEntity(
            "/prices/query?date={date}&productId={productId}&brandId={brandId}",
            PriceDTO.class, date, productId, brandId);

        assertEquals(200, responseEntity.getStatusCode().value());
        assertNotNull(responseEntity.getBody());
        assertEquals(1L, responseEntity.getBody().getId());
        assertEquals(35.50, responseEntity.getBody().getPrice());
       
    }

    @Test
    public void testRequestAt4PMOnDay14() {
        LocalDateTime date = LocalDateTime.of(2020, 6, 14, 16, 0);
        Long productId = 35455L;
        Long brandId = 1L;

        ResponseEntity<PriceDTO> responseEntity = restTemplate.getForEntity(
            "/prices/query?date={date}&productId={productId}&brandId={brandId}",
            PriceDTO.class, date, productId, brandId);

        assertEquals(200, responseEntity.getStatusCode().value());
        assertNotNull(responseEntity.getBody());
        assertEquals(2L, responseEntity.getBody().getId());
        assertEquals(25.45, responseEntity.getBody().getPrice());
       
    }

    @Test
    public void testRequestAt9PMOnDay14() {
        LocalDateTime date = LocalDateTime.of(2020, 6, 14, 21, 0);
        Long productId = 35455L;
        Long brandId = 1L;

        ResponseEntity<PriceDTO> responseEntity = restTemplate.getForEntity(
            "/prices/query?date={date}&productId={productId}&brandId={brandId}",
            PriceDTO.class, date, productId, brandId);

        assertEquals(200, responseEntity.getStatusCode().value());
        assertNotNull(responseEntity.getBody());
        assertEquals(1L, responseEntity.getBody().getId());
        assertEquals(35.50, responseEntity.getBody().getPrice());
       
    }

    @Test
    public void testRequestAt10AMOnDay15() {
        LocalDateTime date = LocalDateTime.of(2020, 6, 15, 10, 0);
        Long productId = 35455L;
        Long brandId = 1L;

        ResponseEntity<PriceDTO> responseEntity = restTemplate.getForEntity(
            "/prices/query?date={date}&productId={productId}&brandId={brandId}",
            PriceDTO.class, date, productId, brandId);

        assertEquals(200, responseEntity.getStatusCode().value());
        assertNotNull(responseEntity.getBody());
        assertEquals(3L, responseEntity.getBody().getId());
        assertEquals(30.50, responseEntity.getBody().getPrice());
       
    }

    @Test
    public void testRequestAt9PMOnDay16() {
        LocalDateTime date = LocalDateTime.of(2020, 6, 16, 21, 0);
        Long productId = 35455L;
        Long brandId = 1L;

        ResponseEntity<PriceDTO> responseEntity = restTemplate.getForEntity(
            "/prices/query?date={date}&productId={productId}&brandId={brandId}",
            PriceDTO.class, date, productId, brandId);

        assertEquals(200, responseEntity.getStatusCode().value());
        assertNotNull(responseEntity.getBody());
        assertEquals(4L, responseEntity.getBody().getId());
        assertEquals(38.95, responseEntity.getBody().getPrice());
       
    }
}
