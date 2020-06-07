package com.sucorrientazoadomicilio.model;

public enum Orientation {
    NORTE,
    ORIENTE,
    SUR,
    OCCIDENTE;

    public static int getOrientationIntValue(Orientation orientation){
        switch (orientation){
            case NORTE:
                return 1;
            case ORIENTE:
                return 2;
            case SUR:
                return 3;
            case OCCIDENTE:
                return 4;
        }
        return 0;
    }
}