package com.utn.ExamenMercado_DS2025.strategy;

public class HorizontalDetector implements SequenceDetector{

    @Override
    public boolean detect(char[][] m, int row, int col) {
        if (col + 3 >= m[row].length) return false;

        char base = m[row][col];
        return m[row][col + 1] == base
                && m[row][col + 2] == base
                && m[row][col + 3] == base;
    }

    @Override
    public String name() {
        return "HorizontalDetector";
    }

}


//Aca uso strategy, sino se llenaba de ifs horribles que anda a saber si daban error como lo arreglabas
