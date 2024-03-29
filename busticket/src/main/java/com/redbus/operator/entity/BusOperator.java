package com.redbus.operator.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.redbus.operator.util.CustomDateDeserializer;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;
import java.util.Date;

@Entity
@Table(name = "bus_operator")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class BusOperator {
    @Id
    @Column(name = "bus_id")
    private String busId;

    @OneToOne(mappedBy = "busOperator", cascade = CascadeType.ALL)
    private TicketCost ticketCost;

    @Column(name = "bus_number")
    private String busNumber;
    @Column(name = "bus_operator_company_name")
    private String busOperatorCompanyName;
    @Column(name = "driver_name")
    private String driverName;
    @Column(name = "support_staff")
    private String supportStaff;
    @Column(name = "number_seats")
    private int numberSeats;
    @Column(name = "departure_city")
    private String departureCity;
    @Column(name = "arrival_city")
    private String arrivalCity;
//    @JsonFormat(pattern = "HH:mm:ss")
    @Column(name = "departure_time")
    private LocalTime departureTime;
//    @JsonFormat(pattern = "HH:mm:ss")
    @Column(name = "arrival_time")
    private LocalTime arrivalTime;
    @JsonFormat(pattern = "dd/mm/yy") //define desire date format
    @JsonDeserialize(using = CustomDateDeserializer.class)
    @Temporal(TemporalType.DATE)
    @Column(name = "departure_date")
    private Date departureDate;
    @JsonFormat(pattern = "dd/mm/yy")
    @JsonDeserialize(using = CustomDateDeserializer.class)
    @Column(name = "arrival_date")
    @Temporal(TemporalType.DATE)
    private Date arrivalDate;
    @Column(name = "total_travel_time")
    private double totalTravelTime;
    @Column(name = "bus_type")
    private String busType;
    @Column(name = "amenities")
    private String amenities;

    public String setDepartureCity() {
        return null;
    }
}
