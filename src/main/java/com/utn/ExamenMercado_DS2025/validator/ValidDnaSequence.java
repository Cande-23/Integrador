package com.utn.ExamenMercado_DS2025.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = DnaSequenceValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidDnaSequence {
    String message() default "El ADN debe ser una matriz NxN (minimo 4x4)" +
            " y solo contener A,T,C,G ";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
