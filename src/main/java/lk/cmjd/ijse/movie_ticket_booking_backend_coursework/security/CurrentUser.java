package lk.cmjd.ijse.movie_ticket_booking_backend_coursework.security;

import lk.cmjd.ijse.movie_ticket_booking_backend_coursework.dao.UserDao;
import lk.cmjd.ijse.movie_ticket_booking_backend_coursework.entities.UserEntity;
import lk.cmjd.ijse.movie_ticket_booking_backend_coursework.dto.enums.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CurrentUser {
    private final UserDao userDao;

    public UserEntity get() {
        String id = SecurityContextHolder.getContext().getAuthentication().getName();
        return userDao.findById(id).orElseThrow(() -> new AccessDeniedException("User account no longer exists"));
    }

    public void checkOwner(String ownerId) {
        UserEntity user = get();
        if (user.getRole() != Role.ADMIN && !user.getId().equals(ownerId)) {
            throw new AccessDeniedException("This record belongs to another customer");
        }
    }
}
