package leave.meet.playbours.manage.sys.menu.service;

import leave.meet.playbours.common.dto.CmmnDto;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@SuppressWarnings("unused")
@TypeAlias("MaMenuDto")
@Document(collection = "CLT_MENU")
public class MaMenuDto extends CmmnDto {
    // 일련번호
    @Id
    private String seq;

    // 메뉴 구분
    private String menuClCd;

    // 메뉴코드
    private String menuCd;

    // 상위 메뉴코드
    private String upperCd;

    // 메뉴명
    private String menuNm;

    // 순서
    private String menuOrd;

    // url
    private String menuUrl;

    // 메뉴설명
    private String menuCmt;

    // 사용여부
    private String useYn;

    // 삭제여부
    private String delYn = "N";

    // 최초등록자
    private String frstRegrId;

    // 최초등록일
    private Date frstRegrDt;

    // 최종수정자
    private String lstChgId;

    // 최종수정일
    private Date lstChgDt;

    private List<MaMenuDto> menuList;

    private MaMenuDto maMenuDto;

    public String getSeq() {
        return seq;
    }

    public void setSeq(String seq) {
        this.seq = seq;
    }

    public String getMenuClCd() {
        return menuClCd;
    }

    public void setMenuClCd(String menuClCd) {
        this.menuClCd = menuClCd;
    }

    public String getMenuCd() {
        return menuCd;
    }

    public void setMenuCd(String menuCd) {
        this.menuCd = menuCd;
    }

    public String getUpperCd() {
        return upperCd;
    }

    public void setUpperCd(String upperCd) {
        this.upperCd = upperCd;
    }

    public String getMenuNm() {
        return menuNm;
    }

    public void setMenuNm(String menuNm) {
        this.menuNm = menuNm;
    }

    public String getMenuOrd() {
        return menuOrd;
    }

    public void setMenuOrd(String menuOrd) {
        this.menuOrd = menuOrd;
    }

    public String getMenuUrl() {
        return menuUrl;
    }

    public void setMenuUrl(String menuUrl) {
        this.menuUrl = menuUrl;
    }

    public String getMenuCmt() {
        return menuCmt;
    }

    public void setMenuCmt(String menuCmt) {
        this.menuCmt = menuCmt;
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

    public List<MaMenuDto> getMenuList() {
        return menuList;
    }

    public void setMenuList(List<MaMenuDto> menuList) {
        this.menuList = menuList;
    }

    public MaMenuDto getMaMenuDto() {
        return maMenuDto;
    }

    public void setMaMenuDto(MaMenuDto maMenuDto) {
        this.maMenuDto = maMenuDto;
    }
}
