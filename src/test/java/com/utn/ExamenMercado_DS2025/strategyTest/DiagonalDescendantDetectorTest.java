package com.utn.ExamenMercado_DS2025.strategyTest;

import com.utn.ExamenMercado_DS2025.strategy.DiagonalDescendantDetector;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class DiagonalDescendantDetectorTest {

    private final DiagonalDescendantDetector detector = new DiagonalDescendantDetector();

    @Test
    @DisplayName("Debe detectar diagonal descendente válida")
    void testDetectTrue() {
        char[][] dna = {
                {'A', 'G', 'T', 'C'},
                {'C', 'A', 'G', 'T'}, // (1,1)
                {'T', 'C', 'A', 'G'}, // (2,2)
                {'G', 'T', 'C', 'A'}  // (3,3)
        };
        assertTrue(detector.detect(dna, 0, 0));
    }

    @Test
    @DisplayName("Debe devolver false si no hay espacio en la esquina")
    void testOutOfBounds() {
        // Matriz 4x4
        char[][] dna = {
                {'A', 'G', 'T', 'C'},
                {'C', 'A', 'G', 'T'},
                {'T', 'C', 'A', 'G'},
                {'G', 'T', 'C', 'A'}
        };
        // Si empezamos en fila 1, columna 1 (la segunda A),
        // solo quedan 3 espacios hacia abajo-derecha. Debe dar false.
        assertFalse(detector.detect(dna, 1, 1));
    }
}