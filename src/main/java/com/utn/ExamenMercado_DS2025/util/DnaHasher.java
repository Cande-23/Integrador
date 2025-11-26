package com.utn.ExamenMercado_DS2025.util;

import java.security.MessageDigest;

public final class DnaHasher {

    private DnaHasher() { } // nadie puede instanciarla
    public static String sha256(String[] dna) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            String concatenado = String.join("", dna);
            byte[] hash = digest.digest(concatenado.getBytes());
            return bytesToHex(hash);
        } catch (Exception e) {
            throw new RuntimeException("Error calculando hash", e);
        }
    }

    private static String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }
}