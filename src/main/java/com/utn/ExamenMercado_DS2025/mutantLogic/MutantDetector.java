package com.utn.ExamenMercado_DS2025.mutantLogic;

import com.utn.ExamenMercado_DS2025.strategy.SequenceDetector;
import com.utn.ExamenMercado_DS2025.strategy.SequenceDetectorFactory;
import com.utn.ExamenMercado_DS2025.validator.DnaValidationChain;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
@Slf4j
@RequiredArgsConstructor
public class MutantDetector {

    private static final int REQUIRED_SEQUENCES = 2;
    private final DnaValidationChain validationChain;
    private final SequenceDetectorFactory detectorFactory;

    public boolean isMutant(String[] dna) {
        log.info("Iniciando analisis de ADN..............");
        validationChain.validate(dna);

        char[][] matrix = toMatrix(dna);
        int found = 0;

        List<SequenceDetector> detectors = detectorFactory.allDetectors();

        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[row].length; col++) {

                for (SequenceDetector detector : detectors) {

                    // Detecta secuencia solo si empieza en este punto exacto
                    if (detector.detect(matrix, row, col)) {

                        found++;
                        log.debug("Secuencia encontrada con {} en ({},{})", detector.name(), row, col);

                        if (found >= REQUIRED_SEQUENCES) return true;

                        // rompe este for para evitar detectar la MISMA secuencia más de una vez
                        break;
                    }
                }
            }
        }

        log.info("No es mutante ({} secuencias encontradas)", found);
        return false;
    }


    private char[][] toMatrix(String[] dna) {
        int n = dna.length;
        char[][] m = new char[n][n];
        for (int i = 0; i < n; i++) m[i] = dna[i].toCharArray();
        return m;
    }
}