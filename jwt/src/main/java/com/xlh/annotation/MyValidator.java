package com.xlh.annotation;

import javax.validation.*;
import java.util.List;
import java.util.Set;

/**
 * @author: xielinhao
 * @title: MyValidator
 * @projectName: hole
 * @description:
 * @date: 14:35 2021/12/16
 */
public class MyValidator implements ConstraintValidator<MyValid, Object> {

    private MyValid myValid;

    @Override
    public void initialize(MyValid constraintAnnotation) {
        this.myValid = constraintAnnotation;
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        if (value != null) {
            if (value instanceof List) {
                if (((List<?>) value).size() < myValid.min() || ((List<?>) value).size() > myValid.max()) {
                    return false;
                }
            } else {
                Set<ConstraintViolation<Object>> validate = ValidationUtils.getValidator().validate(value);
                for (ConstraintViolation constraintViolation : validate) {
                    context.disableDefaultConstraintViolation();
                    context.buildConstraintViolationWithTemplate(constraintViolation.getMessage()).addConstraintViolation();
                    return false;
                }
            }
        }
        return true;
    }


}

class ValidationUtils {
    public static Validator getValidator() {
        return validator;
    }

    static Validator validator;

    static {
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
    }
}
