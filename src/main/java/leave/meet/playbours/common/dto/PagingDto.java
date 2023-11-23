package leave.meet.playbours.common.dto;

public class PagingDto {
    // 현재 페이지
    private int currentPageNo;

    // 페이지 사이즈
    private int pageSize;

    // 총 데이터 수
    private int totalRecord;

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

    public PagingDto() {
    }

    public PagingDto(int currentPageNo, int pageSize, int totalPage, int startNum, int endNum, boolean hasPrev, int prevIdx, boolean hasNext, int nextIdx) {
        this.currentPageNo = currentPageNo;
        this.pageSize = pageSize;
        this.totalPage = totalPage;
        this.startNum = startNum;
        this.endNum = endNum;
        this.hasPrev = hasPrev;
        this.prevIdx = prevIdx;
        this.hasNext = hasNext;
        this.nextIdx = nextIdx;
    }

    public int getCurrentPageNo() {
        return currentPageNo;
    }

    public void setCurrentPageNo(int currentPageNo) {
        this.currentPageNo = currentPageNo;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalRecord() {
        return totalRecord;
    }

    public void setTotalRecord(int totalRecord) {
        this.totalRecord = totalRecord;
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
}
