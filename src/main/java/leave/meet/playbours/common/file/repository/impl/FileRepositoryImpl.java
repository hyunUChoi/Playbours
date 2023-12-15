package leave.meet.playbours.common.file.repository.impl;

import leave.meet.playbours.common.file.dto.FileDto;
import leave.meet.playbours.common.file.repository.FileRepository;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class FileRepositoryImpl implements FileRepository {

    private final MongoTemplate mongoTemplate;

    public FileRepositoryImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public void insert(FileDto dto) {
        FileDto fileDto = new FileDto();
        fileDto.setFileName(dto.getFileName());
        fileDto.setOriginalFileNm(dto.getOriginalFileNm());
        fileDto.setFileType(dto.getFileType());
        fileDto.setFileSize(dto.getFileSize());
        mongoTemplate.insert(fileDto);
    }

    @Override
    public List<FileDto> findByFileName(String file) {
        Query query = new Query();
        query.addCriteria(Criteria.where("fileName").is(file));
        return mongoTemplate.find(query, FileDto.class);
    }
}
