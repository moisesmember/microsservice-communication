package br.com.konisberg.product_api.infra.util;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class DateConversion {
    public static LocalDateTime convertDateToLocalDateTime(Date date) {
        if (date == null) {
            return null;
        }
        return date.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();
    }
}
