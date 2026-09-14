package lk.cmjd.ijse.movie_ticket_booking_backend_coursework.service.impl;

import lk.cmjd.ijse.movie_ticket_booking_backend_coursework.dto.TheatreDTO;
import lk.cmjd.ijse.movie_ticket_booking_backend_coursework.dto.enums.theatreStatus;
import lk.cmjd.ijse.movie_ticket_booking_backend_coursework.service.TheatreService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TheatreServiceIMPL implements TheatreService {
    @Override
    public void createTheatre(TheatreDTO theatre) {
        System.out.println("create TheatreServiceIMLPL: " + theatre);
    }

    @Override
    public TheatreDTO getselectedTheatre(String theatreId) {
        return new TheatreDTO(
                theatreId,
                "Liberty by Scope Cinemas",
                "Colombo 03, Sri Lanka",
                "387",
                theatreStatus.ACTIVE);
    }

    @Override
    public List<TheatreDTO> getTheatres() {

        List<TheatreDTO> theatersList = List.of(
                new TheatreDTO(
                        "T001",
                        "Liberty by Scope Cinemas",
                        "Colombo 03, Sri Lanka",
                        "387",
                        theatreStatus.ACTIVE
                ),
                new TheatreDTO(
                        "T002",
                        "Savoy 3D Dolby Atmos",
                        "Wellawatte, Colombo 06, Sri Lanka",
                        "663",
                        theatreStatus.ACTIVE
                ),
                new TheatreDTO(
                        "T003",
                        "KCC Multiplex",
                        "Kandy City Centre, Kandy, Sri Lanka",
                        "700",
                        theatreStatus.ACTIVE
                ),
                new TheatreDTO(
                        "T004",
                        "Cinemax 3D",
                        "Reality Plaza, Ja-Ela, Sri Lanka",
                        "500",
                        theatreStatus.ACTIVE
                ));
        return theatersList;
    }

    @Override
    public void updateTheatre(String theatreId, TheatreDTO theatre) {
        System.out.println("Updating Theatre: " + theatreId +" and the theatre is: "+ theatre.toString());
    }

    @Override
    public void deleteTheatre(String theatreId) {
        System.out.println("Deleted Theatre Id is: " + theatreId);
    }
}
