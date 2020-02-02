package demo.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import demo.validations.UserValidation;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = UserValidation.class)
public @interface UserRegisterValidation {

	String message() default "Oooops something went wrong";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
