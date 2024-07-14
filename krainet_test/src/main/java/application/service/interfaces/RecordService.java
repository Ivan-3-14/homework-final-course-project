package application.service.interfaces;

import application.dto.RecordDTO;

public interface RecordService {

    RecordDTO createRecord(Long userId, Long projectId);

    RecordDTO endRecord(Long currentRecordId);
}
