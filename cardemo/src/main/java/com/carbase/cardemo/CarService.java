//This is the service model for UserRestController

package com.carbase.cardemo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
//Constructor for CarService, which creates the new list automatically
public class CarService {
    private List<Car> cars;

    public CarService(){
        cars = new ArrayList<>();
        /*Car car1 = new Car(1,"Peugeot 304",1997);
        Car car2 = new Car(2,"Saab 900",1995);
        Car car3 = new Car(3,"Lada Samara",1980);
        cars.addAll(Arrays.asList(car1,car2,car3));*/ //These cars were part of early testing without Postman
    }

    //getCar method for GET-mapping (one car)
    public Car getCar(Integer id){
        for (Car car : cars) {
            if (car.getId() == id) {
                return car;
            }
        }
        return null;
    }

    //getCars method for GET-mapping (all cars)
    public List<Car> getCars(){
        return this.cars;
        }

    //addCar method for POST-mapping
    public Car addCar(Integer id, String brand, Integer yearModel){
        Car newCar = new Car(id, brand, yearModel);
        cars.add(newCar);
        return newCar;
    }

    //updateCar method for PUT-mapping
    public void updateCar(Integer id, String newBrand, Integer newYearModel) {
        for (Car car : cars) {
            if (car.getId() == id)
            {
                car.setBrand(newBrand);
                car.setYearModel(newYearModel);
                break;
            }
        }
    }

    //deleteCar method for DELETE-mapping
    public void deleteCar(Integer id){
        for (Car car : cars) {
            if (car.getId() == id) {
                cars.remove(car);
                break;
            }
        }
    }


    //searchCars method for searching functionality
    public List<String> searchCars(String keyword) {
        List<String> matchingCars = new ArrayList<>();
        for (Car car : cars) {
            if (car.getBrand().toLowerCase().contains(keyword.toLowerCase()) ||
                    String.valueOf(car.getYearModel()).contains(keyword)) {
                matchingCars.add("ID: " + car.getId() + ", brand: " + car.getBrand() + ", model: " + car.getYearModel());
            }
        }
        return matchingCars;
    }
}
