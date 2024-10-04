package leave.meet.playbours.manage.sys.auth.service;

import jakarta.validation.constraints.NotBlank;
import leave.meet.playbours.common.dto.CmmnDto;
import leave.meet.playbours.manage.sys.menu.service.MaMenuVO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.ibatis.type.Alias;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@SuppressWarnings("unused")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Alias("maAuthVO")
public class MaAuthVO extends CmmnDto implements Serializable {

    public interface insert{};

    @Serial
    private static final long serialVersionUID = 1L;

    // 일련번호
    private int seq;

    // 최초등록자
    private String regId;

    // 최초등록일
    private Date regDt;

    // 최종수정자
    private String modId;

    // 최종수정일
    private Date modDt;

    // 사용여부
    private String useYn;

    // 삭제여부
    private String delYn;

    // 삭제여부
    private String authId;

    // 권한 구분코드
    @NotBlank(groups = {MaAuthVO.insert.class}, message = "권한구분 코드는 필수값입니다.")
    private String authCd;

    // 권한 이름
    @NotBlank(groups = {MaAuthVO.insert.class}, message = "권한이름은 필수값입니다.")
    private String authNm;

    // 권한 설명
    private String authExpl;
    

}
