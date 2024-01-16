package com.redbus.user.service;

import com.redbus.operator.entity.BusOperator;
import com.redbus.operator.repository.BusOperatorRepository;
import com.redbus.user.payload.BusListDto;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SearchBusesService {
    private BusOperatorRepository busOperatorRepository;

    public SearchBusesService(BusOperatorRepository busOperatorRepository) {
        this.busOperatorRepository = busOperatorRepository;
    }

    public List<BusListDto> searchBusBy(String departureCity, String arrivalCity, Date departureDate){
       List<BusOperator> busesAvailable = busOperatorRepository.findByDepartureCityAndArrivalCityAndDepartureDate(departureCity, arrivalCity, departureDate);
       List<BusListDto> dtos = busesAvailable.stream().map(bus->mapToDto(bus)).collect(Collectors.toList());

       return dtos;
    }

    BusListDto mapToDto(BusOperator busOperator){
        BusListDto busListDto = new BusListDto();
        busListDto.setBusId(busOperator.getBusId());
        busListDto.setBusNumber(busOperator.getBusNumber());
        busListDto.setBusOperatorCompanyName(busOperator.getBusOperatorCompanyName());
        busListDto.setNumberSeats(busOperator.getNumberSeats());
        busListDto.setDepartureCity(busOperator.getDepartureCity());
        busListDto.setArrivalCity(busOperator.getArrivalCity());
        busListDto.setDepartureTime(busOperator.getDepartureTime());
        busListDto.setArrivalTime(busOperator.getArrivalTime());
        busListDto.setDepartureDate(busOperator.getDepartureDate());
        busListDto.setArrivalDate(busOperator.getArrivalDate());
        busListDto.setTotalTravelTime(busOperator.getTotalTravelTime());
        busListDto.setBusType(busOperator.getBusType());
        busListDto.setAmenities(busOperator.getAmenities());
        return busListDto;
    }
}
