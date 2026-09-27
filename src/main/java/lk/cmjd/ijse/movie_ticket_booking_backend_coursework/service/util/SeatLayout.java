package lk.cmjd.ijse.movie_ticket_booking_backend_coursework.service.util;

import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

@Component
public class SeatLayout {
    public List<String> seats(int capacity) {
        List<String> seats = new ArrayList<>();
        for (int index = 0; index < capacity; index++) {
            seats.add(rowName(index / 10) + (index % 10 + 1));
        }
        return seats;
    }

    private String rowName(int row) {
        String name = "";
        int value = row + 1;
        while (value > 0) {
            value--;
            name = (char) ('A' + value % 26) + name;
            value /= 26;
        }
        return name;
    }
}
