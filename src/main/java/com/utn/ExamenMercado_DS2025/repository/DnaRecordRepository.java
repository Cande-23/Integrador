package com.utn.ExamenMercado_DS2025.repository;

import com.utn.ExamenMercado_DS2025.entity.DnaRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface DnaRecordRepository extends JpaRepository<DnaRecord, Long> {

    // Spring Data implementa esto automáticamente por el nombre del método
    Optional<DnaRecord> findByDnaHash(String dnaHash);

    // Para las estadísticas (count_mutant_dna / count_human_dna)
    long countByIsMutant(boolean isMutant);
}