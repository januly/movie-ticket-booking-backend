package lk.cmjd.ijse.movie_ticket_booking_backend_coursework.service;

import lk.cmjd.ijse.movie_ticket_booking_backend_coursework.dto.AuthDTO;

public interface AuthService {
    void signUp(AuthDTO authDTO);

    String signIn(AuthDTO authDTO);

}
