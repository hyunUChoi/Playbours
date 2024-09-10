package leave.meet.playbours.manage.sys.auth;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "maAuth")
public class MaAuth {
    // 일련번호
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String seq;

    private String authClCd;

    private String authCode;

    private String authRank;

    // 명칭
    private String authNm;

    private String authCont;

    // 순서
    private int authOrder;

    // 삭제여부
    private String delYn;

    // 사용여부
    private String useYn;

    private String checkList;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date frstRegrDt;

    private String frstRegrId;

    // 최종수정일
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date lstChgDt;

    private String lstChgId;

    @Builder
    public MaAuth(String authClCd, String authCode, String authRank, String authNm, String authCont, int authOrder, String delYn, String useYn, String checkList, Date frstRegrDt, String frstRegrId, Date lstChgDt, String lstChgId) {
        this.authClCd = authClCd;
        this.authCode = authCode;
        this.authRank = authRank;
        this.authNm = authNm;
        this.authOrder = authOrder;
        this.authCont = authCont;
        this.delYn = delYn;
        this.useYn = useYn;
        this.checkList = checkList;
        this.frstRegrDt = frstRegrDt;
        this.frstRegrId = frstRegrId;
        this.lstChgDt = lstChgDt;
        this.lstChgId = lstChgId;
    }

}
