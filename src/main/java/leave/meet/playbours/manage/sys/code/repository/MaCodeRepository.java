package leave.meet.playbours.manage.sys.code.repository;

import leave.meet.playbours.manage.sys.code.dto.MaCodeDto;
import org.springframework.data.domain.Page;

import java.util.List;

public interface MaCodeRepository {
    List<MaCodeDto> findAll(MaCodeDto dto);


    MaCodeDto findOne(MaCodeDto dto);

    int countByCode(String code);


    void insert(MaCodeDto dto);

    void update(MaCodeDto dto);

    void delete(MaCodeDto dto);

    List<MaCodeDto> findCodeList1(String code);

    List<MaCodeDto> findCodeList2(String code);

    MaCodeDto findCodeDetail(String code);
}
