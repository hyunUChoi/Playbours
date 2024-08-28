package leave.meet.playbours.manage.support.board.dto;

import leave.meet.playbours.common.dto.CmmnDto;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@SuppressWarnings("unused")
@TypeAlias("MaBoardDto")
@Document(collection = "CLT_BOARD")
public class MaBoardDto extends CmmnDto {
    // 일련번호
    @Id
    private String seq;

    // 게시판 구분
    private String boardDivn;

    // 공개여부
    private String useYn;

    // 공지시작일
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date notiStrDt;

    // 공지종료일
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date notiEndDt;

    // 질문코드
    private String qstType;

    // 질문유형
    private String qstNm;

    // 게시글 제목
    private String title;

    // 게시글 내용
    private String cont;

    // 답변
    private String reply;

    // 조회수
    private int viewCnt = 0;

    // 첨부파일
    private String atchFile;

    // 최초등록자
    private String frstRegrId;

    // 최초등록일
    private Date frstRegrDt;

    // 최종수정자
    private String lstChgId;

    // 최종수정일
    private Date lstChgDt;

    // 삭제여부
    private String delYn = "N";

    public String getSeq() {
        return seq;
    }

    public void setSeq(String seq) {
        this.seq = seq;
    }

    public String getBoardDivn() {
        return boardDivn;
    }

    public void setBoardDivn(String boardDivn) {
        this.boardDivn = boardDivn;
    }

    public String getUseYn() {
        return useYn;
    }

    public void setUseYn(String useYn) {
        this.useYn = useYn;
    }

    public Date getNotiStrDt() {
        return notiStrDt;
    }

    public void setNotiStrDt(Date notiStrDt) {
        this.notiStrDt = notiStrDt;
    }

    public Date getNotiEndDt() {
        return notiEndDt;
    }

    public void setNotiEndDt(Date notiEndDt) {
        this.notiEndDt = notiEndDt;
    }

    public String getQstType() {
        return qstType;
    }

    public void setQstType(String qstType) {
        this.qstType = qstType;
    }

    public String getQstNm() {
        return qstNm;
    }

    public void setQstNm(String qstNm) {
        this.qstNm = qstNm;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCont() {
        return cont;
    }

    public void setCont(String cont) {
        this.cont = cont;
    }

    public String getReply() {
        return reply;
    }

    public void setReply(String reply) {
        this.reply = reply;
    }

    public int getViewCnt() {
        return viewCnt;
    }

    public void setViewCnt(int viewCnt) {
        this.viewCnt = viewCnt;
    }

    public String getAtchFile() {
        return atchFile;
    }

    public void setAtchFile(String atchFile) {
        this.atchFile = atchFile;
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
