package leave.meet.playbours.manage.sys.menu.repository;

import leave.meet.playbours.manage.sys.menu.service.MaMenuDto;

import java.util.List;

public interface MaMenuRepository {
    List<MaMenuDto> findAll();

    MaMenuDto findOne(MaMenuDto dto);

    void insert(MaMenuDto dto);

    void update(MaMenuDto dto);

    void delete(MaMenuDto dto);
}
