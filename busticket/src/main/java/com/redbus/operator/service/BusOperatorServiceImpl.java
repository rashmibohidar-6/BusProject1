package com.redbus.operator.service;

import com.redbus.operator.entity.BusOperator;
import com.redbus.operator.entity.TicketCost;
import com.redbus.operator.payload.BusOperatorDto;
import com.redbus.operator.repository.BusOperatorRepository;
import com.redbus.operator.repository.TicketCostRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

@Service
public class BusOperatorServiceImpl implements BusOperatorService {
    private final BusOperatorRepository busOperatorRepository;
    private final ModelMapper mapper;
    private final TicketCostRepository ticketCostRepository;

    @Autowired
    public BusOperatorServiceImpl(BusOperatorRepository busOperatorRepository, ModelMapper mapper, TicketCostRepository ticketCostRepository) {
        this.busOperatorRepository = busOperatorRepository;
        this.mapper = mapper;
        this.ticketCostRepository = ticketCostRepository;
    }

    @Override
    public BusOperatorDto scheduleBus(BusOperatorDto busOperatorDto) {
        // Generate a random UUID for the busId
        String busId = UUID.randomUUID().toString();

        // Map the DTO to an entity
        BusOperator busOperator = mapToEntity(busOperatorDto);

        TicketCost ticketCost = new TicketCost();
        ticketCost.setCost((busOperatorDto.getTicketCost().getTicketId()));
        ticketCost.setCode((busOperatorDto.getTicketCost().getCost()));
        ticketCost.setCode(busOperatorDto.getTicketCost().getCode());
        ticketCost.setDiscountAmount(busOperatorDto.getTicketCost().getDiscountAmount());

        busOperator.setBusId(busId);
        busOperator.setTicketCost(ticketCost);

        // Save the entity
        BusOperator savedBusSchedule = busOperatorRepository.save(busOperator);

        // Map the saved entity back to DTO
        return mapToDto(savedBusSchedule);
    }

    @Override
    public List<BusOperator> getAllBusOperators() {
        return busOperatorRepository.findAll();
    }

    // Add other methods as needed

    private BusOperator mapToEntity(BusOperatorDto busOperatorDto) {
        return mapper.map(busOperatorDto, BusOperator.class);
    }

    private BusOperatorDto mapToDto(BusOperator busOperator) {
        return mapper.map(busOperator, BusOperatorDto.class);
    }
}

