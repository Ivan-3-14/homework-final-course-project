package application.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import static application.utils.Constant.*;

@Controller
public class ApplicationExceptionController {

    @GetMapping(path = "/error")
    public String sendErrorMessage(Model model, String message, String pathToReturn) {
        model.addAttribute(ERROR_MESSAGE, message);
        model.addAttribute(PATH_TO_RETURN, pathToReturn);
        return ERROR;
    }

}
