package com.utn.ExamenMercado_DS2025.controller;


import com.utn.ExamenMercado_DS2025.dto.StatsResponse;
import com.utn.ExamenMercado_DS2025.service.StatsService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(StatsController.class)
class StatsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StatsService statsService;

    @Test
    void getStatsReturnsCorrectNumbers() throws Exception {

        StatsResponse mockResponse = StatsResponse.builder()
                .countMutantDna(40)
                .countHumanDna(100)
                .ratio(0.4)
                .build();

        when(statsService.getStats()).thenReturn(mockResponse);

        mockMvc.perform(get("/stats"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.countMutantDna").value(40))
                .andExpect(jsonPath("$.countHumanDna").value(100))
                .andExpect(jsonPath("$.ratio").value(0.4));
    }
    //arreglado
}
