package leave.meet.playbours.manage.sys.menu.service;

import leave.meet.playbours.common.paging.PagingResult;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MaMenuDAO {

    List<MaMenuVO> selectList(PagingResult<?> pagingResult);

    List<MaMenuVO> selectLowerList(MaMenuVO maMenuVO);

    int selectCount(MaMenuVO maMenuVO);

    MaMenuVO selectContents(MaMenuVO maMenuVO);

    MaMenuVO selectContentsByUpperCd(MaMenuVO maMenuVO);

    int selectCodeCount(MaMenuVO maMenuVO);

    void insertContents(MaMenuVO maMenuVO);

    void updateContents(MaMenuVO maMenuVO);

    void deleteContents(MaMenuVO maMenuVO);

    void deleteLowerContents(MaMenuVO maMenuVO);
}
