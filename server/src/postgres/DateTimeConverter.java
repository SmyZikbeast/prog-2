package postgres;

import java.time.LocalDateTime;

public class DateTimeConverter {
    public static LocalDateTime toDate(String d){
        return LocalDateTime.parse(d);
    }
}
