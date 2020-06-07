package com.sucorrientazoadomicilio.controller;

import com.sucorrientazoadomicilio.model.Address;
import com.sucorrientazoadomicilio.utils.*;

import java.util.List;

public class FlowController implements Runnable{

    //<editor-fold desc="Attributes">
    private final DronController dronController = new DronController();
    private final OrderValidator orderValidator = new OrderValidator();
    private final DataReader dataReader = new TextReader();
    private final DataWriter dataWriter = new TextWriter();

    private final int dronID;
    //</editor-fold>

    public FlowController(int dronID) {
        this.dronID = dronID    ;
    }

    @Override
    public void run() {

        //For example the dron0's input file name is in01 and dron19's file name is in20
        String  fileNumber = String.valueOf(dronID+1);
        String dronFileName = dronID < 9 ? "0" + fileNumber : fileNumber;

        List<String> orders = dataReader.readData(Parameters.READ_DRON_PATH
                + Parameters.READ_DRON_ORDERS_PREFIX + dronFileName + Parameters.READ_DRON_ORDERS_EXTENSION);

        if(orders.isEmpty()){
            return;
        }

        orderValidator.validateAddresses(orders);
        List<Address> validAddresses = orderValidator.getValidAddresses();

        if(!validAddresses.isEmpty()) {
            dronController.deliverOrders(validAddresses);
            List<String> locations = dronController.getLocations();
            if(!locations.isEmpty()) {
                dataWriter.writeData(Parameters.WRITE_DRON_PATH,
                        Parameters.WRITE_DRON_ORDERS_PREFIX + dronFileName + Parameters.WRITE_DRON_ORDERS_EXTENSION,
                        locations);
            }
        }
        List<String> invalidOrders = orderValidator.getInvalidOrders();

        if(!invalidOrders.isEmpty()) {
            dataWriter.writeData(Parameters.WRITE_INVALID_ORDERS_PATH, Parameters.WRITE_DRON_INVALID_ORDERS_PREFIX + dronFileName + Parameters.WRITE_DRON_ORDERS_EXTENSION,
                    invalidOrders);
        }
    }
}
