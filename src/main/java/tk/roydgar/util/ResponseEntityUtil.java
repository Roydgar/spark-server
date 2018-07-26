package tk.roydgar.util;

import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.HttpStatus.OK;

public class ResponseEntityUtil {

    private ResponseEntityUtil() {}

    public static ResponseEntity<?> responseEntityFromOptional(Optional<?> value) {
        return value.isPresent() ? new ResponseEntity<>(value.get(), OK)
                : new ResponseEntity<>(NO_CONTENT);
    }

    public static ResponseEntity<?> responseEntityFromList(List<?> list) {
        return new ResponseEntity<>(list, list.isEmpty() ? NO_CONTENT : OK);
    }

}