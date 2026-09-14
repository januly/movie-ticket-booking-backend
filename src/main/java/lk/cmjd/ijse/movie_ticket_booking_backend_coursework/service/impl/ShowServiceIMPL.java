package lk.cmjd.ijse.movie_ticket_booking_backend_coursework.service.impl;

import lk.cmjd.ijse.movie_ticket_booking_backend_coursework.dto.ShowDTO;
import lk.cmjd.ijse.movie_ticket_booking_backend_coursework.dto.enums.showStatus;
import lk.cmjd.ijse.movie_ticket_booking_backend_coursework.service.ShowService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShowServiceIMPL implements ShowService {


    @Override
    public void createShow(ShowDTO show) {
        System.out.println("create ShowIMPL: " + show);

    }

    @Override
    public ShowDTO getselectedShow(String showId) {
        return new ShowDTO(
                "S001",
                "Eda Ra",
                "Liberty By Scope Cinemas - Screen 1",
                "2026-10-09",
                "19:15",
                "1500.00",
                showStatus.SCHEDULED);
    }

    @Override
    public List<ShowDTO> getShows() {
        List<ShowDTO> showsList = List.of(
                new ShowDTO(
                        "S001",
                        "Eda Ra",
                        "Liberty By Scope Cinemas - Screen 1",
                        "2026-10-09",
                        "19:15",
                        "1500.00",
                        showStatus.SCHEDULED
                ),
                new ShowDTO(
                        "S002",
                        "Eda Ra",
                        "Savoy Premier - Rajagiriya",
                        "2026-10-09",
                        "19:00",
                        "1200.00",
                        showStatus.SCHEDULED
                ),
                new ShowDTO(
                        "S003",
                        "Baththa",
                        "Liberty By Scope Cinemas - Screen 2",
                        "2026-10-09",
                        "21:30",
                        "1500.00",
                        showStatus.SCHEDULED
                ),
                new ShowDTO(
                        "S004",
                        "Adaraneeya Tharuwak",
                        "Scope Cinemas - Havelock City Mall",
                        "2026-10-09",
                        "19:30",
                        "1800.00",
                        showStatus.SCHEDULED
                ));
        return showsList;
    }

    @Override
    public void updateShow(String showId, ShowDTO show) {
        System.out.println("Updating Show: " + showId+ " and the Show is: "+show.toString());

    }

    @Override
    public void deleteShow(String showId) {
        System.out.println("Deleted Show Id is: " + showId);
    }
}
