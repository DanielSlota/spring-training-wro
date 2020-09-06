package pl.sda.springboottraining.constrains;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PeselValidatorImpl implements ConstraintValidator<Pesel, CharSequence> {

    @Override
    public boolean isValid(CharSequence value, ConstraintValidatorContext context) {

        System.out.println("To jest walidator!");

        return true;
    }
}
