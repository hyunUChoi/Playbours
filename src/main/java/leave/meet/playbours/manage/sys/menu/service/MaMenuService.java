package leave.meet.playbours.manage.sys.menu.service;

import java.util.List;

public interface MaMenuService {

    List<MaMenuVO> selectList(MaMenuVO maMenuVO);

    int selectCodeCount(MaMenuVO maMenuVO);

    void insertContents(MaMenuVO maMenuVO);
}
