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
public class TheatreServiceIMPL implements TheatreService {
    private final TheatreDao theatreDao;
    private final ShowDao showDao;
    private final Conversion conversion;

    @Override
    public TheatreDTO createTheatre(TheatreDTO dto) {
        TheatreEntity theatre = conversion.toTheatreEntity(dto);
        theatre.setTheatreId(IDGenerate.theatreId());
        log.info("Created theatre {}", theatre.getTheatreId());
        return conversion.toTheatreDTO(theatreDao.save(theatre));
    }

    @Override
    @Transactional(readOnly = true)
    public TheatreDTO getSelectedTheatre(String id) { return conversion.toTheatreDTO(findTheatre(id)); }

    @Override
    @Transactional(readOnly = true)
    public List<TheatreDTO> getTheatres() { return theatreDao.findAll().stream().map(conversion::toTheatreDTO).toList(); }

    @Override
    public TheatreDTO updateTheatre(String id, TheatreDTO dto) {
        TheatreEntity theatre = theatreDao.findForUpdate(id).orElseThrow(() -> new ResourceNotFoundException("Theatre not found"));
        if (theatre.getCapacity() != dto.getCapacity() && showDao.existsByTheatreTheatreId(id)) {
            throw new BusinessException("A theatre with shows cannot change its capacity");
        }
        theatre.setTheatreName(dto.getTheatreName());
        theatre.setLocation(dto.getLocation());
        theatre.setCapacity(dto.getCapacity());
        theatre.setStatus(dto.getStatus());
        log.info("Updated theatre {}", id);
        return conversion.toTheatreDTO(theatreDao.save(theatre));
    }

    @Override
    public void deleteTheatre(String id) {
        TheatreEntity theatre = findTheatre(id);
        if (showDao.existsByTheatreTheatreId(id)) { throw new BusinessException("A theatre with shows cannot be deleted"); }
        theatreDao.delete(theatre);
        log.info("Deleted theatre {}", id);
    }

    private TheatreEntity findTheatre(String id) {
        return theatreDao.findById(id).orElseThrow(() -> new ResourceNotFoundException("Theatre not found"));
    }
}
