package com.utn.ExamenMercado_DS2025.mutantLogic;

// Importamos las clases de tu paquete principal

import com.utn.ExamenMercado_DS2025.exception.InvalidDnaException;
import com.utn.ExamenMercado_DS2025.strategy.*;
import com.utn.ExamenMercado_DS2025.validator.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

// Importaciones estáticas para las aserciones
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MutantDetectorTest {

    private MutantDetector mutantDetector;

    @BeforeEach
    void setUp() {
        // VALIDADORES
        List<DnaValidator> validators = Arrays.asList(
                new NotNullValidator(),
                new SquareMatrizValidator(),
                new AllowedCharsValidator()
        );
        DnaValidationChain chain = new DnaValidationChain(validators);

        // DETECTORES
        List<SequenceDetector> detectors = Arrays.asList(
                new HorizontalDetector(),
                new VerticalDetector(),
                new DiagonalDescendantDetector(),
                new DiagonalAscendantDetector()
        );
        SequenceDetectorFactory factory = new SequenceDetectorFactory(detectors);

        // DETECTOR
        mutantDetector = new MutantDetector(chain, factory);
    }

    // ============================
    // MUTANTES (TRUE)
    // ============================

    @Test
    @DisplayName("Mutante 1: Dos secuencias horizontales")
    void testMutantHorizontal() {
        String[] dna = {
                "AAAAAA",
                "CAGTGC",
                "TTATGT",
                "AGAAGG",
                "CCCCTA",
                "TCACTG"
        };
        assertTrue(mutantDetector.isMutant(dna));
    }

    @Test
    @DisplayName("Mutante 2: Dos secuencias verticales")
    void testMutantVertical() {
        String[] dna = {
                "ATGCGA",
                "ATGTGA",
                "ATTTGA",
                "AGAAGA",
                "CCTCTA",
                "TCACTG"
        };
        assertTrue(mutantDetector.isMutant(dna));
    }

    @Test
    @DisplayName("Mutante 3: Diagonal descendente (clásico ejemplo ML)")
    void testMutantDiagonalDown() {
        String[] dna = {
                "ATGCGA",
                "CAGTGC",
                "TTATGT",
                "AGAAGG",
                "CCCCTA",
                "TCACTG"
        };
        assertTrue(mutantDetector.isMutant(dna));
    }

    @Test
    @DisplayName("Mutante 4: Diagonal ascendente")
    void testMutantDiagonalUp() {
        String[] dna = {
                "ATGCGA",
                "CAGTGC",
                "TTAAGT",
                "AGTAGG",
                "CTCCTA",
                "TCACTG"
        };
        assertTrue(mutantDetector.isMutant(dna));
    }

    @Test
    @DisplayName("Mutante 5: Full mutante (todas direcciones posibles)")
    void testFullMutant() {
        String[] dna = {
                "AAAA",
                "AAAA",
                "AAAA",
                "AAAA"
        };
        assertTrue(mutantDetector.isMutant(dna));
    }

    // ============================
    // HUMANOS (FALSE)
    // ============================

    @Test
    @DisplayName("Humano 1: sin secuencias")
    void testHumanNoSequences() {
        String[] dna = {
                "ATGC",
                "CAGT",
                "TGAT",
                "GACT"
        };
        assertFalse(mutantDetector.isMutant(dna));
    }

    @Test
    @DisplayName("Humano 2: solo 1 horizontal")
    void testHumanOneHorizontal() {
        String[] dna = {
                "AAAA",
                "CAGT",
                "TGAT",
                "GACT"
        };
        assertFalse(mutantDetector.isMutant(dna));
    }

    @Test
    @DisplayName("Humano 3: solo 1 vertical")
    void testHumanOneVertical() {
        String[] dna = {
                "ATGC",
                "ACGT",
                "AGTC",
                "ATCG"
        };
        assertFalse(mutantDetector.isMutant(dna));
    }

    @Test
    @DisplayName("Humano 4: solo 1 diagonal")
    void testHumanOneDiagonal() {
        String[] dna = {
                "ATGC",
                "CAGT",
                "TGCT",
                "GACT"
        };
        assertFalse(mutantDetector.isMutant(dna));
    }

    @Test
    @DisplayName("Humano 5: sin secuencias mutantes")
    void testHumanThreeLetters() {
        String[] dna = {
                "ATGC",
                "CAGT",
                "TTAT",
                "AGAC"
        };
        assertFalse(mutantDetector.isMutant(dna));
    }



    // ============================
    // CASOS INVÁLIDOS (EXCEPCIONES)
    // ============================

    @Test
    @DisplayName("Error: ADN nulo")
    void testInvalidNull() {
        assertThrows(InvalidDnaException.class,
                () -> mutantDetector.isMutant(null));
    }

    @Test
    @DisplayName("Error: ADN vacío")
    void testInvalidEmpty() {
        assertThrows(InvalidDnaException.class,
                () -> mutantDetector.isMutant(new String[]{}));
    }

    @Test
    @DisplayName("Error: matriz no cuadrada")
    void testInvalidNotSquare() {
        String[] dna = { "ATGC", "CAGT", "TTAT" };
        assertThrows(InvalidDnaException.class,
                () -> mutantDetector.isMutant(dna));
    }

    @Test
    @DisplayName("Error: caracteres inválidos")
    void testInvalidCharacters() {
        String[] dna = { "ATGC", "AXGT", "TTAT", "AGAC" };
        assertThrows(InvalidDnaException.class,
                () -> mutantDetector.isMutant(dna));
    }

    @Test
    @DisplayName("Error: números en el ADN")
    void testInvalidNumbers() {
        String[] dna = { "1234", "5678", "9012", "3456" };
        assertThrows(InvalidDnaException.class,
                () -> mutantDetector.isMutant(dna));
    }

}