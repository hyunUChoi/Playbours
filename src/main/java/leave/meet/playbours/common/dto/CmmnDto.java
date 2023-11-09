package leave.meet.playbours.common.dto;

import java.io.Serializable;

public class CmmnDto implements Serializable {
    // 현재 페이지
    private int pageNo;

    // 총 페이지 수
    private int totalPage;

    // 시작
    private int startNum;

    // 마지막
    private int endNum;

    // 이전 페이지 여부
    private boolean hasPrev;

    // 이전 페이지 인덱스
    private int prevIdx;

    // 다음 페이지 여부
    private boolean hasNext;

    // 다음 페이지 인덱스
    private int nextIdx;

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

    private String searchKeyword;

    private String searchOption;

    public CmmnDto() {
    }

    public CmmnDto(int totalPage, int startNum, int endNum, boolean hasPrev, int prevIdx, boolean hasNext, int nextIdx) {
        this.totalPage = totalPage;
        this.startNum = startNum;
        this.endNum = endNum;
        this.hasPrev = hasPrev;
        this.prevIdx = prevIdx;
        this.hasNext = hasNext;
        this.nextIdx = nextIdx;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getStartNum() {
        return startNum;
    }

    public void setStartNum(int startNum) {
        this.startNum = startNum;
    }

    public int getEndNum() {
        return endNum;
    }

    public void setEndNum(int endNum) {
        this.endNum = endNum;
    }

    public boolean isHasPrev() {
        return hasPrev;
    }

    public void setHasPrev(boolean hasPrev) {
        this.hasPrev = hasPrev;
    }

    public int getPrevIdx() {
        return prevIdx;
    }

    public void setPrevIdx(int prevIdx) {
        this.prevIdx = prevIdx;
    }

    public boolean isHasNext() {
        return hasNext;
    }

    public void setHasNext(boolean hasNext) {
        this.hasNext = hasNext;
    }

    public int getNextIdx() {
        return nextIdx;
    }

    public void setNextIdx(int nextIdx) {
        this.nextIdx = nextIdx;
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
