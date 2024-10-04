package leave.meet.playbours.manage.sys.auth.service;

import leave.meet.playbours.manage.sys.menu.service.MaMenuVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MaAuthDAO {

    List<MaAuthVO> selectList(MaAuthVO maAuthVO);

    int selectCount(MaAuthVO maAuthVO);

    MaAuthVO selectContents(MaAuthVO maAuthVO);

    int selectCodeCount(MaAuthVO maAuthVO);

    void insertContents(MaAuthVO maAuthVO);
}