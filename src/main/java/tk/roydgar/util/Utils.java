package tk.roydgar.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class Utils {

    private Utils() {}

    public static Long parseId(String id) {
        Long result;
        try {
            result = Long.parseLong(id);
        } catch (NumberFormatException e) {
            return null;
        }

        return result;
    }

    public static LocalDateTime getLocalDateTimeInUTC() {
        return ZonedDateTime.now(ZoneId.of("UTC")).toLocalDateTime();
    }

    public static LocalDate getLocalDateInUTC() {
        return ZonedDateTime.now(ZoneId.of("UTC")).toLocalDate();
    }
}
