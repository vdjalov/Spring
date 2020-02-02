package demo.validations;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import demo.annotations.UserRegisterValidation;

public class UserValidation implements ConstraintValidator<UserRegisterValidation, String>{

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		return value.length() > 3 && value.length() < 15;
	}

}
