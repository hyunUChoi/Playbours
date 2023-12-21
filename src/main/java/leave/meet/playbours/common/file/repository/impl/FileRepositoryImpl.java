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
        fileDto.setFileStorePath(dto.getFileStorePath());
        fileDto.setFileType(dto.getFileType());
        fileDto.setFileSize(dto.getFileSize());
        mongoTemplate.insert(fileDto);
    }

    @Override
    public void delete(String fileName) {
        Query query = new Query();
        query.addCriteria(Criteria.where("fileName").is(fileName));
        mongoTemplate.remove(query, FileDto.class);
    }

    @Override
    public List<FileDto> findFilesByFileName(String fileName) {
        Query query = new Query();
        query.addCriteria(Criteria.where("fileName").is(fileName));
        return mongoTemplate.find(query, FileDto.class);
    }

    @Override
    public FileDto findFile(String fileName) {
        Query query = new Query();
        query.addCriteria(Criteria.where("fileName").is(fileName));
        return mongoTemplate.findOne(query, FileDto.class);
    }
}
