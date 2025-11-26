package com.utn.ExamenMercado_DS2025.entity;
//Carpeta Entity: la tabla de BD
import jakarta.persistence.*; // JPA para base de datos
import lombok.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "dna_records", // Nombre de la tabla en H2
        indexes = {@Index(name = "idx_dna_hash", columnList = "dnaHash"), //Mejoras agregadas por mati
                @Index(name = "idx_is_mutant", columnList = "isMutant")})

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DnaRecord implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false, length = 64) // CRÍTICO: unique=true evita duplicados
    private String dnaHash; // Hash SHA-256 del ADN

    private boolean isMutant; // true = mutante, false = humano


    //metodos de mejora agregados por mati
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }

    // Constructor corto que faltaba
    public DnaRecord(String dnaHash, boolean isMutant) {
        this.dnaHash = dnaHash;
        this.isMutant = isMutant;
        this.createdAt = LocalDateTime.now(); // @PrePersist no se ejecuta con builder/manual
    }
}