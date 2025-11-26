package com.utn.ExamenMercado_DS2025.validator;

import com.utn.ExamenMercado_DS2025.exception.InvalidDnaException;
import org.springframework.stereotype.Component;

@Component
public class NotNullValidator implements DnaValidator {

    @Override
    public void check(String[] dna) {
        if (dna == null)
            throw new InvalidDnaException("ADN es null");

        if (dna.length == 0)
            throw new InvalidDnaException("ADN vacío");
    }
    //arreglado
}

