package com.utn.ExamenMercado_DS2025.validatorTest;

import com.utn.ExamenMercado_DS2025.validator.DnaSequenceValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


public class DnaSequenceValidatorTest {

    private final DnaSequenceValidator validator = new DnaSequenceValidator();
    private final ConstraintValidatorContext context = null; //No lo usamos aca

    @Test
    public void validDnaReturnsTrue() {
        String[] dna = {"ATGC", "CAGT", "TTAT", "AGAC"};
        assertThat(validator.isValid(dna,context)).isTrue();
    }

    @Test
    public void nullDnaReturnsFalse() {
        assertThat(validator.isValid(null, context)).isFalse();
    }

    @Test
    public void nonSquareDnaReturnsFalse() {
        String[] dna = {"ATG", "CAGT", "TTAT", "AGAC"};
        assertThat(validator.isValid(dna, context)).isFalse();
    }

    @Test
    public void invalidCharsReturnsFalse() {
        String[] dna = {"ATGX", "CAGT", "TTAT", "AGAC"};
        assertThat(validator.isValid(dna, context)).isFalse();
    }
}
