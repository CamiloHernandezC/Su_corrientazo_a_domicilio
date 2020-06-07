package com.sucorrientazoadomicilio.controller;

import com.sucorrientazoadomicilio.model.Address;
import com.sucorrientazoadomicilio.model.Movements;
import com.sucorrientazoadomicilio.model.Dron;
import com.sucorrientazoadomicilio.utils.Parameters;
import com.sucorrientazoadomicilio.utils.ResponseCode;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class OrderValidator {

    //<editor-fold desc="Attributes">
    //To calculate the final address and validate if it's inside boundaries
    private static final Logger logger = LogManager.getLogger(OrderValidator.class);
    private final Dron simulatedDron = new Dron();
    private final List<Address> validAddresses = new ArrayList<>();
    private final List<String> invalidOrders = new ArrayList<>();
    //</editor-fold>

    //<editor-fold desc="Getter-Setter">
    public List<String> getInvalidOrders() {
        return invalidOrders;
    }

    public List<Address> getValidAddresses() {
        return validAddresses;
    }
    //</editor-fold>

    /**
     * Validates a list of orders (for each order verify the coverage and correct move code)
     * and saves valid Adrressess and invalid Orders
     * @param orders from read file
     */
    public void validateAddresses(List<String> orders) {
        for(String order : orders){
            simulatedDron.restart();
            if (processOrder(order) != ResponseCode.OK){
                invalidOrders.add(order + " " + Parameters.INVALID_ORDER_CODE_MESSAGE);
                continue;
            }
            if(Math.abs(simulatedDron.getPositionX()) > Parameters.RESTAURANT_COVERAGE
                    || Math.abs(simulatedDron.getPositionY()) > Parameters.RESTAURANT_COVERAGE){
                invalidOrders.add(order + " " + Parameters.ADRRESS_OUT_OF_RANGE_MESSAGE);
            }else{
                validAddresses.add(new Address(simulatedDron.getPositionX(),simulatedDron.getPositionY()));
            }
        }
    }

    public ResponseCode processOrder(String orden) {
        for (String c : orden.split("")){
            Movements movement;
            try {
                movement = Movements.valueOf(c);
            }catch (IllegalArgumentException e){
                logger.error(e.getClass());
                return ResponseCode.INVALID_ORDER;
            }
            switch (movement) {
                case A:
                    simulatedDron.move(1);
                    break;
                case D:
                    simulatedDron.turnClockWise();
                    break;
                case I:
                    simulatedDron.turnCounterClockWise();
                    break;
                default:
                    return ResponseCode.INVALID_ORDER;
            }
        }
        return ResponseCode.OK;
    }
}
