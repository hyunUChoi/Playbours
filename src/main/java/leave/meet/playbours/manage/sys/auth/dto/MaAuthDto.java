package leave.meet.playbours.manage.sys.auth.dto;

/*@Getter
@NoArgsConstructor
public class MaAuthDto{

    private String seq;

    private String authClCd;

    private String authCode;

    private String authRank;

    private String authNm;

    private String authCont;

    private int authOrder;

    private String delYn;

    private String useYn;

    private String checkList;

    private Date frstRegrDt;

    private String frstRegrId;

    private Date lstChgDt;

    private String lstChgId;

    @Builder
    public MaAuthDto(String authClCd,String authCode,String authRank, String authNm,String authCont,int authOrder, String delYn, String useYn, String checkList, Date frstRegrDt,String frstRegrId, Date lstChgDt, String lstChgId) {
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

    public MaAuth toEntity() {
        return MaAuth.builder()
                .authClCd(authClCd)
                .authCode(authCode)
                .authRank(authRank)
                .authNm(authNm)
                .authOrder(authOrder)
                .authCont(authCont)
                .delYn(delYn)
                .useYn(useYn)
                .checkList(checkList)
                .frstRegrDt(frstRegrDt)
                .frstRegrId(frstRegrId)
                .lstChgDt(lstChgDt)
                .lstChgId(lstChgId)
                .build();
    }

    public void update(String seq, String authCont) {
        this.seq = seq;
        this.authCont = authCont;
    }
}*/
