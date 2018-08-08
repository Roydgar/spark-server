package tk.roydgar.util.validation.validator;

import tk.roydgar.util.validation.annotation.Password;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordValidator implements
        ConstraintValidator<Password, String> {

    @Override
    public void initialize(Password password) {
    }


    @Override
    public boolean isValid(String password,
                           ConstraintValidatorContext cxt) {
        return password.matches("^(?=.*\\d)(?=.*[a-zA-Z]).{4,8}$");
    }

}
