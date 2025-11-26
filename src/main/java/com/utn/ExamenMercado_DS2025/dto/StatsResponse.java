package com.utn.ExamenMercado_DS2025.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StatsResponse {
    private long countMutantDna;
    private long countHumanDna;
    private double ratio; //mutantes /humanos

}
