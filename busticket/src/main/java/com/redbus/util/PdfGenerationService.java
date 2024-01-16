package com.redbus.util;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.redbus.user.payload.BookingDetailsDto;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Service
public class PdfGenerationService {
    @Autowired
    private EmailService emailService;

    public void generateAndSendPdf(BookingDetailsDto bookingDetails) throws DocumentException, MessagingException, IOException {
        byte[] pdfBytes = generatePdf(bookingDetails);

        // Attach PDF to the email
        MimeMessage message = emailService.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setTo(bookingDetails.getEmail());
        helper.setSubject("Booking Confirmation - Booking Id: " + bookingDetails.getBookingId());
        helper.setText("Your booking is confirmed. Details attached.");

        // Attach the PDF
        helper.addAttachment("booking_details.pdf", new ByteArrayResource(pdfBytes));

        // Send the email
        emailService.sendMimeMessage(message);
    }

    public byte[] generatePdf(BookingDetailsDto bookingDetails) throws DocumentException {
        Document document = new Document();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PdfWriter.getInstance(document, baos);

        document.open();

        addContent(document, bookingDetails);

        document.close();

        return baos.toByteArray();
    }

    private void addContent(Document document, BookingDetailsDto bookingDetails) throws DocumentException {
        document.add(new Paragraph("Booking ID: " + bookingDetails.getBookingId()));
        document.add(new Paragraph("Bus Company: " + bookingDetails.getBusCompany()));
        document.add(new Paragraph("From: " + bookingDetails.getFrom()));
        document.add(new Paragraph("To: " + bookingDetails.getTo()));
        document.add(new Paragraph("Name: " + bookingDetails.getFirstName() + " " + bookingDetails.getLastName()));
        document.add(new Paragraph("Email: " + bookingDetails.getEmail()));
        document.add(new Paragraph("Mobile: " + bookingDetails.getMobile()));
        document.add(new Paragraph("Price: $" + bookingDetails.getPrice()));
    }
}

