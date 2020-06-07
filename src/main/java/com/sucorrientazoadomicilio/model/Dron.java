package com.sucorrientazoadomicilio.model;


import com.sucorrientazoadomicilio.utils.Parameters;

public class Dron {

    //<editor-fold desc="Atributos">
    //Siempre inicializa en la posición 0,0,Norte. En el plano cardinal
    private int positionX;
    private int positionY;
    private Orientation orientation = Orientation.NORTE;
    //</editor-fold>

    //<editor-fold desc="Getter-Setter">
    public int getPositionX() {
        return positionX;
    }

    public void setPositionX(int positionX) {
        this.positionX = positionX;
    }

    public int getPositionY() {
        return positionY;
    }

    public void setPositionY(int positionY) {
        this.positionY = positionY;
    }

    public Orientation getOrientation() {
        return orientation;
    }

    public void setOrientation(Orientation orientation) {
        this.orientation = orientation;
    }
    //</editor-fold>

    public void move(int distance){
        switch (orientation){
            case NORTE:
                positionY += distance;
                break;
            case SUR:
                positionY -= distance;
                break;
            case ORIENTE:
                positionX += distance;
                break;
            case OCCIDENTE:
                positionX -= distance;
                break;
            default:
                throw new IllegalStateException(Parameters.UNEXPECTED_VALUE_MESSAGE + orientation);
        }
    }

    public void turnClockWise(){
        switch (orientation) {
            case NORTE:
                orientation = Orientation.ORIENTE;
                break;
            case ORIENTE:
                orientation = Orientation.SUR;
                break;
            case SUR:
                orientation = Orientation.OCCIDENTE;
                break;
            case OCCIDENTE:
                orientation = Orientation.NORTE;
                break;
            default:
                throw new IllegalStateException(Parameters.UNEXPECTED_VALUE_MESSAGE + orientation);
        }
    }

    public void turnCounterClockWise(){
        switch (orientation) {
            case NORTE:
                orientation = Orientation.OCCIDENTE;
                break;
            case OCCIDENTE:
                orientation = Orientation.SUR;
                break;
            case SUR:
                orientation = Orientation.ORIENTE;
                break;
            case ORIENTE:
                orientation = Orientation.NORTE;
                break;
            default:
                throw new IllegalStateException(Parameters.UNEXPECTED_VALUE_MESSAGE + orientation);
        }
    }

    /**
     * Only for simulatedDron
     */
    public void restart() {
        positionX = 0;
        positionY = 0;
        orientation = Orientation.NORTE;
    }
}
