package com.carbase.cardemo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserRestController {

    private CarService cService = new CarService();

    @Autowired
    public void UserController(CarService cService) {
        this.cService = cService;
    }
    
    //The index page
    @GetMapping("/")
    public String welcome(){
        return "Welcome to the unofficial car database!";
    }

    //GET-mapping for a specific car
    @GetMapping("/car/{id}")                                    
    public ResponseEntity<?> getCar(@PathVariable Integer id){
        Car car = cService.getCar(id);
        if (car != null){
            return ResponseEntity.status(HttpStatus.OK).body(car);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Car id " + id + " was not found.");
    }

    //GET-mapping for all cars with a slightly different approach
    @GetMapping("/cars")                                        
    
    public ResponseEntity<?> getCars() {
        if (cService.getCars().isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No cars were found.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(cService.getCars());
    }

    //PUT-mapping for updating a car
    @PutMapping("/car/{id}")                                    
    public ResponseEntity<?> updateCar(@PathVariable Integer id, @RequestParam String newBrand, @RequestParam Integer newYearModel) {
        Car car = cService.getCar(id);

        if (car == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Car id " + id + " was not found.");
        }

        car.setBrand(newBrand);
        car.setYearModel(newYearModel);
        return ResponseEntity.status(HttpStatus.OK).body("Car with id " + id + " has been updated in the database.");
    }

    //POST-mapping for adding a new car
    @PostMapping("/car")
    public ResponseEntity<?> addCar(@RequestParam Integer id, @RequestParam String brand, @RequestParam Integer yearModel) {
        Car car = cService.getCar(id);
        
        if (car != null){
        return ResponseEntity.status(HttpStatus.CONFLICT).body("This car with id " + id + " is already in the database.");
    }

        cService.addCar(id, brand, yearModel);

        return ResponseEntity.status(HttpStatus.CREATED).body("A new car with id " + id + " has been created.");
    }

    //DELETE-mapping for deleting a car
    @DeleteMapping("/car/{id}")
    public ResponseEntity<?> deleteCar(@PathVariable Integer id) {
        Car car = cService.getCar(id);
        
        if (car == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Car id " + id + " was not found.");
        }
        cService.deleteCar(id);
        return ResponseEntity.status(HttpStatus.OK).body("Car with id " + id + " was deleted from the database.");
    }

    //Search-function for all cars in the database
    @GetMapping("/cars/search")
    public ResponseEntity<?> searchCars(@RequestParam String keyword){
        List<String> car = cService.searchCars(keyword);

        if (car.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No search results.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(cService.searchCars(keyword));
    }
}
