package leave.meet.playbours.manage.sys.menu.service.dto;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "TB_MENU")
public class MaMenuDto {
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

    // 최초등록자
    private String frstRegrId;

    // 최초등록일
    private String frstRegrDt;

    // 최종수정자
    private String lstChgId;

    // 최종수정일
    private String lstChgDt;

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

    public String getFrstRegrId() {
        return frstRegrId;
    }

    public void setFrstRegrId(String frstRegrId) {
        this.frstRegrId = frstRegrId;
    }

    public String getFrstRegrDt() {
        return frstRegrDt;
    }

    public void setFrstRegrDt(String frstRegrDt) {
        this.frstRegrDt = frstRegrDt;
    }

    public String getLstChgId() {
        return lstChgId;
    }

    public void setLstChgId(String lstChgId) {
        this.lstChgId = lstChgId;
    }

    public String getLstChgDt() {
        return lstChgDt;
    }

    public void setLstChgDt(String lstChgDt) {
        this.lstChgDt = lstChgDt;
    }
}
