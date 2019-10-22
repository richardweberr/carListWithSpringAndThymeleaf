package com.carlist.cardata.model;

public class Car {
    private String make;
    private String carModel;

    public Car() {
    }

    public Car(String make, String carModel) {
        this.make = make;
        this.carModel = carModel;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getCarModel() {
        return carModel;
    }

    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }
}

