package leave.meet.playbours.common.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("unused")
@Getter
@Setter
@NoArgsConstructor
public class CmmnDto implements Serializable {
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

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date searchStrDt;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date searchEndDt;

    private String searchKeyword;

    private String searchOption;

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
}
