package leave.meet.playbours.manage.member.user.service;

import leave.meet.playbours.common.dto.CmmnDto;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "TB_USER")
public class MaUserDto extends CmmnDto {

    // 인덱스
    private String seq;
    // 유저구분
    private String userClCd;

    // 유저 고유 코드
    private String userCd;

    // 유저 아이디
    private String userId;

    // 유저이름
    private String userNm;

    // 유저 이메일
    private String userEmail;

    // 유저 성별
    private String userSex;

    // 유저 나이
    private String userAge;

    // 활동지역
    private String activArea;

    // 관심사
    private String interest;

    // 유저 비밀번호
    private String userPwd;

    // 비밀번호 틀린횟수
    private String pwdFailCnt;

    // 사용여부
    private String useYn;

    // 회원가입일
    private String frstRegrDt;

    // 승인상태
    private String applyState;

    // 최종수정일
    private String lstChgDt;

    public String getUserCd() {
        return userCd;
    }

    public void setUserCd(String userCd) {
        this.userCd = userCd;
    }

    public String getSeq() {
        return seq;
    }

    public void setSeq(String seq) {
        this.seq = seq;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserNm() {
        return userNm;
    }

    public void setUserNm(String userNm) {
        this.userNm = userNm;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPwd() {
        return userPwd;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }

    public String getPwdFailCnt() {
        return pwdFailCnt;
    }

    public void setPwdFailCnt(String pwdFailCnt) {
        this.pwdFailCnt = pwdFailCnt;
    }

    public String getUseYn() {
        return useYn;
    }

    public void setUseYn(String useYn) {
        this.useYn = useYn;
    }

    public String getFrstRegrDt() {
        return frstRegrDt;
    }

    public void setFrstRegrDt(String frstRegrDt) {
        this.frstRegrDt = frstRegrDt;
    }

    public String getApplyState() {
        return applyState;
    }

    public void setApplyState(String applyState) {
        this.applyState = applyState;
    }

    public String getLstChgDt() {
        return lstChgDt;
    }

    public void setLstChgDt(String lstChgDt) {
        this.lstChgDt = lstChgDt;
    }

    public String getUserClCd() {
        return userClCd;
    }

    public void setUserClCd(String userClCd) {
        this.userClCd = userClCd;
    }
}
