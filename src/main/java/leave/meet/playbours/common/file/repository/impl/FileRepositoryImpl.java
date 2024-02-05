package leave.meet.playbours.common.file.repository.impl;

import leave.meet.playbours.common.file.dto.FileDto;
import leave.meet.playbours.common.file.repository.FileRepository;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.Date;
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
        fileDto.setSaveFileNm(dto.getSaveFileNm());
        fileDto.setOriginalFileNm(dto.getOriginalFileNm());
        fileDto.setFileStorePath(dto.getFileStorePath());
        fileDto.setFileType(dto.getFileType());
        fileDto.setFileSize(dto.getFileSize());
        // TODO 로그인 아이디로 변경
        fileDto.setFrstRegrId("admin");
        fileDto.setFrstRegrDt(new Date());
        fileDto.setDelYn(dto.getDelYn());
        fileDto.setTempYn(dto.getTempYn());
        mongoTemplate.insert(fileDto);
    }

    @Override
    public void delete(String saveFileNm) {
        Query query = new Query();
        Update update = new Update();

        query.addCriteria(Criteria.where("saveFileNm").is(saveFileNm));
        // TODO 로그인 아이디로 변경
        update.set("lstChgId", "admin");
        update.set("lstChgDt", new Date());
        update.set("delYn", "Y");
        mongoTemplate.upsert(query, update, FileDto.class);
    }

    @Override
    public int countByFileName(String fileName) {
        Query query = new Query();
        query.addCriteria(Criteria.where("fileName").is(fileName));
        query.addCriteria(Criteria.where("delYn").is("N"));
        return (int) mongoTemplate.count(query, FileDto.class);
    }

    @Override
    public List<FileDto> findFilesByFileName(String fileName) {
        Query query = new Query();
        query.addCriteria(Criteria.where("fileName").is(fileName));
        query.addCriteria(Criteria.where("delYn").is("N"));
        return mongoTemplate.find(query, FileDto.class);
    }

    @Override
    public void deleteDelY(Object fileName) {
        Query query = new Query();
        query.addCriteria(Criteria.where("fileName").is(fileName));
        query.addCriteria(Criteria.where("delYn").is("Y"));
        mongoTemplate.remove(query, FileDto.class);
    }

    @Override
    public void updateTempN(Object fileName) {
        Query query = new Query();
        Update update = new Update();
        query.addCriteria(Criteria.where("fileName").is(fileName));
        update.set("tempYn", "N");
        mongoTemplate.updateMulti(query, update, FileDto.class);
    }

    @Override
    public void deleteTempY(Object fileName) {
        Query query = new Query();
        query.addCriteria(Criteria.where("fileName").is(fileName));
        query.addCriteria(Criteria.where("tempYn").is("Y"));
        mongoTemplate.remove(query, FileDto.class);
    }

    @Override
    public void updateDelN(Object fileName) {
        Query query = new Query();
        Update update = new Update();
        query.addCriteria(Criteria.where("fileName").is(fileName));
        update.set("delYn", "N");
        mongoTemplate.updateMulti(query, update, FileDto.class);
    }

}
