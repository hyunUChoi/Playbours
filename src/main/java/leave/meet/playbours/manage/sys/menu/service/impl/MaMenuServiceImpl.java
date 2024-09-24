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
}
