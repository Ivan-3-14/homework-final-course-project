package application.controller;

import application.DTO.filtersDTO.PriceFilterToFront;
import application.DTO.usersDTO.ManagerDTO;
import application.DTO.usersDTO.UserDTO;
import application.entity.enums.roles.Roles;
import application.services.interfaces.ManagerService;
import application.services.interfaces.PriceService;
import application.services.interfaces.UserService;
import liquibase.pro.packaged.S;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;

import static application.utils.Constant.*;

@Controller
@RequiredArgsConstructor
public class AdminController {

    private final UserService userService;
    private final ManagerService managerService;
    private final PriceService priceService;
    private final ApplicationExceptionController applicationExceptionController;

    @GetMapping(path = "/adminMainPage")
    public String goToAdminMainPage(Model model) {
        List<UserDTO> list = userService.getAllManager();
        model.addAttribute(ALL_MANAGER_LIST, list);
        return MAIN_PAGE_ADMIN;
    }

    @GetMapping(path = "/myAccount")
    public String goToAccount() {
        return PERSONAL_ACCOUNT;
    }

    @PostMapping(path = "/toUpdateManager")
    public String toUpdateManager(Model model, @RequestParam Long userId) {
        model.addAttribute("currentManager", userService.readUser(userId));
        return UPDATE_MANAGER_FORM;
    }

    @PostMapping(path = "/updateManager")
    public String updateManager(Model model, UserDTO userDTO, @RequestParam Long userId) {
        userDTO.setId(userId);
        userDTO.setRole(Roles.MANAGER);
        userService.updateUser(userDTO);
        return goToAdminMainPage(model);
    }

    @PostMapping(path = "/deleteManager")
    public String deleteFromOrderList(Model model, @RequestParam Long userId) {
        userService.deleteUser(userId);
        return goToAdminMainPage(model);
    }

    @GetMapping(path = "/changePrice")
    public String goToChangePricePage(Model model) {
        PriceFilterToFront priceFilterToFront = priceService.getAllPrice();
        model.addAttribute(CONCRETE_PRICE_LIST, priceFilterToFront.getConcretePriceDTOList());
        model.addAttribute(AUTO_PRICE_LIST, priceFilterToFront.getAutoPriceDTOList());
        model.addAttribute(AUTO_CAPACITY_LIST, priceFilterToFront.getAutoCapacityDTOList());
        return CHANGE_PRICES;
    }


    @PostMapping(path = "/changeConcretePrice")
    public String changeConcretePrice(Model model, @RequestParam Long tempConcretePriceID,
                                      @RequestParam Double concretePrice) {
        priceService.updateConcretePrice(tempConcretePriceID, concretePrice);
        return goToChangePricePage(model);
    }

    @PostMapping(path = "/changeAutoPrice")
    public String changeAutoPrice(Model model, @RequestParam Long tempAutoPriceID,
                                  @RequestParam Double autoPrice) {

        priceService.updateAutoPriceDTO(tempAutoPriceID, autoPrice);
        return goToChangePricePage(model);
    }

    @PostMapping(path = "/changeDeliveryCoefficient")
    public String changeDeliveryCoefficient(Model model, @RequestParam Long tempDelAfter50ID,
                                            @RequestParam Long tempDelBefore50ID,
                                            @RequestParam Integer delCoefficient) {

        priceService.updateAutoCapacityDTO(tempDelBefore50ID, tempDelAfter50ID, delCoefficient);
        return goToChangePricePage(model);
    }


    @GetMapping(path = "/newManager")
    public String goToNewManagerForm() {
        return CREATE_MANAGER_FORM;
    }

    @PostMapping(path = "/createNewManager")
    public String createNewManager(Model model, @Valid UserDTO userDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            model.addAttribute(ADMIN_ERROR, ADMIN_ERROR);
            return applicationExceptionController.sendErrorMessage(model, UPDATE_USER_FIELD_NULL, NEW_MANAGER_FORM);
        }
        userDTO.setRole(Roles.MANAGER);
        ManagerDTO managerDTO = managerService.createManager(userDTO);

        if (managerDTO == null) {
            model.addAttribute(ADMIN_ERROR, ADMIN_ERROR);
            return applicationExceptionController.sendErrorMessage(model, ERROR_USER_ALREADY_EXIST, NEW_MANAGER_FORM);
        }
        return goToAdminMainPage(model);
    }
}
