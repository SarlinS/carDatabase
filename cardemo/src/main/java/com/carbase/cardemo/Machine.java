//This is a Machine-class which Car-class will inherit

package com.carbase.cardemo;

public class Machine {
    public String brand;
    public int yearModel;

    public Machine(String brand, int yearModel) {
        this.brand = brand;
        this.yearModel = yearModel;
    }

    public String getBrand() {
        return this.brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getYearModel() {
        return this.yearModel;
    }

    public void setYearModel(int yearModel) {
        this.yearModel = yearModel;
    }

}
