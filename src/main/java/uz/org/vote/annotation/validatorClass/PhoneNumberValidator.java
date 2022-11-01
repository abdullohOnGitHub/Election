package uz.org.vote.annotation.validatorClass;

import uz.org.vote.annotation.ValidatePhoneNumber;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class PhoneNumberValidator implements ConstraintValidator<ValidatePhoneNumber, String> {
    @Override
    public boolean isValid(String phoneNumber, ConstraintValidatorContext constraintValidatorContext) {
        List<String> operatorCodes = List.of("99", "98", "97", "95", "94", "93", "91", "90", "88", "78", "71", "55", "33");
        return phoneNumber.length() == 9 && operatorCodes.contains(phoneNumber.substring(0, 2));
    }
}
