package lk.cmjd.ijse.movie_ticket_booking_backend_coursework.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import java.time.Instant;
import java.util.Map;

@Getter
@AllArgsConstructor
public class ApiError {
    private Instant timestamp;
    private int status;
    private String message;
    private String path;
    private Map<String, String> errors;
}
