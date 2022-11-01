package uz.org.vote.annotation;

import uz.org.vote.annotation.validatorClass.PhoneNumberValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = PhoneNumberValidator.class)
public @interface ValidatePhoneNumber {
    public String message() default "Invalid phoneNumber";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
