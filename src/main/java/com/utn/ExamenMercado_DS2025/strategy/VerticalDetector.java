package com.utn.ExamenMercado_DS2025.strategy;


import org.springframework.stereotype.Component;

@Component
public class VerticalDetector implements SequenceDetector{
    @Override
    public boolean detect(char[][] m, int row, int col) {
        if (row + 3 >= m.length) return false;
        char base = m[row][col];
        return m[row + 1][col] == base &&
                m[row + 2][col] == base &&
                m[row + 3][col] == base;
    }

    @Override
    public String name() {
        return "Vertical";
    }
}
