package leave.meet.playbours.manage.sys.menu.service.impl;

import leave.meet.playbours.manage.sys.menu.service.MaMenuDAO;
import leave.meet.playbours.manage.sys.menu.service.MaMenuService;
import leave.meet.playbours.manage.sys.menu.service.MaMenuVO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MaMenuServiceImpl implements MaMenuService {

    private final MaMenuDAO maMenuDAO;

    @Override
    public List<MaMenuVO> selectList(MaMenuVO maMenuVO) {
        return maMenuDAO.selectList(maMenuVO);
    }

    @Override
    public int selectCount(MaMenuVO maMenuVO) {
        return 0;
    }

    @Override
    public MaMenuVO selectContents(MaMenuVO maMenuVO) {
        return maMenuDAO.selectContents(maMenuVO);
    }

    @Override
    public int selectCodeCount(MaMenuVO maMenuVO) {
        return maMenuDAO.selectCodeCount(maMenuVO);
    }

    @Override
    public void insertContents(MaMenuVO maMenuVO) {
        maMenuDAO.insertContents(maMenuVO);
    }

    @Override
    public void updateContents(MaMenuVO maMenuVO) {
        maMenuDAO.updateContents(maMenuVO);
    }
}
