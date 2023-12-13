package leave.meet.playbours.manage.sys.code.repository;

import leave.meet.playbours.manage.sys.code.service.MaCodeDto;
import org.springframework.data.domain.Page;

import java.util.List;

public interface MaCodeRepository {
    Page<MaCodeDto> findByPagingAndFiltering(int page, int size, MaCodeDto dto, String procType);


    MaCodeDto findOne(MaCodeDto dto);

    MaCodeDto findOneByCode(MaCodeDto dto);

    int countByCode(String menuCd);

    void insert(MaCodeDto dto);

    void update(MaCodeDto dto);

    void delete(MaCodeDto dto);

    List<MaCodeDto> findCodeList(String code);
}
