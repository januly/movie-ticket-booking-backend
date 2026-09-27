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
public class ShowServiceIMPL implements ShowService {
    private final ShowDao showDao;
    private final MovieDao movieDao;
    private final TheatreDao theatreDao;
    private final BookingDao bookingDao;
    private final Conversion conversion;
    private final BookingRules bookingRules;
    private final SeatLayout seatLayout;

    @Override
    public ShowDTO createShow(ShowDTO dto) {
        if (dto.getStatus() != showStatus.SCHEDULED) { throw new BusinessException("New shows must be scheduled"); }
        TheatreEntity theatre = lockTheatre(dto.getTheatreId());
        MovieEntity movie = findMovie(dto.getMovieId());
        validateShow(dto, movie, theatre, null);
        ShowEntity show = ShowEntity.builder().showId(IDGenerate.showId()).movie(movie).theatre(theatre)
                .showDate(dto.getShowDate()).showTime(dto.getShowTime()).ticketPrice(dto.getTicketPrice()).status(dto.getStatus()).build();
        log.info("Created show {}", show.getShowId());
        return conversion.toShowDTO(showDao.save(show));
    }

    @Override
    @Transactional(readOnly = true)
    public ShowDTO getSelectedShow(String id) { return conversion.toShowDTO(findShow(id)); }

    @Override
    @Transactional(readOnly = true)
    public List<ShowDTO> getShows(String movieId, String theatreId, LocalDate date, showStatus status) {
        return showDao.findAll().stream()
                .filter(show -> movieId == null || show.getMovie().getMovieId().equals(movieId))
                .filter(show -> theatreId == null || show.getTheatre().getTheatreId().equals(theatreId))
                .filter(show -> date == null || show.getShowDate().equals(date))
                .filter(show -> status == null || show.getStatus() == status)
                .map(conversion::toShowDTO).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<ShowDTO> getShowsForMovie(String movieId) {
        findMovie(movieId);
        return showDao.findByMovieMovieId(movieId).stream().map(conversion::toShowDTO).toList();
    }

    @Override
    public ShowDTO updateShow(String id, ShowDTO dto) {
        TheatreEntity theatre = lockTheatre(dto.getTheatreId());
        ShowEntity show = bookingRules.lockShow(id);
        MovieEntity movie = findMovie(dto.getMovieId());
        boolean changed = !show.getMovie().getMovieId().equals(dto.getMovieId())
                || !show.getTheatre().getTheatreId().equals(dto.getTheatreId())
                || !show.getShowDate().equals(dto.getShowDate()) || !show.getShowTime().equals(dto.getShowTime())
                || show.getTicketPrice().compareTo(dto.getTicketPrice()) != 0;
        if (bookingDao.existsByShowShowId(id) && changed) {
            throw new BusinessException("A show with bookings cannot change its schedule, theatre, movie or price");
        }
        if (show.getStatus() != showStatus.SCHEDULED && dto.getStatus() != show.getStatus()) {
            throw new BusinessException("A completed or cancelled show cannot change status");
        }
        validateShow(dto, movie, theatre, id);
        show.setMovie(movie);
        show.setTheatre(theatre);
        show.setShowDate(dto.getShowDate());
        show.setShowTime(dto.getShowTime());
        show.setTicketPrice(dto.getTicketPrice());
        show.setStatus(dto.getStatus());
        if (dto.getStatus() == showStatus.CANCELLED) {
            bookingDao.findByShowShowIdAndStatusNot(id, bookingStatus.CANCELLED).forEach(bookingRules::cancel);
        }
        log.info("Updated show {} to {}", id, dto.getStatus());
        return conversion.toShowDTO(showDao.save(show));
    }

    @Override
    public void deleteShow(String id) {
        ShowEntity show = bookingRules.lockShow(id);
        if (bookingDao.existsByShowShowId(id)) { throw new BusinessException("A show with bookings cannot be deleted. Cancel it instead"); }
        showDao.delete(show);
        log.info("Deleted show {}", id);
    }

    @Override
    @Transactional(readOnly = true)
    public SeatAvailabilityDTO getSeats(String id) {
        ShowEntity show = findShow(id);
        List<String> allSeats = seatLayout.seats(show.getTheatre().getCapacity());
        Set<String> booked = new HashSet<>();
        for (BookingEntity booking : bookingDao.findByShowShowIdAndStatusNot(id, bookingStatus.CANCELLED)) {
            booked.addAll(Arrays.asList(booking.getSeatNumbers().split(",")));
        }
        return new SeatAvailabilityDTO(id, allSeats.size(), allSeats.stream().filter(booked::contains).toList(),
                allSeats.stream().filter(seat -> !booked.contains(seat)).toList());
    }

    private void validateShow(ShowDTO dto, MovieEntity movie, TheatreEntity theatre, String excludedId) {
        if (dto.getStatus() != showStatus.SCHEDULED) { return; }
        if (movie.getStatus() == movieStatus.ENDED) { throw new BusinessException("An ended movie cannot have new shows"); }
        if (theatre.getStatus() != theatreStatus.ACTIVE) { throw new BusinessException("Shows require an active theatre"); }
        LocalDateTime start = LocalDateTime.of(dto.getShowDate(), dto.getShowTime());
        if (!start.isAfter(LocalDateTime.now(BookingRules.SRI_LANKA))) { throw new BusinessException("Scheduled shows must start in the future"); }
        LocalDateTime end = start.plusMinutes(movie.getDuration());
        for (ShowEntity existing : showDao.findByTheatreTheatreIdAndStatus(theatre.getTheatreId(), showStatus.SCHEDULED)) {
            if (existing.getShowId().equals(excludedId)) { continue; }
            LocalDateTime existingStart = LocalDateTime.of(existing.getShowDate(), existing.getShowTime());
            LocalDateTime existingEnd = existingStart.plusMinutes(existing.getMovie().getDuration());
            if (start.isBefore(existingEnd) && end.isAfter(existingStart)) {
                throw new BusinessException("This theatre already has a show during the requested time");
            }
        }
    }

    private ShowEntity findShow(String id) { return showDao.findById(id).orElseThrow(() -> new ResourceNotFoundException("Show not found")); }
    private MovieEntity findMovie(String id) { return movieDao.findById(id).orElseThrow(() -> new ResourceNotFoundException("Movie not found")); }
    private TheatreEntity lockTheatre(String id) { return theatreDao.findForUpdate(id).orElseThrow(() -> new ResourceNotFoundException("Theatre not found")); }
}
