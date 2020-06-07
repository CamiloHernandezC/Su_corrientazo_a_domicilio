package com.sucorrientazoadomicilio.controller;


import com.sucorrientazoadomicilio.model.Address;
import com.sucorrientazoadomicilio.model.Orientation;
import com.sucorrientazoadomicilio.utils.Parameters;
import com.sucorrientazoadomicilio.model.Dron;

import java.util.ArrayList;
import java.util.List;

public class DronController {

    //<editor-fold desc="Attributes">
    private final Dron dron = new Dron();
    private final List<String> locations = new ArrayList<>();
    //</editor-fold>

    //<editor-fold desc="Getter-Setter">
    public List<String> getLocations() {
        return locations;
    }

    public Dron getDron() { return dron; }
    //</editor-fold>

    public String getDronLocation(){
        return "(" + dron.getPositionX() + ", " + dron.getPositionY() + ") dirección " + dron.getOrientation().toString();
    }

    public void goToAddress(int x, int y) {
        if(dron.getPositionX() != x){
            if(dron.getPositionX() < x){
                changeOrientation(Orientation.ORIENTE);
            }else if(dron.getPositionX() > x){
                changeOrientation(Orientation.OCCIDENTE);
            }
            dron.move(Math.abs(dron.getPositionX() - x));
        }
        if(dron.getPositionY() != y) {
            if (dron.getPositionY() < y) {
                changeOrientation(Orientation.NORTE);
            } else {
                changeOrientation(Orientation.SUR);
            }
            dron.move(Math.abs(dron.getPositionY() - y));
        }
    }

    private void changeOrientation(Orientation desiredOrientation) {
        int dronOrientationInt = Orientation.getOrientationIntValue(dron.getOrientation());
        int desiredOrientationInt = Orientation.getOrientationIntValue(desiredOrientation);

        //turningSense = 0 -> counterclockwise; 1 -> clock wise;
        boolean turningSense;
        if(dronOrientationInt < desiredOrientationInt) {
            turningSense = Math.abs(dronOrientationInt - desiredOrientationInt) == 3;
        }else {
            turningSense = (Math.abs(dronOrientationInt - desiredOrientationInt) != 3);
        }

        while (desiredOrientation != dron.getOrientation()){
            if (turningSense) {
                dron.turnClockWise();
            } else {
                dron.turnCounterClockWise();
            }
        }
    }

    public void deliverOrders(List<Address> validAddresses) {
        //Each dron can carry Parameters.MAX_ORDERS_PER_TIME each travel, then it have to comeback to restaurant
        int travelsCounter = 0;
        for (Address address : validAddresses) {
            travelsCounter++;
            goToAddress(address.getX(), address.getY());
            locations.add(getDronLocation());
            if((travelsCounter % Parameters.MAX_ORDERS_PER_TIME) == 0){
                goToAddress(0,0);
            }
        }
        //Once dron has delivered all orders go back to restaurant
        if(dron.getPositionX() != 0 || dron.getPositionY() != 0){
            goToAddress(0,0);
        }
    }
}
