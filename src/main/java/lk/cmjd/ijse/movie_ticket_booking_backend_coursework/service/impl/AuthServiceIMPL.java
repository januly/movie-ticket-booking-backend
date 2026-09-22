package lk.cmjd.ijse.movie_ticket_booking_backend_coursework.service.impl;

import lk.cmjd.ijse.movie_ticket_booking_backend_coursework.dao.UserDao;
import lk.cmjd.ijse.movie_ticket_booking_backend_coursework.dto.*;
import lk.cmjd.ijse.movie_ticket_booking_backend_coursework.dto.enums.Role;
import lk.cmjd.ijse.movie_ticket_booking_backend_coursework.entities.UserEntity;
import lk.cmjd.ijse.movie_ticket_booking_backend_coursework.exception.BusinessException;
import lk.cmjd.ijse.movie_ticket_booking_backend_coursework.security.JwtService;
import lk.cmjd.ijse.movie_ticket_booking_backend_coursework.service.AuthService;
import lk.cmjd.ijse.movie_ticket_booking_backend_coursework.service.util.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import java.util.Locale;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class AuthServiceIMPL implements AuthService {
    private final UserDao userDao;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final Conversion conversion;

    @Override
    public UserDTO signUp(AuthDTO dto) {
        String email = dto.getEmail().trim().toLowerCase(Locale.ROOT);
        if (userDao.existsByEmail(email)) {
            throw new BusinessException("An account already exists for this email");
        }
        UserEntity user = UserEntity.builder().id(IDGenerate.id()).name(dto.getName().trim()).email(email)
                .password(passwordEncoder.encode(dto.getPassword())).role(Role.CUSTOMER).build();
        userDao.save(user);
        log.info("Registered customer {}", user.getId());
        return conversion.toUserDTO(user);
    }

    @Override
    @Transactional(readOnly = true)
    public AuthResponseDTO signIn(SignInDTO dto) {
        UserEntity user = userDao.findByEmail(dto.getEmail().trim().toLowerCase(Locale.ROOT)).orElse(null);
        if (user == null || !passwordEncoder.matches(dto.getPassword(), user.getPassword())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid email or password");
        }
        log.info("User {} signed in", user.getId());
        return new AuthResponseDTO(jwtService.createToken(user), "Bearer", jwtService.getExpirationSeconds(),
                conversion.toUserDTO(user));
    }
}
