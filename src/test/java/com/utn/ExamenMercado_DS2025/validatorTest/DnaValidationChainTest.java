package com.utn.ExamenMercado_DS2025.validatorTest;

import com.utn.ExamenMercado_DS2025.validator.DnaValidator;
import com.utn.ExamenMercado_DS2025.validator.DnaValidationChain;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class DnaValidationChainTest {

    private final String[] validDna = {"ATGC", "CAGT", "TTAT", "AGAC"};
    private final String[] invalidDna = {"ATGC", "CAGX"};

    @Test
    void testValidationChain_AllValid() {
        DnaValidator val1 = mock(DnaValidator.class);
        DnaValidator val2 = mock(DnaValidator.class);

        DnaValidationChain chain = new DnaValidationChain(List.of(val1, val2));

        assertDoesNotThrow(() -> chain.validate(validDna),
                "La cadena debería validar con todos los validadores exitosos");

        verify(val1).check(validDna);
        verify(val2).check(validDna);
    }

    @Test
    void testValidationChain_StopsOnFirstInvalid() {
        DnaValidator val1 = mock(DnaValidator.class);
        DnaValidator val2 = mock(DnaValidator.class);

        doThrow(new IllegalArgumentException("Falla de validación")).when(val1).check(invalidDna);

        DnaValidationChain chain = new DnaValidationChain(List.of(val1, val2));

        assertThatThrownBy(() -> chain.validate(invalidDna))
                .isInstanceOf(IllegalArgumentException.class)
                .withFailMessage("La cadena debería fallar al encontrar el primer validador inválido");

        verify(val1).check(invalidDna);

        verify(val2, never()).check(any());
    }
    //arreglado
}
