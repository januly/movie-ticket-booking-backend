package lk.cmjd.ijse.movie_ticket_booking_backend_coursework.service;

import lk.cmjd.ijse.movie_ticket_booking_backend_coursework.dto.*;
import lk.cmjd.ijse.movie_ticket_booking_backend_coursework.dto.enums.*;
import java.time.LocalDate;
import java.util.List;

public interface UserService {
    UserDTO getSelectedUser(String id);
    List<UserDTO> getUsers();
    UserDTO updateUser(String id, UserDTO dto);
    void deleteUser(String id);
}
