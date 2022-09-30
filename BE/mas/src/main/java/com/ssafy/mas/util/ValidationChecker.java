package com.ssafy.mas.util;

import org.springframework.stereotype.Component;

@Component
public class ValidationChecker {
    public boolean idValidationCheck(String inputId) {
        return inputId.length() <= 16 && validationCheck(inputId, 8);
    }

    public boolean pwdValidationCheck(String inputPwd) {
        return validationCheck(inputPwd, 8);
    }

    public boolean validationCheck(String str, int length) {
        if(str.length() < length) {
            return false;
        } else {
            boolean containNumeric = str.matches(".*[0-9].*");
            boolean containAlphabet = str.matches(".*[a-zA-Z].*");
            return containNumeric && containAlphabet;
        }
    }
}
