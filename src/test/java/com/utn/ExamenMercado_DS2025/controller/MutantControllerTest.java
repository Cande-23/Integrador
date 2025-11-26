package com.utn.ExamenMercado_DS2025.controller;

import com.utn.ExamenMercado_DS2025.dto.DnaRequest;
import com.utn.ExamenMercado_DS2025.service.MutantService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

public class MutantControllerTest {

    @Test
    public void testCheckMutant_whenMutant_returns200() {
        MutantService mockService = Mockito.mock(MutantService.class);
        Mockito.when(mockService.isMutant(Mockito.any())).thenReturn(true);

        MutantController controller = new MutantController(mockService);
        DnaRequest request = DnaRequest.builder()
                .dna(new String[]{"ATGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG"})
                .build();

        ResponseEntity<Void> response = controller.checkMutant(request);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testCheckMutant_whenNotMutant_returns403() {
        MutantService mockService = Mockito.mock(MutantService.class);
        Mockito.when(mockService.isMutant(Mockito.any())).thenReturn(false);

        MutantController controller = new MutantController(mockService);
        DnaRequest request = DnaRequest.builder() // uso el patrón Builder
                .dna(new String[]{"ATGCGA", "CAGTGC", "TTATTT", "AGACGG", "GCGTCA", "TCACTG"})
                .build();

        ResponseEntity<Void> response = controller.checkMutant(request);

        assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());
    }
}