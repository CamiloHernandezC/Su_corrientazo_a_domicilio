package com.sucorrientazoadomicilio;

import com.sucorrientazoadomicilio.model.Dron;
import com.sucorrientazoadomicilio.model.Orientation;
import com.sucorrientazoadomicilio.utils.Parameters;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class DronTest {

    final Random r  = new Random();

    @Test
    void move() {
        Dron dron = new Dron();
        int initialX = r.nextInt(Parameters.RESTAURANT_COVERAGE *2)-Parameters.RESTAURANT_COVERAGE;
        int initialY = r.nextInt(Parameters.RESTAURANT_COVERAGE *2)-Parameters.RESTAURANT_COVERAGE;
        int distance = r.nextInt(Parameters.RESTAURANT_COVERAGE);
        Orientation[] orientationValues = Orientation.values();
        Orientation initialOrientation = orientationValues[r.nextInt(orientationValues.length)];
        dron.setPositionX(initialX);
        dron.setPositionY(initialY);
        dron.setOrientation(initialOrientation);
        dron.move(distance);
        int expectedX = initialX;
        int expectedY = initialY;
        switch (initialOrientation){
            case NORTE:
                expectedY = initialY + distance;
                break;
            case SUR:
                expectedY = initialY - distance;
                break;
            case ORIENTE:
                expectedX = initialX + distance;
                break;
            case OCCIDENTE:
                expectedX = initialX - distance;
                break;
        }

        assertTrue(expectedX == dron.getPositionX() && expectedY == dron.getPositionY());

    }

    @Test
    void turnClockWise() {
        Dron dron = new Dron();
        Orientation[] orientationValues = Orientation.values();
        Orientation initialOrientation = orientationValues[r.nextInt(orientationValues.length)];
        dron.setOrientation(initialOrientation);
        Orientation expectedOrientation = null;
        switch (initialOrientation) {
            case NORTE:
                expectedOrientation = Orientation.ORIENTE;
                break;
            case ORIENTE:
                expectedOrientation = Orientation.SUR;
                break;
            case SUR:
                expectedOrientation = Orientation.OCCIDENTE;
                break;
            case OCCIDENTE:
                expectedOrientation = Orientation.NORTE;
                break;
            default:
                fail();
        }
        dron.turnClockWise();
        assertEquals(expectedOrientation,dron.getOrientation());
    }

    @Test
    void turnCounterClockWise() {
        Dron dron = new Dron();
        Orientation[] orientationValues = Orientation.values();
        Orientation initialOrientation = orientationValues[r.nextInt(orientationValues.length)];
        dron.setOrientation(initialOrientation);
        Orientation expectedOrientation = null;
        switch (initialOrientation) {
            case NORTE:
                expectedOrientation = Orientation.OCCIDENTE;
                break;
            case OCCIDENTE:
                expectedOrientation = Orientation.SUR;
                break;
            case SUR:
                expectedOrientation = Orientation.ORIENTE;
                break;
            case ORIENTE:
                expectedOrientation = Orientation.NORTE;
                break;
            default:
                fail();
        }
        dron.turnCounterClockWise();
        assertEquals(expectedOrientation,dron.getOrientation());
    }
}