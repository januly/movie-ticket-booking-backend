package lk.cmjd.ijse.movie_ticket_booking_backend_coursework.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lk.cmjd.ijse.movie_ticket_booking_backend_coursework.dto.enums.theatreStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name="Theatre")
public class TheatreEntity implements Serializable {
    @Id
    private String theatreId;
    private String theatreName;
    private String location;
    private String Capacity;
    @Enumerated(EnumType.STRING)
    private theatreStatus status;

    @OneToMany(mappedBy = "theatre")
    @JsonIgnore
    private List<ShowEntity> shows;
}
