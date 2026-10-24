package com.example.carmanager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
@Controller
public class CarController {
    private final CarRepository repository;
    public CarController(CarRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("cars", repository.findAll());
        model.addAttribute("car", new Car());
        return "index";
    }

    @PostMapping("/add")
    public String addCar(@ModelAttribute Car car) {
        repository.save(car);
        return "redirect:/";
    }

    @PostMapping("/edit/{id}")
    public String editCar(
            @PathVariable Long id,
            @ModelAttribute Car updatedCar) {
        Car car = repository.findById(id).orElseThrow();
        car.setBrand(updatedCar.getBrand());
        car.setModel(updatedCar.getModel());
        car.setYear(updatedCar.getYear());
        car.setEngine(updatedCar.getEngine());
        car.setDisplacement(updatedCar.getDisplacement());
        car.setHorsepower(updatedCar.getHorsepower());
        car.setWeight(updatedCar.getWeight());
        car.setTopSpeed(updatedCar.getTopSpeed());
        car.setLayout(updatedCar.getLayout());
        car.setAspiration(updatedCar.getAspiration());
        car.setImageUrl(updatedCar.getImageUrl());
        repository.save(car);
        return "redirect:/";
    }

    @PostMapping("/delete/{id}")
    public String deleteCar(@PathVariable Long id) {
        repository.deleteById(id);
        return "redirect:/";
    }
}