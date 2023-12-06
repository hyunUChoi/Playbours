package leave.meet.playbours.common.service;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.client.gridfs.model.GridFSFile;
import leave.meet.playbours.common.dto.FileDto;
import org.apache.commons.io.IOUtils;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsOperations;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class FileService {

    private final GridFsTemplate gridFsTemplate;

    private final GridFsOperations gridFsOperations;

    public FileService(GridFsTemplate gridFsTemplate, GridFsOperations gridFsOperations) {
        this.gridFsTemplate = gridFsTemplate;
        this.gridFsOperations = gridFsOperations;
    }

    public String addFile(MultipartFile file) throws IOException {

        DBObject metadata = new BasicDBObject();
        metadata.put("fileSize", file.getSize());
        Object fileID = gridFsTemplate.store(file.getInputStream(), file.getOriginalFilename(), file.getContentType(), metadata);

        return fileID.toString();
    }

    public FileDto downloadFile(String id) throws IOException {

        GridFSFile gridFSFile = gridFsTemplate.findOne(new Query(Criteria.where("_id").is(id)));
        FileDto fileDto = new FileDto();

        if(gridFSFile != null && gridFSFile.getMetadata() != null) {
            fileDto.setFileName(gridFSFile.getFilename());
            fileDto.setFileType(gridFSFile.getMetadata().get("_contentType").toString());
            fileDto.setFileSize(gridFSFile.getMetadata().get("fileSize").toString());
            fileDto.setFile(IOUtils.toByteArray(gridFsOperations.getResource(gridFSFile).getInputStream()));
        }

        return fileDto;
    }

    public void deleteFile(String id) throws IOException {
        gridFsTemplate.delete(new Query(Criteria.where("_id").is(id)));
    }
}
