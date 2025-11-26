package com.utn.ExamenMercado_DS2025.service;

import com.utn.ExamenMercado_DS2025.dto.StatsResponse;
import com.utn.ExamenMercado_DS2025.repository.DnaRecordRepository;

public interface StatsService {
    long getMutantCount();
    long getHumanCount();

    StatsResponse getStats();   // <-- agregue esto nada mas como mejora
}
