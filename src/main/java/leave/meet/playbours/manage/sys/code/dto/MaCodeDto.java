package leave.meet.playbours.manage.sys.code.dto;

import leave.meet.playbours.common.dto.CmmnDto;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@SuppressWarnings("unused")
@TypeAlias("MaCodeDto")
@Document(collection = "CLT_CODE")
public class MaCodeDto extends CmmnDto {
    // 일련번호
    @Id
    private String seq;

    // 그룹
    private String groupCode;

    //부모의 id
    private String parentCode;

    // code
    private String code;

    // 명칭
    private String name;

    // 순서
    private int order;

    // 삭제여부
    private String delYn;

    // 사용여부
    private String useYn;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date frstRegrDt;

    private String frstRegrId;

    // 최종수정일
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date lstChgDt;

    private String lstChgId;

    public String getSeq() {
        return seq;
    }

    public void setSeq(String seq) {
        this.seq = seq;
    }

    public String getGroupCode() {
        return groupCode;
    }

    public void setGroupCode(String groupCode) {
        this.groupCode = groupCode;
    }

    public String getParentCode() {
        return parentCode;
    }

    public void setParentCode(String parentCode) {
        this.parentCode = parentCode;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDelYn() {
        return delYn;
    }

    public void setDelYn(String delYn) {
        this.delYn = delYn;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public String getUseYn() {
        return useYn;
    }

    public void setUseYn(String useYn) {
        this.useYn = useYn;
    }

    public Date getFrstRegrDt() {
        return frstRegrDt;
    }

    public void setFrstRegrDt(Date frstRegrDt) {
        this.frstRegrDt = frstRegrDt;
    }

    public String getFrstRegrId() {
        return frstRegrId;
    }

    public void setFrstRegrId(String frstRegrId) {
        this.frstRegrId = frstRegrId;
    }

    public Date getLstChgDt() {
        return lstChgDt;
    }

    public void setLstChgDt(Date lstChgDt) {
        this.lstChgDt = lstChgDt;
    }

    public String getLstChgId() {
        return lstChgId;
    }

    public void setLstChgId(String lstChgId) {
        this.lstChgId = lstChgId;
    }
}
