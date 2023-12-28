package br.com.interpreto.infra.validation.constraints;

import br.com.interpreto.infra.validation.DataNascValidation;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = DataNascValidation.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface DataNasc {

    String message() default "Data de nascimento inválida";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}
