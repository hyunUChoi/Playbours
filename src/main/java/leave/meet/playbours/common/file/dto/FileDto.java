package leave.meet.playbours.common.file.dto;

import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

@SuppressWarnings("unused")
@TypeAlias("FileDto")
@Document(collection = "CLT_FILE")
public class FileDto {
    private String fileName;

    private String originalFileNm;

    private String fileStorePath;

    private String fileType;

    private long fileSize;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getOriginalFileNm() {
        return originalFileNm;
    }

    public void setOriginalFileNm(String originalFileNm) {
        this.originalFileNm = originalFileNm;
    }

    public String getFileStorePath() {
        return fileStorePath;
    }

    public void setFileStorePath(String fileStorePath) {
        this.fileStorePath = fileStorePath;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public long getFileSize() {
        return fileSize;
    }

    public void setFileSize(long fileSize) {
        this.fileSize = fileSize;
    }
}
