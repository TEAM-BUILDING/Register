package nl.rabobank.register.web.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import nl.rabobank.register.web.model.User;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/register")
public class RegisterController {
    private static final String CLAZZ = RegisterController.class.getName();
    private static final Logger LOGGER = Logger.getLogger(CLAZZ);

    {
        LOGGER.entering(CLAZZ,"init");
    }

    @RequestMapping(method = RequestMethod.GET)
    public String viewRegistration(Map<String, Object> model) {
        LOGGER.entering(CLAZZ,"viewRegistration");

        User userForm = new User();
        model.put("userForm", userForm);

        List<String> professionList = new ArrayList<String>();
        professionList.add("Developer");
        professionList.add("Designer");
        professionList.add("IT Manager");
        model.put("professionList", professionList);

        LOGGER.exiting(CLAZZ, "viewRegistration");
        return "Registration";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String processRegistration(@ModelAttribute("userForm") User user, BindingResult result) {
        LOGGER.entering(CLAZZ,"processRegistration", user);

        if (result.hasFieldErrors()) {
            LOGGER.fine("Error in registration form");
            return "Registration";
        }

        LOGGER.exiting(CLAZZ, "processRegistration");
        return "RegistrationSuccess";
    }

}
