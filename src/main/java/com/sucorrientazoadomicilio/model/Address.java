package com.sucorrientazoadomicilio.model;

import java.util.Objects;

public class Address {
    private int x;
    private int y;

    public Address(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return x == address.x &&
                y == address.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
