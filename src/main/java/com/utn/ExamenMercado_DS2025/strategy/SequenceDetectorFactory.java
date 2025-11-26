package com.utn.ExamenMercado_DS2025.strategy;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class SequenceDetectorFactory {

    private final List<SequenceDetector> detectors;  //Spring inyecta TODAS las que tengan @Component

    public List<SequenceDetector> allDetectors() {
        return detectors;
    }
}
