package com.utn.ExamenMercado_DS2025.strategyTest;

import com.utn.ExamenMercado_DS2025.strategy.VerticalDetector;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class VerticalDetectorTest {

    private final VerticalDetector detector = new VerticalDetector();

    @Test
    @DisplayName("Debe detectar secuencia vertical válida")
    void testDetectTrue() {
        char[][] dna = {
                {'A', 'G', 'T'},
                {'A', 'C', 'G'},
                {'A', 'T', 'C'},
                {'A', 'G', 'T'}, // 4 A's en columna 0
                {'C', 'C', 'C'}
        };
        assertTrue(detector.detect(dna, 0, 0));
    }

    @Test
    @DisplayName("Debe devolver false si no coincide")
    void testDetectFalse() {
        char[][] dna = {
                {'A', 'G'},
                {'A', 'C'},
                {'G', 'T'}, // Rota
                {'A', 'G'}
        };
        assertFalse(detector.detect(dna, 0, 0));
    }

    @Test
    @DisplayName("Debe manejar límites verticales")
    void testOutOfBounds() {
        char[][] dna = {
                {'A'}, {'A'}, {'A'}
                // Solo 3 filas, imposible encontrar vertical de 4
        };
        assertFalse(detector.detect(dna, 0, 0));
    }
}