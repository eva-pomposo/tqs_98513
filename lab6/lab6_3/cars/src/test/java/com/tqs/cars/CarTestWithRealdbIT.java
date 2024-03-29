package com.tqs.cars;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;

import java.util.List;

import com.tqs.cars.model.Car;
import com.tqs.cars.repository.CarRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestPropertySource( locations = "application-integrationtest.properties")
class CarTestWithRealdbIT {
    // will need to use the server port for the invocation url
    @LocalServerPort
    int randomServerPort;

    // a REST client that is test-friendly
    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private CarRepository repository;

    @AfterEach
    public void resetDb() {
        repository.deleteAll();
    }

    @Test
     void whenValidInput_thenCreateCar() {
        Car car = new Car("Buick", "Opel Cascada");
        ResponseEntity<Car> entity = restTemplate.postForEntity("/api/createCar", car, Car.class);

        List<Car> found = repository.findAll();
        assertThat(found).extracting(Car::getMaker).containsOnly("Buick");
    }

    @Test
     void givenEmployees_whenGetEmployees_thenStatus200()  {
        createTestCar("Buick", "Opel Cascada");
        createTestCar("Chevrolet", "Veraneio");


        ResponseEntity<List<Car>> response = restTemplate
                .exchange("/api/cars", HttpMethod.GET, null, new ParameterizedTypeReference<List<Car>>() {
                });

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).extracting(Car::getMaker).containsExactly("Buick", "Chevrolet");

    }

    @Test
    void testGetCarById_withIdValid(){
        Car car = new Car("Buick", "Opel Cascada");
        repository.saveAndFlush(car);

        ResponseEntity<Car> response = restTemplate
        .exchange("/api/carById/" + car.getCarId() , HttpMethod.GET, null, new ParameterizedTypeReference<Car>() {
        });

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).extracting(Car::getMaker).isEqualTo("Buick");

    }

    @Test
    void testGetCarById_withIdInvalid(){
        ResponseEntity<Car> response = restTemplate
        .exchange("/api/carById/-9", HttpMethod.GET, null, new ParameterizedTypeReference<Car>() {
        });

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    private void createTestCar(String maker, String model) {
        Car car = new Car(maker, model);
        repository.saveAndFlush(car);
    }
}