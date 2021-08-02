package com.jbond.projects.spring_cource.validation;

import com.jbond.projects.spring_cource.Cat;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.w3c.dom.ls.LSResourceResolver;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;

import javax.xml.transform.Result;
import javax.xml.transform.Source;
import org.springframework.validation.Validator;
import java.io.IOException;

public class CatValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
       return Cat.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        ValidationUtils.rejectIfEmpty(errors, "age", "age.empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "name.empty.or.whitespace");
        Cat target = (Cat) o;
        if (target.getAge() < 0) {
            errors.rejectValue("age", "age less then 0");
        }
    }
}
