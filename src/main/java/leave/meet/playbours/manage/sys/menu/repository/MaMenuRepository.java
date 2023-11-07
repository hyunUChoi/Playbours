package leave.meet.playbours.manage.sys.menu.repository;

import leave.meet.playbours.manage.sys.menu.service.MaMenuDto;
import org.springframework.data.domain.Page;

import java.util.HashMap;
import java.util.List;

public interface MaMenuRepository {
    Page<MaMenuDto> findByPagingAndFiltering(int page, int size, HashMap<String, String> param);

    MaMenuDto findOne(String seq);

    MaMenuDto findOneByCode(String code);

    int countByCode(String menuCd);

    void insert(MaMenuDto dto);

    void update(MaMenuDto dto);

    void delete(MaMenuDto dto);
}
