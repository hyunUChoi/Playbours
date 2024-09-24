package leave.meet.playbours.common.paging.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
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

    public PagingDto(int currentPageNo, int pageSize, int totalRecord, int totalPage, int startNum, int endNum, boolean hasPrev, int prevIdx, boolean hasNext, int nextIdx) {
        this.currentPageNo = currentPageNo;
        this.pageSize = pageSize;
        this.totalRecord = totalRecord;
        this.totalPage = totalPage;
        this.startNum = startNum;
        this.endNum = endNum;
        this.hasPrev = hasPrev;
        this.prevIdx = prevIdx;
        this.hasNext = hasNext;
        this.nextIdx = nextIdx;
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
}
