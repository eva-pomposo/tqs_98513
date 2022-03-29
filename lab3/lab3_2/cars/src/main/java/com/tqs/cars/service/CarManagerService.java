package com.tqs.cars.service;

import java.lang.StackWalker.Option;
import java.util.List;
import java.util.Optional;

import com.tqs.cars.model.Car;
import com.tqs.cars.repository.CarRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class CarManagerService {
    @Autowired
    private CarRepository repository;

    public Car save(Car car){
        return repository.save(car);
    }

    public List<Car> getAllCars(){
        return repository.findAll();
    }

    public Optional<Car> getCarDetails(Long id){
        return Optional.of(repository.findByCarId(id));
    }
}