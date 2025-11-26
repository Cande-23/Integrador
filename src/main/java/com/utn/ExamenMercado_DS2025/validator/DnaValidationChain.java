package com.utn.ExamenMercado_DS2025.validator;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
@RequiredArgsConstructor  // Esto genera un constructor, si falta, nos tira error en las proximas clases
public class DnaValidationChain {
    private final List<DnaValidator> validators; // Spring inyecta todas

    public void validate(String[]dna) {
        for (DnaValidator v : validators) {
            v.check(dna); //Lanza una excepcion si falla
        }
    }
}
