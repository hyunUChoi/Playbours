package leave.meet.playbours.common.dto;


import org.springframework.data.annotation.Transient;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("unused")
public class CmmnDto implements Serializable {
    @Transient
    // 현재 페이지
    private int pageNo = 1;

    // 구분값
    private String procType;

    // 검색용 & 기타 파라미터 넘기기용
    private String search1;

    private String search2;

    private String search3;

    private String search4;

    private String search5;

    private String search6;

    private String search7;

    private String search8;

    private String search9;

    private String search10;

    private Date searchStrDt;

    private Date searchEndDt;

    private String searchKeyword;

    private String searchOption;

    public CmmnDto() {
    }

    public void setSearch(final CmmnDto dto) {
        this.pageNo = dto.getPageNo();
        this.search1 = dto.getSearch1();
        this.search2 = dto.getSearch2();
        this.search3 = dto.getSearch3();
        this.search4 = dto.getSearch4();
        this.search5 = dto.getSearch5();
        this.search6 = dto.getSearch6();
        this.search7 = dto.getSearch7();
        this.search8 = dto.getSearch8();
        this.search9 = dto.getSearch9();
        this.search10 = dto.getSearch10();
        this.searchStrDt = dto.getSearchStrDt();
        this.searchEndDt = dto.getSearchEndDt();
        this.searchOption = dto.getSearchOption();
        this.searchKeyword = dto.getSearchKeyword();
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public String getProcType() {
        return procType;
    }

    public void setProcType(String procType) {
        this.procType = procType;
    }

    public String getSearch1() {
        return search1;
    }

    public void setSearch1(String search1) {
        this.search1 = search1;
    }

    public String getSearch2() {
        return search2;
    }

    public void setSearch2(String search2) {
        this.search2 = search2;
    }

    public String getSearch3() {
        return search3;
    }

    public void setSearch3(String search3) {
        this.search3 = search3;
    }

    public String getSearch4() {
        return search4;
    }

    public void setSearch4(String search4) {
        this.search4 = search4;
    }

    public String getSearch5() {
        return search5;
    }

    public void setSearch5(String search5) {
        this.search5 = search5;
    }

    public String getSearch6() {
        return search6;
    }

    public void setSearch6(String search6) {
        this.search6 = search6;
    }

    public String getSearch7() {
        return search7;
    }

    public void setSearch7(String search7) {
        this.search7 = search7;
    }

    public String getSearch8() {
        return search8;
    }

    public void setSearch8(String search8) {
        this.search8 = search8;
    }

    public String getSearch9() {
        return search9;
    }

    public void setSearch9(String search9) {
        this.search9 = search9;
    }

    public String getSearch10() {
        return search10;
    }

    public void setSearch10(String search10) {
        this.search10 = search10;
    }

    public Date getSearchStrDt() {
        return searchStrDt;
    }

    public void setSearchStrDt(Date searchStrDt) {
        this.searchStrDt = searchStrDt;
    }

    public Date getSearchEndDt() {
        return searchEndDt;
    }

    public void setSearchEndDt(Date searchEndDt) {
        this.searchEndDt = searchEndDt;
    }

    public String getSearchKeyword() {
        return searchKeyword;
    }

    public void setSearchKeyword(String searchKeyword) {
        this.searchKeyword = searchKeyword;
    }

    public String getSearchOption() {
        return searchOption;
    }

    public void setSearchOption(String searchOption) {
        this.searchOption = searchOption;
    }
}
