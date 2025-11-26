package com.utn.ExamenMercado_DS2025.validator;

import com.utn.ExamenMercado_DS2025.exception.InvalidDnaException;

public class SquareMatrizValidator implements DnaValidator {
    public void check(String[] dna) {
        int n = dna.length;
        for (String row : dna) {
            if (row.length() != n) {
                throw new InvalidDnaException("Matriz debe ser NxN");
            }
        }
    }
}
