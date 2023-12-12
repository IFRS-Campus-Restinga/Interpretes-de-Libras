package br.com.interpreto.infra.validation;

import br.com.interpreto.infra.validation.constraints.DataNasc;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;

public class DataNascValidation implements ConstraintValidator<DataNasc, String> {
    public static final int ANO_MIN = LocalDate.now().getYear() - 100;
    public static final int ANO_MAX = LocalDate.now().getYear() - 18;


    @Override
    public void initialize(DataNasc constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null || value.isEmpty()){
            return true;
        }
        boolean resultado = false;
        String[] partesData = value.split("-");

        String year = partesData[0];String month = partesData[1];String day = partesData[2];
        int dia = Integer.parseInt(day);
        int mes = Integer.parseInt(month);
        int ano = Integer.parseInt(year);
        System.out.println(dia);
        System.out.println(mes);
        System.out.println(ano);

        if (ano > ANO_MIN && ano < ANO_MAX){
            resultado = true;
        }

        if (isAnoBissexto(ano)){
            if (mes == 2 && dia > 29){
                resultado = false;
            }
        } else if (mes == 2 && dia > 28) {
            resultado = false;
        }
        return resultado;
    }

    public boolean isAnoBissexto(int ano){
        int divisivelPor4 = ano % 4;
        int divisivelPor100 = ano % 100;
        int divisivelPor400 = ano % 400;

        if (divisivelPor4 == 0 && divisivelPor100 != 0){
            return true;
        } else {
            return divisivelPor400 == 0;
        }

    }

}
