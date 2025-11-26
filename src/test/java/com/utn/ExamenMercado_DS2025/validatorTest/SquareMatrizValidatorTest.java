package com.utn.ExamenMercado_DS2025.validatorTest;

import com.utn.ExamenMercado_DS2025.exception.InvalidDnaException;
import com.utn.ExamenMercado_DS2025.validator.SquareMatrizValidator;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow; // Para éxito (no excepción)
import static org.assertj.core.api.Assertions.assertThatThrownBy; // Para fallo (excepción)

public class SquareMatrizValidatorTest {

    private final SquareMatrizValidator validator = new SquareMatrizValidator();

    @Test
    public void testCheck_whenSquareMatrix_doesNotThrowException() {
        String[] squareDna = {"ATGC", "CAGT", "TTAT", "AGAC"};
        assertDoesNotThrow(() -> validator.check(squareDna));
    }

    @Test
    public void testCheck_whenNonSquareMatrix_throwsException() {
        String[] nonSquareDna = {"ATGC", "CAGT", "TTAT"};
        assertThatThrownBy(() -> validator.check(nonSquareDna))
                .isInstanceOf(InvalidDnaException.class)
                .hasMessage("Matriz debe ser NxN");
    }

    @Test
    public void testCheck_whenRowLengthIsIncorrect_throwsException() {
        String[] nonSquareDna = {"ATGC", "CAG", "TTAT", "AGAC"};
        assertThatThrownBy(() -> validator.check(nonSquareDna))
                .isInstanceOf(InvalidDnaException.class)
                .hasMessage("Matriz debe ser NxN");
    }

    @Test
    public void testCheck_whenEmptyArray_doesNotThrowException() {
        String[] empty = {};
        assertDoesNotThrow(() -> validator.check(empty));
    }
    //arreglado
}
