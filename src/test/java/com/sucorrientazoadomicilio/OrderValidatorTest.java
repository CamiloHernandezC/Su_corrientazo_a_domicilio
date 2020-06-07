package com.sucorrientazoadomicilio;

import com.sucorrientazoadomicilio.controller.OrderValidator;
import com.sucorrientazoadomicilio.model.Address;
import com.sucorrientazoadomicilio.utils.Parameters;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OrderValidatorTest {

    @Test
    void validateAddresses() {
        OrderValidator orderValidator = new OrderValidator();
        List<String> ordersList = new ArrayList<>();
        StringBuilder invalidOrder = new StringBuilder("AAIDC");
        String validOrder = "AAAAIAA";
        ordersList.add(invalidOrder.toString());
        ordersList.add(validOrder);
        orderValidator.validateAddresses(ordersList);
        invalidOrder.append(" ");
        invalidOrder.append(Parameters.INVALID_ORDER_CODE_MESSAGE);
        List<String> invalidOrdersList = new ArrayList<>();
        List<Address> validAddress = new ArrayList<>();
        invalidOrdersList.add(invalidOrder.toString());
        validAddress.add(new Address(-2,4));
        assertAll(
                () -> assertIterableEquals(invalidOrdersList, orderValidator.getInvalidOrders()),
                () -> assertIterableEquals(validAddress, orderValidator.getValidAddresses())
        );

    }
}