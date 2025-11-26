package com.utn.ExamenMercado_DS2025.strategy;

public interface SequenceDetector {
    boolean detect (char[][] matrix, int row, int col);
    String name();
}
