package com.asgarov.shop.validation;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;

import org.apache.commons.beanutils.BeanUtils;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({TYPE, ANNOTATION_TYPE})
@Retention(RUNTIME)
@Constraint(validatedBy = FieldMatch.FieldMatchValidator.class)
@Documented
public @interface FieldMatch
{
    String message() default "The fields must match";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
    String first();
    String second();

    @Target({TYPE, ANNOTATION_TYPE})
    @Retention(RUNTIME)
    @Documented
    @interface List
    { 
        FieldMatch[] value();
    }

    class FieldMatchValidator implements ConstraintValidator<FieldMatch, Object> {

        private String firstFieldName;
        private String secondFieldName;
        private String message;

        @Override
        public void initialize(final FieldMatch constraintAnnotation) {
            firstFieldName = constraintAnnotation.first();
            secondFieldName = constraintAnnotation.second();
            message = constraintAnnotation.message();
        }

        @Override
        public boolean isValid(final Object value, final ConstraintValidatorContext context) {
            boolean valid = true;
            try {
                final Object firstObj = BeanUtils.getProperty(value, firstFieldName);
                final Object secondObj = BeanUtils.getProperty(value, secondFieldName);

                valid = firstObj == null && secondObj == null || firstObj != null && firstObj.equals(secondObj);
            } catch (final Exception ignore) {
                // ignore
            }

            if (!valid) {
                context.buildConstraintViolationWithTemplate(message)
                        .addPropertyNode(firstFieldName)
                        .addConstraintViolation()
                        .disableDefaultConstraintViolation();
            }

            return valid;
        }
    }
}