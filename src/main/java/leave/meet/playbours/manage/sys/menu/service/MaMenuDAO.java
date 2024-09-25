package leave.meet.playbours.manage.sys.menu.service;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MaMenuDAO {

    List<MaMenuVO> selectList(MaMenuVO maMenuVO);

    int selectCodeCount(MaMenuVO maMenuVO);

    void insertContents(MaMenuVO maMenuVO);
}
