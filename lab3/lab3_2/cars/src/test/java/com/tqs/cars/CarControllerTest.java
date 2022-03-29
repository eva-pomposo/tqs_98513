package com.tqs.cars;

import org.springframework.beans.factory.annotation.Autowired;
import com.tqs.cars.controller.CarController;
import com.tqs.cars.model.Car;
import com.tqs.cars.service.CarManagerService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import java.lang.StackWalker.Option;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CarController.class)
public class CarControllerTest {
    @Autowired
    private MockMvc mvc;    //entry point to the web framework

    @MockBean
    private CarManagerService service;

    @Test
    void whenPostCar_thenCreateCar( ) throws Exception {
        Car car = new Car("Buick", "Opel Cascada");
        
        when( service.save(Mockito.any()) ).thenReturn( car);

        mvc.perform(
            post("/api/createCar").contentType(MediaType.APPLICATION_JSON).content(JsonUtils.toJson(car)))
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.maker", is("Buick")))
            .andExpect(jsonPath("$.model", is("Opel Cascada")));


        verify(service, times(1)).save(Mockito.any());
    }

    @Test
    void givenManyCars_whenGetCars_thenReturnJsonArray() throws Exception {
        Car car1 = new Car("Buick", "Opel Cascada");
        Car car2 = new Car("Chevrolet", "Veraneio");
        Car car3 = new Car("Ferrari", "Ferrari Enzo");

        List<Car> allCars = Arrays.asList(car1, car2, car3);

        when( service.getAllCars()).thenReturn(allCars);

        mvc.perform(
            get("/api/cars").contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$", hasSize(3)))
            .andExpect(jsonPath("$[0].maker", is(car1.getMaker())))
            .andExpect(jsonPath("$[1].maker", is(car2.getMaker())))
            .andExpect(jsonPath("$[2].maker", is(car3.getMaker())))
            .andExpect(jsonPath("$[0].model", is(car1.getModel())))
            .andExpect(jsonPath("$[1].model", is(car2.getModel())))
            .andExpect(jsonPath("$[2].model", is(car3.getModel())));

        verify(service, times(1)).getAllCars();
    }

    @Test
    void givenCar_whenGetCarById_thenReturnJsonArray() throws Exception {
        Car car = new Car("Buick", "Opel Cascada");
        car.setCarId(1L);

        when( service.getCarDetails(1L)).thenReturn(Optional.of(car));
        mvc.perform(
            get("/api/carById/1").contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.maker", is(car.getMaker())))
            .andExpect(jsonPath("$.model", is(car.getModel())));

        verify(service, times(1)).getCarDetails(1L);
    }

    @Test
    void testGetCarById_withIdInvalid() throws Exception {
        mvc.perform(
            get("/api/carById/1"))
            .andExpect(status().isNotFound());

        verify(service, times(1)).getCarDetails(1L);
    }
}
