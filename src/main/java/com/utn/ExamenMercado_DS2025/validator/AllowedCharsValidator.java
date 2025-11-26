package com.utn.ExamenMercado_DS2025.validator;

import com.utn.ExamenMercado_DS2025.exception.InvalidDnaException;

public class AllowedCharsValidator implements DnaValidator {
    @Override
    public void check(String[] dna) {

        for (String row : dna) {

            if (row == null) { // Una fila nula no puede tener caracteres válidos.
                throw new InvalidDnaException("Solo se permiten A,T,C,G (fila nula detectada)");
            }

            if (!row.matches("[ATCG]+")) {
                throw new InvalidDnaException("Solo se permiten A,T,C,G");
            }
        }
    }
}

//cHAIN OF RESPONSIBILITY nos permite la validacion en la propia clase,
// para agregar una validacion nueva, solo creo la clase y pongo @Component y listo
// Cada validator testeable aisladamente
