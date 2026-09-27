package lk.cmjd.ijse.movie_ticket_booking_backend_coursework.service.util;

import java.util.UUID;

public class IDGenerate {
    public static String id() { return "U-" + UUID.randomUUID(); }
    public static String theatreId() { return "T-" + UUID.randomUUID(); }
    public static String showId() { return "S-" + UUID.randomUUID(); }
    public static String paymentId() { return "P-" + UUID.randomUUID(); }
    public static String movieId() { return "M-" + UUID.randomUUID(); }
    public static String bookingId() { return "B-" + UUID.randomUUID(); }
}
