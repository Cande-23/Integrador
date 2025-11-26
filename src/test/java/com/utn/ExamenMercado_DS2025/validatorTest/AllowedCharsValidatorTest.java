package com.utn.ExamenMercado_DS2025.validatorTest;

import com.utn.ExamenMercado_DS2025.exception.InvalidDnaException;
import com.utn.ExamenMercado_DS2025.validator.AllowedCharsValidator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AllowedCharsValidatorTest {

    private final AllowedCharsValidator validator = new AllowedCharsValidator();

    @Test
    public void testCheck_whenValidChars_doesNotThrowException() {
        String[] validDna = {"ATGC", "CAGT", "TTAT", "AGAC"};
        assertDoesNotThrow(() -> validator.check(validDna));
    }

    @Test
    public void testCheck_whenInvalidChars_throwsException() {
        String[] invalidDna = {"ATGC", "CAGX", "TTAT", "AGAC"};

        InvalidDnaException ex = assertThrows(
                InvalidDnaException.class,
                () -> validator.check(invalidDna)
        );

        assertEquals("Solo se permiten A,T,C,G", ex.getMessage());
    }

    @Test
    public void testCheck_whenNullRow_throwsException() {
        String[] dnaWithNull = {"ATGC", null, "TTAT", "AGAC"};

        InvalidDnaException ex = assertThrows(
                InvalidDnaException.class,
                () -> validator.check(dnaWithNull)
        );

        assertEquals("Solo se permiten A,T,C,G (fila nula detectada)", ex.getMessage());
    }

    @Test
    public void testCheck_whenEmptyArray_doesNotThrowException() {
        String[] empty = {};
        assertDoesNotThrow(() -> validator.check(empty));
    }
    //arreglado
}
