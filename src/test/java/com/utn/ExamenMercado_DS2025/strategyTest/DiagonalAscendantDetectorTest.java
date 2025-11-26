package com.utn.ExamenMercado_DS2025.strategyTest;

import com.utn.ExamenMercado_DS2025.strategy.DiagonalAscendantDetector;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class DiagonalAscendantDetectorTest {

    private final DiagonalAscendantDetector detector = new DiagonalAscendantDetector();

    @Test
    @DisplayName("Debe detectar diagonal ascendente válida")
    void testDetectTrue() {
        char[][] dna = {
                {'T', 'G', 'T', 'A'}, // (0,3) -> Final
                {'C', 'A', 'A', 'T'}, // (1,2)
                {'T', 'A', 'A', 'G'}, // (2,1)
                {'A', 'T', 'C', 'A'}  // (3,0) -> Inicio
        };
        // Empezamos a buscar desde abajo (fila 3, col 0)
        assertTrue(detector.detect(dna, 3, 0));
    }

    @Test
    @DisplayName("Debe evitar IndexOutOfBounds hacia arriba")
    void testOutOfBoundsUpper() {
        char[][] dna = {
                {'A', 'G'},
                {'C', 'A'},
                {'T', 'C'}
        };
        // Fila 1 es muy arriba para buscar 4 hacia arriba (necesita filas 1, 0, -1, -2 -> Error)
        assertFalse(detector.detect(dna, 1, 0));
    }
}