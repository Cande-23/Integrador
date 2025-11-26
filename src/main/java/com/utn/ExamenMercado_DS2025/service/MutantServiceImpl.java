package com.utn.ExamenMercado_DS2025.service;

import com.utn.ExamenMercado_DS2025.entity.DnaRecord;
import com.utn.ExamenMercado_DS2025.mutantLogic.MutantDetector;
import com.utn.ExamenMercado_DS2025.repository.DnaRecordRepository;
import com.utn.ExamenMercado_DS2025.util.DnaHasher;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MutantServiceImpl implements MutantService {
    private final MutantDetector detector;
    private final DnaRecordRepository repository;

    @Override
    public boolean isMutant(String[] dna) {
        String hash = DnaHasher.sha256(dna);

        return repository.findByDnaHash(hash)
                .map(record -> record.isMutant())   // cache hit
                .orElseGet(() -> {
                    boolean result = detector.isMutant(dna);
                    repository.save(new DnaRecord(hash, result));
                    return result;
                });
    }


}
