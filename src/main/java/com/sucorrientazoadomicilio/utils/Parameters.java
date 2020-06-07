package com.sucorrientazoadomicilio.utils;

public final class Parameters {


    public static final String WRITE_DRON_PATH = "./outputFiles/";
    public static final String WRITE_INVALID_ORDERS_PATH = WRITE_DRON_PATH + "invalidOrders/";
    public static final String WRITE_DRON_ORDERS_PREFIX = "out";
    public static final String WRITE_DRON_INVALID_ORDERS_PREFIX = "invalid";
    public static final String WRITE_DRON_ORDERS_EXTENSION = ".txt";
    public static final String READ_DRON_PATH = "./inputFiles/";
    public static final String READ_DRON_ORDERS_PREFIX = "in";
    public static final String READ_DRON_ORDERS_EXTENSION = ".txt";
    public static final String INVALID_ORDER_CODE_MESSAGE = "Caracter invalido";
    public static final String ADRRESS_OUT_OF_RANGE_MESSAGE = "Dirección fuera del alcance";
    public static final String UNEXPECTED_VALUE_MESSAGE = "Valor no esperado";
    public static final String DIRECTORY_NOT_CREATED = "Directory was not created";
    public static final String EXECUTION_FINALIZED_MESSAGE = "Ejecución terminada." ;

    public static final int NUMBER_OF_DRONES = 20;
    public static final int MAX_ORDERS_PER_TIME = 3;
    public static final int RESTAURANT_COVERAGE = 10;



    private Parameters() {
    }
}
