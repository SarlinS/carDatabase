//This is the Car-class

package com.carbase.cardemo;

//Car inherits Machine-class properties brand and yearModel
public class Car extends Machine{
    private int id;

    public Car(Integer id, String brand, Integer yearModel) {
        super(brand, yearModel);
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
