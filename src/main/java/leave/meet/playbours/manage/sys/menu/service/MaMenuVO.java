package leave.meet.playbours.manage.sys.menu.service;

import jakarta.validation.constraints.NotBlank;
import leave.meet.playbours.common.dto.CmmnDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.ibatis.type.Alias;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@SuppressWarnings("unused")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Alias("maMenuVO")
public class MaMenuVO extends CmmnDto implements Serializable {

    public interface insert{};

    @Serial
    private static final long serialVersionUID = 1L;

    // 일련번호
    private int seq;

    // 최초등록자
    private String regId;

    // 최초등록일
    private String regDt;

    // 최종수정자
    private String modId;

    // 최종수정일
    private String modDt;

    // 메뉴 구분
    @NotBlank(groups = {insert.class}, message = "메뉴구분은 필수값입니다.")
    private String menuClCd;

    // 메뉴코드
    @NotBlank(groups = {insert.class}, message = "메뉴코드는 필수값입니다.")
    private String menuCd;

    // 상위 메뉴코드
    private String upperCd;

    // 메뉴명
    @NotBlank(groups = {insert.class}, message = "메뉴명은 필수값입니다.")
    private String menuNm;

    // 순서
    @NotBlank(groups = {insert.class}, message = "메뉴순서는 필수값입니다.")
    private String menuOrd;

    // url
    @NotBlank(groups = {insert.class}, message = "메뉴 URL은 필수값입니다.")
    private String menuUrl;

    // 메뉴설명
    private String menuCmt;

    // 사용여부
    @NotBlank(groups = {insert.class}, message = "사용여부는 필수값입니다.")
    private String useYn;

    // 삭제여부
    private String delYn;

    private List<MaMenuVO> menuList;

}
