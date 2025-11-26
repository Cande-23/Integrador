package com.utn.ExamenMercado_DS2025.strategy;

import org.springframework.stereotype.Component;

@Component
public class DiagonalAscendantDetector implements SequenceDetector {
    @Override
    public boolean detect(char[][] m, int row, int col) {
        // Validamos que haya espacio hacia ARRIBA (porque sube) y hacia la DERECHA
        // row >= 3 porque vamos a restar índices (fila 3, 2, 1, 0)
        if (row < 3 || col + 3 >= m[0].length) {
            return false;
        }

        char base = m[row][col];
        return m[row-1][col+1] == base &&
                m[row-2][col+2] == base &&
                m[row-3][col+3] == base;
    }

    @Override
    public String name() {
        return "Diagonal Ascendente";
    }
}