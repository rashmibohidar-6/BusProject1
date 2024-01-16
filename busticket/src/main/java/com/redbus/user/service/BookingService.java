package com.redbus.user.service;

import com.redbus.operator.entity.BusOperator;
import com.redbus.operator.entity.TicketCost;
import com.redbus.operator.repository.BusOperatorRepository;
import com.redbus.operator.repository.TicketCostRepository;
import com.redbus.user.entity.Booking;
import com.redbus.user.payload.BookingDetailsDto;
import com.redbus.user.payload.PassengerDetails;
import com.redbus.user.repository.BookingRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class BookingService {
    private BusOperatorRepository busOperatorRepository;
    private TicketCostRepository ticketCostRepository;
    private BookingRepository bookingRepository;

    public BookingService(BusOperatorRepository busOperatorRepository, TicketCostRepository ticketCostRepository, BookingRepository bookingRepository) {
        this.busOperatorRepository = busOperatorRepository;
        this.ticketCostRepository = ticketCostRepository;
        this.bookingRepository = bookingRepository;
    }

    public BookingDetailsDto createBooking(String busId,
                                           String ticketId,
                                           PassengerDetails passengerDetails){
        BusOperator bus = busOperatorRepository.findById(busId).get();
        TicketCost ticketCost = ticketCostRepository.findById(ticketId).get();

        Booking booking = new Booking();
        String bookingId = UUID.randomUUID().toString();
        booking.setBookingId(bookingId);
        booking.setBusId(busId);
        booking.setTicketId(ticketId);
        booking.setToCity(bus.getArrivalCity());
        booking.setFromCity(bus.setDepartureCity());
        booking.setBusCompany(bus.getBusOperatorCompanyName());
        booking.setPrice(ticketCost.getCost());
        booking.setFirstName(passengerDetails.getFirstName());
        booking.setFirstName(passengerDetails.getLastName());
        booking.setEmail(passengerDetails.getEmail());
        booking.setMobile(passengerDetails.getMobile());

        Booking ticketCreateDetails = bookingRepository.save(booking);

        BookingDetailsDto dto = new BookingDetailsDto();
        dto.setBookingId(ticketCreateDetails.getBookingId());
        dto.setFrom(ticketCreateDetails.getFromCity());
        dto.setTo(ticketCreateDetails.getToCity());
        dto.setFirstName(ticketCreateDetails.getFirstName());
        dto.setLastName(ticketCreateDetails.getLastName());
        dto.setPrice(ticketCreateDetails.getPrice());
        dto.setEmail(ticketCreateDetails.getEmail());
        dto.setMobile(ticketCreateDetails.getMobile());
        dto.setBusCompany(ticketCreateDetails.getBusCompany());
        return dto;
    }
}
