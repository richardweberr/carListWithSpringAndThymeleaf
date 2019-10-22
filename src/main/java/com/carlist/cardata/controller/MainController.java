package com.carlist.cardata.controller;

import com.carlist.cardata.form.CarForm;
import com.carlist.cardata.model.Car;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;

@Controller
public class MainController {
    private static List<Car> cars = new ArrayList<Car>();

    static {
        cars.add(new Car("Audi", "A2"));
        cars.add(new Car("BMW", "116i"));
    }

    // Inject via application.properties
    @Value("${welcome.message}")
    private String message;
    @Value("${error.message}")
    private String errorMessage;

    @RequestMapping(value = {"/", "/index"}, method = RequestMethod.GET)
    public String index(Model model) {
        model.addAttribute("message", message);
        return "index";
    }

    @RequestMapping(value = {"/carList"}, method = RequestMethod.GET)
    public String carList(Model model) {
        model.addAttribute("cars", cars);
        return "carList";
    }

    @RequestMapping(value = {"/addCar"}, method = RequestMethod.GET)
    public String showAddCarPage(Model model) {
        CarForm carForm = new CarForm();
        model.addAttribute("carForm", carForm);
        return "addCar";
    }

    //partie ROUTER, methode GET sur l'url /addcar
    @RequestMapping(value = {"/addCar"}, method = RequestMethod.POST)
    //partie CONTOLLER, passe le modèle model de classe (SPRING) Model au template (return "addCar") ou une URL return "redirect:/carList"
    public String saveCar(Model model, @ModelAttribute("carForm") CarForm carForm) {
        String make = carForm.getMake();
        String carmodel = carForm.getCarModel();
        if (make != null && make.length() > 0 && carmodel != null && carmodel.length() > 0) {
            Car newCar = new Car(make, carmodel);
            cars.add(newCar);
            return "redirect:/carList";
        }
        model.addAttribute("errorMessage", errorMessage);
        return "addCar";
    }
}
