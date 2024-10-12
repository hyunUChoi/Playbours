package leave.meet.playbours.manage.sys.menu.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface MaMenuService {

    Page<MaMenuVO> selectList(MaMenuVO maMenuVO, Pageable pageable);

    List<MaMenuVO> selectLowerList(MaMenuVO maMenuVO);

    int selectCount(MaMenuVO maMenuVO);

    MaMenuVO selectContents(MaMenuVO maMenuVO, String procType);

    int selectCodeCount(MaMenuVO maMenuVO);

    void insertContents(MaMenuVO maMenuVO);

    void updateContents(MaMenuVO maMenuVO);

    void deleteContents(MaMenuVO maMenuVO);
}
