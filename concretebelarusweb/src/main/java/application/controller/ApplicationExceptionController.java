package application.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


import static application.utils.Constant.*;

@Controller
@RequiredArgsConstructor
public class ApplicationExceptionController {

    @GetMapping(path = "/error")
    public String sendErrorMessage(Model model, String message, String pathToReturn) {
        model.addAttribute(ERROR_MESSAGE, message != null ? message : DEFAULT_ERROR_MESSAGE);
        model.addAttribute(PATH_TO_RETURN, pathToReturn != null ? pathToReturn : AUTHORIZATION_PAGE);
        return ERROR;
    }

}
