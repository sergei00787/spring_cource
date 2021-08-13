package com.jbond.projects.spring_cource.hibernate_learn.entity.ConverterExample;

import javax.persistence.AttributeConverter;
import java.sql.Date;

public class DateStringConverter implements AttributeConverter<Date, String> {


    @Override
    public String convertToDatabaseColumn(Date date) {
        return date.toString();
    }

    @Override
    public Date convertToEntityAttribute(String s) {
        Date date = Date.valueOf(s);
        return date;
    }
}
