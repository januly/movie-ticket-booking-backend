package lk.cmjd.ijse.movie_ticket_booking_backend_coursework.service.impl;

import lk.cmjd.ijse.movie_ticket_booking_backend_coursework.dao.*;
import lk.cmjd.ijse.movie_ticket_booking_backend_coursework.dto.*;
import lk.cmjd.ijse.movie_ticket_booking_backend_coursework.dto.enums.*;
import lk.cmjd.ijse.movie_ticket_booking_backend_coursework.entities.*;
import lk.cmjd.ijse.movie_ticket_booking_backend_coursework.exception.*;
import lk.cmjd.ijse.movie_ticket_booking_backend_coursework.security.CurrentUser;
import lk.cmjd.ijse.movie_ticket_booking_backend_coursework.service.*;
import lk.cmjd.ijse.movie_ticket_booking_backend_coursework.service.util.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.*;
import java.util.*;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class UserServiceIMPL implements UserService {
    private final UserDao userDao;
    private final BookingDao bookingDao;
    private final Conversion conversion;

    @Override
    @Transactional(readOnly = true)
    public UserDTO getSelectedUser(String id) { return conversion.toUserDTO(findUser(id)); }

    @Override
    @Transactional(readOnly = true)
    public List<UserDTO> getUsers() { return userDao.findAll().stream().map(conversion::toUserDTO).toList(); }

    @Override
    public UserDTO updateUser(String id, UserDTO dto) {
        UserEntity user = findUser(id);
        String email = dto.getEmail().trim().toLowerCase(Locale.ROOT);
        userDao.findByEmail(email).ifPresent(existing -> {
            if (!existing.getId().equals(id)) { throw new BusinessException("An account already exists for this email"); }
        });
        user.setName(dto.getName().trim());
        user.setEmail(email);
        user.setRole(dto.getRole());
        log.info("Updated user {}", id);
        return conversion.toUserDTO(userDao.save(user));
    }

    @Override
    public void deleteUser(String id) {
        UserEntity user = findUser(id);
        if (bookingDao.existsByUserId(id)) { throw new BusinessException("A user with bookings cannot be deleted"); }
        userDao.delete(user);
        log.info("Deleted user {}", id);
    }

    private UserEntity findUser(String id) {
        return userDao.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }
}
