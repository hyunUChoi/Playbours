package leave.meet.playbours.manage.sys.code.service;

import leave.meet.playbours.common.dto.CmmnDto;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@SuppressWarnings("unused")
@TypeAlias("MaCodeDto")
@Document(collection = "CLT_ADDR")
public class MaCodeDto extends CmmnDto {
    // 일련번호
    @Id
    private String seq;

    // 그룹
    private String groupSeq;

    //부모의 id
    private String parentSeq;

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

    public String getGroupSeq() {
        return groupSeq;
    }

    public void setGroupSeq(String groupSeq) {
        this.groupSeq = groupSeq;
    }

    public String getParentSeq() {
        return parentSeq;
    }

    public void setParentSeq(String parentSeq) {
        this.parentSeq = parentSeq;
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
