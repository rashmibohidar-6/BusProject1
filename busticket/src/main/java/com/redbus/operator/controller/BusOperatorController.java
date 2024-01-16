package com.redbus.operator.controller;

import com.redbus.operator.entity.BusOperator;
import com.redbus.operator.payload.BusOperatorDto;
import com.redbus.operator.service.BusOperatorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bus-operators")
public class BusOperatorController {

    private final BusOperatorService busOperatorService;

    @Autowired
    public BusOperatorController(BusOperatorService busOperatorService) {
        this.busOperatorService = busOperatorService;
    }

    @PostMapping
    public ResponseEntity<?> scheduleBus(@Valid @RequestBody BusOperatorDto busOperatorDto, BindingResult result) {

        if(result.hasErrors()){
            return new ResponseEntity<>(result.getFieldError().getDefaultMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        BusOperatorDto dto = busOperatorService.scheduleBus(busOperatorDto);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

//    @GetMapping("/{bus_operator_company_name}")
//    public ResponseEntity<BusOperator> getBusOperatorByName(@PathVariable String busOperatorCompanyName) {
//        BusOperator busOperator = busOperatorService.getBusOperatorByName(busOperatorCompanyName);
//        if (busOperator != null) {
//            return ResponseEntity.ok(busOperator);
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }

    @GetMapping
    public ResponseEntity<List<BusOperator>> getAllBusOperators() {
        List<BusOperator> busOperators = busOperatorService.getAllBusOperators();
        return ResponseEntity.ok(busOperators);
    }

//    @PostMapping
//    public ResponseEntity<BusOperator> createBusOperator(@RequestBody BusOperator busOperator) {
//        BusOperator createdBusOperator = busOperatorService.createBusOperator(busOperator);
//        return new ResponseEntity<>(createdBusOperator, HttpStatus.CREATED);
//    }

}

