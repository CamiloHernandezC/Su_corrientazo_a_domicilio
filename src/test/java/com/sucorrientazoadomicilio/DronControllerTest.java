package com.sucorrientazoadomicilio;

import com.sucorrientazoadomicilio.controller.DronController;
import com.sucorrientazoadomicilio.model.Dron;
import com.sucorrientazoadomicilio.utils.Parameters;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class DronControllerTest {

    final Random r  = new Random();

    @Test
    void goToAddress() {
        int expectedX = r.nextInt(Parameters.RESTAURANT_COVERAGE *2)-Parameters.RESTAURANT_COVERAGE;
        int expectedY = r.nextInt(Parameters.RESTAURANT_COVERAGE *2)-Parameters.RESTAURANT_COVERAGE;
        DronController dronController = new DronController();
        dronController.goToAddress(expectedX, expectedY);
        Dron dron = dronController.getDron();
        assertTrue(expectedX == dron.getPositionX() && expectedY == dron.getPositionY());
    }
}