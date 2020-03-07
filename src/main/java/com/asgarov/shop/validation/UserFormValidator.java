package com.asgarov.shop.validation;



import com.asgarov.shop.entity.User;
import com.asgarov.shop.repository.UserRepository;
import com.asgarov.shop.util.UserForm;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class UserFormValidator implements Validator {
    UserRepository userRepository;

    public UserFormValidator(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override public boolean supports(final Class<?> aClass) {
        return User.class.equals(aClass);
    }

    @Override public void validate(final Object object, final Errors errors) {
        UserForm userForm = (UserForm) object;
        if(!userForm.getPassword().equals(userForm.getConfirmPassword())){
            errors.reject("passwordRepeat", "PasswordsDontMatch");
        }

        if(userRepository.findByEmail(userForm.getEmail()).isPresent()){
            errors.reject("email", "emailIsAlreadyRegistered");
        }
    }
}
