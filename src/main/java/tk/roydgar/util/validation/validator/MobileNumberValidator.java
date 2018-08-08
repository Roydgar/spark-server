package tk.roydgar.util.validation.validator;

import tk.roydgar.util.validation.annotation.MobileNumber;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class MobileNumberValidator implements
        ConstraintValidator<MobileNumber, String> {

    @Override
    public void initialize(MobileNumber mobileNumber) {
    }


    @Override
    public boolean isValid(String mobileNumber,
                           ConstraintValidatorContext cxt) {
        return mobileNumber.matches("^((\\+380)\\d{9})|(\\d{10})");
    }

}
