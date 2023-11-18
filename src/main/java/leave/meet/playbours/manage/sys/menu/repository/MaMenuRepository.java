package leave.meet.playbours.manage.sys.menu.repository;

import leave.meet.playbours.manage.sys.menu.service.MaMenuDto;
import org.springframework.data.domain.Page;

public interface MaMenuRepository {
    Page<MaMenuDto> findByPagingAndFiltering(int page, int size, MaMenuDto maMenuDto, String procType);

    MaMenuDto findOne(MaMenuDto maMenuDto);

    MaMenuDto findOneByCode(MaMenuDto maMenuDto);

    int countByCode(String menuCd);

    void insert(MaMenuDto dto);

    void update(MaMenuDto dto);

    void delete(MaMenuDto dto);
}
