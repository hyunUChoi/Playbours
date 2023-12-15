package leave.meet.playbours.manage.sys.code.dto;

import leave.meet.playbours.common.dto.CmmnDto;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

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

    //삭제여부
    private String delYn;

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
}
