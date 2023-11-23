package leave.meet.playbours.manage.sys.menu.repository;

import leave.meet.playbours.manage.sys.menu.service.MaMenuDto;
import org.springframework.data.domain.Page;

import java.util.List;

public interface MaMenuRepository {
    Page<MaMenuDto> findByPagingAndFiltering(int page, int size, MaMenuDto dto, String procType);

    MaMenuDto findOne(MaMenuDto dto);

    MaMenuDto findOneByCode(MaMenuDto dto);

    int countByCode(String menuCd);

    void insert(MaMenuDto dto);

    void update(MaMenuDto dto);

    void delete(MaMenuDto dto);

    List<MaMenuDto> findMenuList(MaMenuDto dto);
}
