package com.utn.ExamenMercado_DS2025.service;

import com.utn.ExamenMercado_DS2025.dto.StatsResponse;
import com.utn.ExamenMercado_DS2025.repository.DnaRecordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor  //crea constructor que falta

public class StatsServiceImpl implements StatsService {
    private final DnaRecordRepository repository; //Inyectamos la BD


    /* Se agrega constructor manual ya que con lombok presentaba errores.
    public StatsServiceImpl(DnaRecordRepository repository) {
        this.repository = repository;
    }*/
    /* Lo elimine porque da error en swagger y no nos sirve, debe devolver un true o false si es mutante o no, y dejarlo en el repository
    @Override
    public long getMutantCount() {

        return 40;  //Demo
    }

    @Override
    public long getHumanCount() {
        return 100;
    }// demo
    */
    @Override
    public long getMutantCount(){
        return repository.countByIsMutant(true);
    }

    @Override
    public long  getHumanCount(){
        return repository.countByIsMutant(false);
    }

    //Metodo que lee la BD, mejora agregada

    public StatsResponse getStats() {
        long mutantes = getMutantCount();
        long humanos  = getHumanCount();
        double ratio  = (humanos == 0) ? (mutantes > 0 ? mutantes : 0.0)
                : (double) mutantes / humanos;

        return StatsResponse.builder()
                .countMutantDna(mutantes)
                .countHumanDna(humanos)
                .ratio(ratio)
                .build();
    }
}
