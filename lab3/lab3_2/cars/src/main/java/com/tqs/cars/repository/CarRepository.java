package com.tqs.cars.repository;

import java.util.List;

import com.tqs.cars.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepository extends JpaRepository<Car,Long> {
    public Car findByCarId(Long id);
    public List<Car> findAll();
}
