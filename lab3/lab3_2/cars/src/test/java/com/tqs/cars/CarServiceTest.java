package com.tqs.cars;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.internal.verification.VerificationModeFactory;
import org.mockito.junit.jupiter.MockitoExtension;
import com.tqs.cars.model.Car;
import com.tqs.cars.repository.CarRepository;
import com.tqs.cars.service.CarManagerService;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class CarServiceTest {

    @Mock( lenient = true)
    private CarRepository repository;

    @InjectMocks
    private CarManagerService service;

    @BeforeEach
    public void setUp() {

        Car car = new Car("Buick", "Opel Cascada");
        car.setCarId(1L);

        Car car2 = new Car("Chevrolet", "Veraneio");
        Car car3 = new Car("Ferrari", "Ferrari Enzo");

        List<Car> allCars = Arrays.asList(car, car2, car3);

        Mockito.when(repository.findByCarId(car.getCarId())).thenReturn(car);
        Mockito.when(repository.findAll()).thenReturn(allCars);
        Mockito.when(repository.findByCarId(-99L)).thenReturn(null);

    }

    @Test
     void whenValidId_thenCarShouldBeFound() {

        Car fromDb = service.getCarDetails(1L).get();
        assertThat(fromDb.getMaker()).isEqualTo("Buick");
        
        verifyFindByIdIsCalledOnce();

    }

    @Test
     void whenInValidId_thenCarShouldNotBeFound() throws NullPointerException {

        assertThrows(NullPointerException.class, () -> {
            Optional<Car> fromDb = service.getCarDetails(99L);
            assertThat(fromDb).isNull();

        });

        verifyFindByIdIsCalledOnce();

    }

    @Test
     void given3Cars_whengetAll_thenReturn3Records() {

        Car car = new Car("Buick", "Opel Cascada");
        Car car2 = new Car("Chevrolet", "Veraneio");
        Car car3 = new Car("Ferrari", "Ferrari Enzo");

        List<Car> allCars = service.getAllCars();
        verifyFindAllCarsIsCalledOnce();
        assertThat(allCars).hasSize(3).extracting(Car::getMaker).contains(car.getMaker(), car2.getMaker(), car3.getMaker());

    }

    private void verifyFindByIdIsCalledOnce() {
        Mockito.verify(repository, VerificationModeFactory.times(1)).findByCarId(Mockito.anyLong());
    }

    private void verifyFindAllCarsIsCalledOnce() {
        Mockito.verify(repository, VerificationModeFactory.times(1)).findAll();
    }


}
