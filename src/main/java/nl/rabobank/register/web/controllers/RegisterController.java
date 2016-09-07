package nl.rabobank.register.web.controllers;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import nl.rabobank.register.web.model.User;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/register")
public class RegisterController {
    private static final String CLAZZ = RegisterController.class.getName();
    private static final Logger LOGGER = Logger.getLogger(CLAZZ);
    private static final Map<String,User> database = new HashMap<String, User>();

    {
        LOGGER.entering(CLAZZ,"init");
    }

    @RequestMapping(method = RequestMethod.GET)
    public String viewRegistration(Map<String, Object> model) {
        LOGGER.entering(CLAZZ,"viewRegistration");

        User userForm = new User();
        model.put("userForm", userForm);

        fillProfessionList(model);

        LOGGER.exiting(CLAZZ, "viewRegistration");
        return "Registration";
    }

    private void fillProfessionList(Map<String, Object> model) {
        List<String> professionList = new ArrayList<String>();
        professionList.add("Developer");
        professionList.add("Designer");
        professionList.add("IT Manager");
        model.put("professionList", professionList);
    }

    @RequestMapping(method = RequestMethod.POST)
    public String processRegistration(@Valid @ModelAttribute("userForm") User user, BindingResult result) {
        LOGGER.entering(CLAZZ,"processRegistration", user);

        if( database.containsKey(user.getUsername())){
            result.addError(
                    new FieldError("userForm", "username", "User already exists")
            );
        }

        if (result.hasFieldErrors()) {
            LOGGER.fine("Error in registration form");
            return "Registration";
        }

        database.put(user.getUsername(), user);

        LOGGER.exiting(CLAZZ, "processRegistration");
        return "RegistrationSuccess";
    }

}
