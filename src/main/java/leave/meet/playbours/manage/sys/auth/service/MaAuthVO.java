package leave.meet.playbours.manage.sys.auth.service;

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
@Alias("maAuthVO")
public class MaAuthVO extends CmmnDto implements Serializable {

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

    // 사용여부
    private String useYn;

    // 삭제여부
    private String delYn;

}
