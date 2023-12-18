package leave.meet.playbours.manage.sys.code.repository;

import leave.meet.playbours.manage.sys.code.dto.MaCodeDto;
import org.springframework.data.domain.Page;

import java.util.List;

public interface MaCodeRepository {
    List<MaCodeDto> findAll(MaCodeDto dto);


    MaCodeDto findOne(MaCodeDto dto);

    MaCodeDto findOneByCode(MaCodeDto dto);


    void insert(MaCodeDto dto);

    void update(MaCodeDto dto);

    void delete(MaCodeDto dto);

    List<MaCodeDto> findCodeList(String code);

    MaCodeDto findCodeDetail(String code);
}
