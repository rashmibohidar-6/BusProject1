package com.redbus.operator.payload;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.redbus.operator.entity.TicketCost;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;
import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BusOperatorDto {
    private String busId;
    @NotEmpty
    private String busNumber;
    @NotEmpty
    @Size(min= 8, message = "Name should have at least 8 characters!!")
    private String busOperatorCompanyName;
    @NotEmpty
    @Size(min = 10, message = "Driver Name should have 10 characters!!")
    private String driverName;
    @NotEmpty
    private String supportStaff;

    private int numberSeats;
    @NotEmpty
    private String departureCity;
    @NotEmpty
    private String arrivalCity;

    @JsonFormat(pattern = "HH:mm:ss")
    private LocalTime departureTime;

    @JsonFormat(pattern = "HH:mm:ss")
    private LocalTime arrivalTime;
    @NotNull //for date and time and for integer type use @NotNull for validation
    @JsonFormat(pattern = "dd/mm/yy")
    private Date departureDate;
    @NotNull
    @JsonFormat(pattern = "dd/mm/yy")
    private Date arrivalDate;

    private double totalTravelTime;
    @NotEmpty
    private String busType;
    @NotEmpty
    private String amenities;

    private TicketCost ticketCost;
}
