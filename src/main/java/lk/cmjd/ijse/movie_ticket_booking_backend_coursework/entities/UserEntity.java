package lk.cmjd.ijse.movie_ticket_booking_backend_coursework.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lk.cmjd.ijse.movie_ticket_booking_backend_coursework.dto.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Builder
@Table(name="Users")
public class UserEntity implements Serializable {
    @Id
    private String id;
    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<BookingEntity> bookings;
}
