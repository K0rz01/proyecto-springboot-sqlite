package com.universidad.proyecto.util;

import com.universidad.proyecto.exception.BadRequestException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import static com.universidad.proyecto.util.AppConstants.BAD_DATE_FORMAT;

public final class ValidationUtil {

    private static final DateTimeFormatter ISO_FORMATTER = DateTimeFormatter.ISO_LOCAL_DATE;

    private ValidationUtil() {
    }

    public static LocalDate parseIsoDate(String date) {
        if (date == null || date.isBlank()) {
            return null;
        }
        try {
            return LocalDate.parse(date, ISO_FORMATTER);
        } catch (DateTimeParseException ex) {
            throw new BadRequestException(BAD_DATE_FORMAT);
        }
    }
}

