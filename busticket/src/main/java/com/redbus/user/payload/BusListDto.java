package com.redbus.user.payload;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;
import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BusListDto {
    private String busId;
    private String busNumber;
    @Size(min= 8, message = "Name should have at least 8 characters!!")
    private String busOperatorCompanyName;

    private int numberSeats;
    private String departureCity;
    private String arrivalCity;
    @JsonFormat(pattern = "HH:mm:ss")
    private LocalTime departureTime;

    @JsonFormat(pattern = "HH:mm:ss")
    private LocalTime arrivalTime;

    @JsonFormat(pattern = "dd/mm/yy")
    private Date departureDate;
    @JsonFormat(pattern = "dd/mm/yy")
    private Date arrivalDate;

    private double totalTravelTime;
    private String busType;
    private String amenities;

}
