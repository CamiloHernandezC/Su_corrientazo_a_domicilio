package com.sucorrientazoadomicilio;

import com.sucorrientazoadomicilio.controller.FlowController;
import com.sucorrientazoadomicilio.utils.Parameters;

public class Main {
    public static void main(String[] args) {

        Thread[] controllers = new Thread [Parameters.NUMBER_OF_DRONES];
         for(int dronID = 0; dronID < Parameters.NUMBER_OF_DRONES; dronID++){
             controllers[dronID] = new Thread(new FlowController(dronID));
             controllers[dronID].start();
         }
    }
}
