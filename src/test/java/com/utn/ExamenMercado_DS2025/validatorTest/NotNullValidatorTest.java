package com.utn.ExamenMercado_DS2025.validatorTest;

import com.utn.ExamenMercado_DS2025.exception.InvalidDnaException;
import com.utn.ExamenMercado_DS2025.validator.NotNullValidator;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.assertj.core.api.Assertions.assertThatThrownBy; // Necesitas AssertJ para esta aserción
import static org.junit.jupiter.api.Assertions.assertThrows;

public class NotNullValidatorTest {

    private final NotNullValidator validator = new NotNullValidator();

    @Test
    public void testCheck_whenNotNull_doesNotThrowException() {
        String[] nonNullDna = {"ATGC", "CAGT"};
        assertDoesNotThrow(() -> validator.check(nonNullDna));
    }

    @Test
    public void testCheck_whenNull_throwsException() {
        assertThrows(InvalidDnaException.class,
                () -> validator.check(null),
                "ADN es null");
    }

    @Test
    public void testCheck_whenEmpty_throwsException() {
        assertThrows(InvalidDnaException.class,
                () -> validator.check(new String[]{}),
                "ADN vacío");
    }
    //arreglado
}
