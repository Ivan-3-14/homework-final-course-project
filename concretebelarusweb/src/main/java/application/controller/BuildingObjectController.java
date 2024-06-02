package application.controller;

import application.DTO.filtersDTO.BuildingObjectPaginationFilter;
import application.services.interfaces.BuildingObjectService;
import liquibase.repackaged.org.apache.commons.lang3.StringUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import static application.utils.Constant.*;

@Controller
@RequiredArgsConstructor
public class BuildingObjectController {

    private final BuildingObjectService buildingObjectService;

    @GetMapping(path = "/myObjects")
    public String goToMyOrders(Model model, @RequestParam Long currentUserId,
                               @RequestParam(defaultValue = "0") int currentPage) {
        BuildingObjectPaginationFilter objectPaginationFilter = buildingObjectService.getObjectPaginationFilter(currentUserId,
                currentPage, ROW_IN_PAGE_OBJECTS);
        model.addAttribute(LIST_OF_OBJECTS, objectPaginationFilter.getObjectDTOList());
        model.addAttribute(CURRENT_PAGE, objectPaginationFilter.getCurrentPage());
        model.addAttribute(MAX_PAGE, objectPaginationFilter.getCountOfTotalPage());
        return ALL_OBJECTS;
    }

    @GetMapping(path = "/searchObject")
    public String searchObject(Model model, @RequestParam Long currentUserId,
                               @RequestParam String search,
                               @RequestParam(defaultValue = "0") int currentPage) {
        if (search.equals(NULL) || StringUtils.isBlank(search)) {
            return goToMyOrders(model, currentUserId, currentPage);
        }
        BuildingObjectPaginationFilter filter = buildingObjectService.readObjects(search, currentUserId, currentPage);
        model.addAttribute(SEARCH, search);
        model.addAttribute(LIST_OF_OBJECTS, filter.getObjectDTOList());
        model.addAttribute(CURRENT_PAGE, filter.getCurrentPage());
        model.addAttribute(MAX_PAGE, filter.getCountOfTotalPage());
        return ALL_OBJECTS;
    }
}
