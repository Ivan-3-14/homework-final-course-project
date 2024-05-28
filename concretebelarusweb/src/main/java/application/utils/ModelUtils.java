package application.utils;

import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import static application.utils.Constant.*;

@Component
public class ModelUtils {

    public void addAttributesToModel(Model model, String nameOfObject, Double distanceToObject, Double volumeOfConcrete,
                                     String comment, String name, String surname, String telephoneNumber) {
        model.addAttribute(NAME, name);
        model.addAttribute(SURNAME, surname);
        model.addAttribute(TEL_NUMBER, telephoneNumber);
        model.addAttribute(DISTANCE, distanceToObject);
        model.addAttribute(VOLUME, volumeOfConcrete);
        model.addAttribute(OBJECT_NAME, nameOfObject);
        model.addAttribute(COMMENT, comment);
    }
}
