package leave.meet.playbours.manage.sys.auth.dto;

import leave.meet.playbours.common.dto.CmmnDto;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@SuppressWarnings("unused")
@TypeAlias("MaAuthDto")
@Document(collection = "CLT_AUTH")
public class MaAuthDto extends CmmnDto {
    // 일련번호
    @Id
    private String seq;

    private String authClCd;

    private String authCode;

    private String authRank;

    // 명칭
    private String authNm;

    private String authCont;

    // 순서
    private int authOrder;

    // 삭제여부
    private String delYn;

    // 사용여부
    private String useYn;

    private String checkList;

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

    public String getAuthRank() {
        return authRank;
    }

    public void setAuthRank(String authRank) {
        this.authRank = authRank;
    }

    public String getAuthNm() {
        return authNm;
    }

    public void setAuthNm(String authNm) {
        this.authNm = authNm;
    }

    public String getAuthCont() {
        return authCont;
    }

    public void setAuthCont(String authCont) {
        this.authCont = authCont;
    }

    public int getAuthOrder() {
        return authOrder;
    }

    public void setAuthOrder(int authOrder) {
        this.authOrder = authOrder;
    }

    public String getDelYn() {
        return delYn;
    }

    public void setDelYn(String delYn) {
        this.delYn = delYn;
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

    public String getAuthClCd() {
        return authClCd;
    }

    public void setAuthClCd(String authClCd) {
        this.authClCd = authClCd;
    }

    public String getAuthCode() {
        return authCode;
    }

    public void setAuthCode(String authCode) {
        this.authCode = authCode;
    }

    public String getCheckList() {
        return checkList;
    }

    public void setCheckList(String checkList) {
        this.checkList = checkList;
    }
}
