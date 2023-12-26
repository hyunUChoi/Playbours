package leave.meet.playbours.common.file.dto;

import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@SuppressWarnings("unused")
@TypeAlias("FileDto")
@Document(collection = "CLT_FILE")
public class FileDto {
    // 업로드한 파일을 구분하기 위한 파일명
    private String fileName;

    // 동시 업로드된 파일을 구분하기 위한 파일명
    private String saveFileNm;

    private String originalFileNm;

    private String fileStorePath;

    private String fileType;

    private long fileSize;

    // 최초등록자
    private String frstRegrId;

    // 최초등록일
    private Date frstRegrDt;

    // 최종수정자
    private String lstChgId;

    // 최종수정일
    private Date lstChgDt;

    private String delYn = "N";

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getSaveFileNm() {
        return saveFileNm;
    }

    public void setSaveFileNm(String saveFileNm) {
        this.saveFileNm = saveFileNm;
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

    public String getFrstRegrId() {
        return frstRegrId;
    }

    public void setFrstRegrId(String frstRegrId) {
        this.frstRegrId = frstRegrId;
    }

    public Date getFrstRegrDt() {
        return frstRegrDt;
    }

    public void setFrstRegrDt(Date frstRegrDt) {
        this.frstRegrDt = frstRegrDt;
    }

    public String getLstChgId() {
        return lstChgId;
    }

    public void setLstChgId(String lstChgId) {
        this.lstChgId = lstChgId;
    }

    public Date getLstChgDt() {
        return lstChgDt;
    }

    public void setLstChgDt(Date lstChgDt) {
        this.lstChgDt = lstChgDt;
    }

    public String getDelYn() {
        return delYn;
    }

    public void setDelYn(String delYn) {
        this.delYn = delYn;
    }
}
