package lk.cmjd.ijse.movie_ticket_booking_backend_coursework.service;

import lk.cmjd.ijse.movie_ticket_booking_backend_coursework.dto.*;

public interface AuthService {
    UserDTO signUp(AuthDTO dto);
    AuthResponseDTO signIn(SignInDTO dto);
}
