package leave.meet.playbours.common.file.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Service
public class FileService {

    @Value("${file.upload.path}")
    private String PATH;

    public String saveFile(MultipartFile file) throws IOException {
        String fileNm = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss")) + UUID.randomUUID();
        String filepath = PATH + getFilePath() + fileNm;

        if(!"".equals(filepath)) {
            File saveFolder = new File(filepath);
            if(!saveFolder.exists() || saveFolder.isFile()) {
                saveFolder.mkdirs();
            }
            file.transferTo(saveFolder);
        }
        return fileNm;
    }

    public String getFilePath() {
        LocalDate now = LocalDate.now();
        return now.getYear() + File.separator + now.getMonthValue()  + File.separator + now.getDayOfMonth() + File.separator;
    }
}
