package com.redbus.user.controller;

import com.redbus.user.payload.BookingDetailsDto;
import com.redbus.user.payload.PassengerDetails;
import com.redbus.user.service.BookingService;
import com.redbus.util.EmailService;
import com.redbus.util.PdfGenerationService;  // Assuming you have a PdfGenerationService

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    private BookingService bookingService;
    private EmailService emailService;
    private PdfGenerationService pdfGenerationService;  // Inject PdfGenerationService

    public BookingController(BookingService bookingService, EmailService emailService, PdfGenerationService pdfGenerationService) {
        this.bookingService = bookingService;
        this.emailService = emailService;
        this.pdfGenerationService = pdfGenerationService;
    }

    @PostMapping
    public ResponseEntity<BookingDetailsDto> booking(
            @RequestParam("busId") String busId,
            @RequestParam("ticketId") String ticketId,
            @RequestBody PassengerDetails passengerDetails) {

        BookingDetailsDto booking = bookingService.createBooking(busId, ticketId, passengerDetails);

        if (booking != null) {
            // Generate PDF
            byte[] pdfBytes = pdfGenerationService.generatePdf(booking);

            // Send confirmation email with PDF attachment
            emailService.sendEmailWithAttachment(
                    passengerDetails.getEmail(),
                    "Booking confirmed. Booking Id: " + booking.getBookingId(),
                    "Your booking is confirmed. Name: " + passengerDetails.getFirstName() + " " + passengerDetails.getLastName(),
                    pdfBytes,
                    "booking_details.pdf"
            );
        }

        return new ResponseEntity<>(booking, HttpStatus.OK);
    }
}
