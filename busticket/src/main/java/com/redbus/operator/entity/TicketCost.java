package com.redbus.operator.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "ticket_cost")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TicketCost {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ticket_id", unique = true)
    private String ticketId;

    @OneToOne
    @JoinColumn(name = "bus_id")
    private BusOperator busOperator;

    private double cost;
    private String code;

    @Column(name = "discount_ammount", unique = true)
    private double discountAmount;

    public void setCost(String ticketId) {
    }

    public void setCode(double cost) {
    }

    public void setCode(String code) {
    }
}
