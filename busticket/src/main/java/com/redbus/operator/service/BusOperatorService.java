package com.redbus.operator.service;

import com.redbus.operator.entity.BusOperator;
import com.redbus.operator.payload.BusOperatorDto;

import java.util.List;

public interface BusOperatorService {
    BusOperatorDto scheduleBus(BusOperatorDto busOperatorDto);

//    BusOperator getBusOperatorByName(String busOperatorCompanyName);
//
    List<BusOperator> getAllBusOperators();
//
//    BusOperator createBusOperator(BusOperator busOperator);
//
//    BusOperator updateBusOperator(String busId, BusOperator updatedBusOperator);
//
//    void deleteBusOperator(String busId);
}

