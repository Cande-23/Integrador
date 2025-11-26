package com.utn.ExamenMercado_DS2025.controller;

import com.utn.ExamenMercado_DS2025.dto.DnaRequest;
import com.utn.ExamenMercado_DS2025.service.MutantService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mutant")
public class MutantController {

    private final MutantService mutantService;

    public MutantController(MutantService mutantService) {
        this.mutantService = mutantService;
    }

    @PostMapping
    public ResponseEntity<Void> checkMutant(@RequestBody DnaRequest dnaRequest) {
        boolean isMutant = mutantService.isMutant(dnaRequest.getDna());
        if (isMutant) {
            return ResponseEntity.ok().build();  // 200 OK
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();  // 403 Forbidden
        }
    }
}