package leave.meet.playbours.manage.sys.menu.entity;

import jakarta.persistence.*;
import leave.meet.playbours.common.dto.CmmnDto;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SuppressWarnings("unused")
@Getter
@NoArgsConstructor
@Entity
@Table(name = "T_MENU")
public class MaMenuEntity extends CmmnDto implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    // 일련번호
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SEQ")
    private Long seq;

    // 최초등록자
    @Column(name = "REG_ID")
    private String regId;

    // 최초등록일
    @Column(name = "REG_DT")
    private String regDt;

    // 최종수정자
    @Column(name = "MOD_ID")
    private String modId;

    // 최종수정일
    @Column(name = "MOD_DT")
    private String modDt;

    // 메뉴 구분
    @Column(name = "MENU_CL_CD")
    private String menuClCd;

    // 메뉴코드
    @Column(name = "MENU_CD")
    private String menuCd;

    // 상위 메뉴코드
    @Column(name = "UPPER_CD")
    private String upperCd;

    // 메뉴명
    @Column(name = "MENU_NM")
    private String menuNm;

    // 순서
    @Column(name = "MENU_ORD")
    private String menuOrd;

    // url
    @Column(name = "MENU_URL")
    private String menuUrl;

    // 메뉴설명
    @Column(name = "MENU_CMT")
    private String menuCmt;

    // 사용여부
    @Column(name = "USE_YN")
    private String useYn;

    // 삭제여부
    @Column(name = "DEL_YN")
    private String delYn;

    @OneToMany
    private List<MaMenuEntity> menuList;

    @Builder
    public MaMenuEntity(Long seq, String regId, String regDt, String modId, String modDt, String menuClCd, String menuCd, String upperCd, String menuNm, String menuOrd, String menuUrl, String menuCmt, String useYn, String delYn) {
        this.seq = seq;
        this.regId = regId;
        this.regDt = regDt;
        this.modId = modId;
        this.modDt = modDt;
        this.menuClCd = menuClCd;
        this.menuCd = menuCd;
        this.upperCd = upperCd;
        this.menuNm = menuNm;
        this.menuOrd = menuOrd;
        this.menuUrl = menuUrl;
        this.menuCmt = menuCmt;
        this.useYn = useYn;
        this.delYn = delYn;
    }

}
