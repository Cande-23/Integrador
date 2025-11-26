package com.utn.ExamenMercado_DS2025.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class DnaSequenceValidator implements ConstraintValidator<ValidDnaSequence, String[]> {

    @Override
    public void initialize(ValidDnaSequence constraintAnnotation) {
        //No necesitamos nada mas aca, pero Java obliga a ponerlo
    }

    @Override
    public boolean isValid(String[]dna, ConstraintValidatorContext context) {
        if (dna == null ) return false;

        int n = dna.length;
        if (n < 4 ) return false; //Minimo 4x4

        for (String row : dna) {
            if (row == null || row.length() != n)
                return false;
            if (!row.matches("[ATCG]+")) return false;
        }
        return true;
    }
}
