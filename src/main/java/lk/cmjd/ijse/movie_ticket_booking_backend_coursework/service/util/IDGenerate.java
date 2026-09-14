package lk.cmjd.ijse.movie_ticket_booking_backend_coursework.service.util;

import java.util.UUID;

public class IDGenerate {
    public static String id(){
        return "CA-"+UUID.randomUUID();
    }
    public static String theatreId(){
        return "AC-"+UUID.randomUUID();
    }
    public static String showId(){
        return "GL-"+UUID.randomUUID();
    }
    public static String paymentId(){
        return "UA-"+UUID.randomUUID();
    }
    public static String movieId(){
        return "US-"+UUID.randomUUID();
    }
    public static String bookingId(){
        return "US-"+ UUID.randomUUID();
    }
}
