package application.controller;

import application.dto.RecordDTO;
import application.service.interfaces.RecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import static application.utils.Constant.*;

@Controller
@RequiredArgsConstructor
public class RecordController {

    private final RecordService recordService;

    @PostMapping(path = "/createNewRecord")
    private String createNewRecord(Model model, @RequestParam Long currentUserId,
                                   @RequestParam Long currentProjectId) {
        RecordDTO recordDTO = recordService.createRecord(currentUserId, currentProjectId);
        model.addAttribute(CURRENT_RECORD, recordDTO);
        return RECORD;
    }

    @GetMapping(path = "/stopRecord")
    private String stopRecord(Model model, @RequestParam Long currentRecordId) {
        RecordDTO recordDTO = recordService.endRecord(currentRecordId);

        if (recordDTO != null) {
            model.addAttribute(CURRENT_PROJECT, recordDTO.getProject());
            model.addAttribute(CURRENT_USER, recordDTO.getUser());
        }
        return READ_PROJECT;
    }
}
