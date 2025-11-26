package com.utn.ExamenMercado_DS2025.strategyTest;

import com.utn.ExamenMercado_DS2025.strategy.HorizontalDetector;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class HorizontalDetectorTest {

    private final HorizontalDetector detector = new HorizontalDetector();

    @Test
    @DisplayName("Debe detectar secuencia horizontal válida")
    void testDetectTrue() {
        char[][] dna = {
                {'A', 'A', 'A', 'A', 'C'}, // 4 A's desde col 0
                {'C', 'G', 'T', 'A', 'G'}
        };
        // Probamos fila 0, columna 0
        assertTrue(detector.detect(dna, 0, 0));
    }

    @Test
    @DisplayName("Debe devolver false si la secuencia está rota")
    void testDetectFalse() {
        char[][] dna = {
                {'A', 'A', 'G', 'A', 'C'}, // A-A-G-A (rota)
                {'C', 'G', 'T', 'A', 'G'}
        };
        assertFalse(detector.detect(dna, 0, 0));
    }

    @Test
    @DisplayName("Debe devolver false si no hay espacio (fuera de limites)")
    void testOutOfBounds() {
        char[][] dna = {
                {'C', 'G', 'A', 'A', 'A'}
        };
        // En la columna 2 ('A') solo quedan 3 letras (indices 2,3,4).
        // Necesita 4. No debe dar error, solo false.
        assertFalse(detector.detect(dna, 0, 2));
    }
}
