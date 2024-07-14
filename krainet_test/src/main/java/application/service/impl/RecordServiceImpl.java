package application.service.impl;

import application.dto.RecordDTO;
import application.entity.Project;
import application.entity.Record;
import application.entity.User;
import application.mapper.RecordMapper;
import application.repository.ProjectRepository;
import application.repository.RecordRepository;
import application.repository.UserRepository;
import application.service.interfaces.RecordService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.Duration;

@Slf4j
@Transactional
@Service
@RequiredArgsConstructor
public class RecordServiceImpl implements RecordService {

    private final RecordRepository recordRepository;
    private final RecordMapper recordMapper = Mappers.getMapper(RecordMapper.class);
    private final UserRepository userRepository;
    private final ProjectRepository projectRepository;


    @Override
    public RecordDTO createRecord(Long userId, Long projectId) {
        User user = userRepository.getById(userId);
        Project project = projectRepository.getById(projectId);

        Record record = Record.builder()
                .startTime(new Timestamp(System.currentTimeMillis()))
                .user(user)
                .project(project)
                .build();

        return recordMapper.toDTO(recordRepository.save(record));
    }

    /**
     *  This method is used to complete the recording process.
     *  @param currentRecordId the ID of current record
     *  @return an object of type RecordDTO
     *  If the duration of the record is less than one minute,
     *  the record is deleted from the database, and the method returns an object of type RecordDTO,
     *  where all field values ​​are null, except for the field user and the field project
     */
    @Override
    public RecordDTO endRecord(Long currentRecordId) {
        Record record = recordRepository.getById(currentRecordId);
        record.setEndTime(new Timestamp(System.currentTimeMillis()));
        record.setDuration(Duration.between(record.getStartTime().toLocalDateTime(), record.getEndTime().toLocalDateTime()));

        if (record.getDuration().toMinutesPart() < 1) {
            recordRepository.deleteById(currentRecordId);
            record.setStartTime(null);
            record.setEndTime(null);
            record.setId(null);
            return recordMapper.toDTO(record);
        }
        return recordMapper.toDTO(recordRepository.save(record));
    }

}
