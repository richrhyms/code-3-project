package com.bw.dentaldoor.converter;

import org.apache.commons.lang3.time.DateUtils;
import org.springframework.core.convert.converter.Converter;

import java.text.ParseException;
import java.util.Date;

/**
 * @author Olaleye Afolabi <oafolabi@byteworks.com.ng>
 */
public final class DateConverter implements Converter<String, Date> {

    private final String pattern;

    public DateConverter(String dateFormat) {
        this.pattern = dateFormat;
    }

    @Override
    public Date convert(String source) {
        if (source == null || source.isEmpty()) {
            return null;
        }

        try {
            return DateUtils.parseDate(source, pattern);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
