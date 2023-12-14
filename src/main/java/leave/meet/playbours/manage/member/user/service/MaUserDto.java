package leave.meet.playbours.manage.member.user.service;

import leave.meet.playbours.common.dto.CmmnDto;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@SuppressWarnings("unused")
@TypeAlias("MaUserDto")
@Document(collection = "CLT_USER")
public class MaUserDto extends CmmnDto {

    // 인덱스
    @Id
    private String seq;
    // 유저구분
    private String userClCd;

    // 유저 아이디
    private String userId;

    // 유저이름
    private String userNm;

    // 유저 이메일
    private String userEmail;

    private String userPhone;

    // 유저 성별
    private String userSex;

    private String userYmd;

    // 유저 나이
    private String userAge;

    // 활동지역
    private String areaSido;

    private String areaGungu;

    // 관심사
    private String interest;

    // 유저 비밀번호
    private String userPwd;

    // 비밀번호 틀린횟수
    private String pwdFailCnt;

    // 승인여부
    private String useYn;

    // 삭제여부
    private String delYn;

    private String userCmt;

    // 회원가입일
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

    public String getUserClCd() {
        return userClCd;
    }

    public void setUserClCd(String userClCd) {
        this.userClCd = userClCd;
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

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserSex() {
        return userSex;
    }

    public void setUserSex(String userSex) {
        this.userSex = userSex;
    }

    public String getUserAge() {
        return userAge;
    }

    public void setUserAge(String userAge) {
        this.userAge = userAge;
    }

    public String getInterest() {
        return interest;
    }

    public void setInterest(String interest) {
        this.interest = interest;
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

    public String getDelYn() {
        return delYn;
    }

    public void setDelYn(String delYn) {
        this.delYn = delYn;
    }

    public String getUserCmt() {
        return userCmt;
    }

    public void setUserCmt(String userCmt) {
        this.userCmt = userCmt;
    }

    public Date getFrstRegrDt() {
        return frstRegrDt;
    }

    public void setFrstRegrDt(Date frstRegrDt) {
        this.frstRegrDt = frstRegrDt;
    }

    public Date getLstChgDt() {
        return lstChgDt;
    }

    public void setLstChgDt(Date lstChgDt) {
        this.lstChgDt = lstChgDt;
    }

    public String getAreaSido() {
        return areaSido;
    }

    public void setAreaSido(String areaSido) {
        this.areaSido = areaSido;
    }

    public String getAreaGungu() {
        return areaGungu;
    }

    public void setAreaGungu(String areaGungu) {
        this.areaGungu = areaGungu;
    }

    public String getFrstRegrId() {
        return frstRegrId;
    }

    public void setFrstRegrId(String frstRegrId) {
        this.frstRegrId = frstRegrId;
    }

    public String getLstChgId() {
        return lstChgId;
    }

    public void setLstChgId(String lstChgId) {
        this.lstChgId = lstChgId;
    }

    public String getUserYmd() {
        return userYmd;
    }

    public void setUserYmd(String userYmd) {
        this.userYmd = userYmd;
    }
}
