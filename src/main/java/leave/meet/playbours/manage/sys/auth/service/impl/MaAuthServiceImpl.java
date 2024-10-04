package leave.meet.playbours.manage.sys.auth.service.impl;

import leave.meet.playbours.manage.sys.auth.service.MaAuthDAO;
import leave.meet.playbours.manage.sys.auth.service.MaAuthService;
import leave.meet.playbours.manage.sys.auth.service.MaAuthVO;
import leave.meet.playbours.manage.sys.menu.service.MaMenuDAO;
import leave.meet.playbours.manage.sys.menu.service.MaMenuVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MaAuthServiceImpl implements MaAuthService {

    private final MaAuthDAO maAuthDAO;

    @Override
    public List<MaAuthVO> selectList(MaAuthVO maAuthVO) {
        return maAuthDAO.selectList(maAuthVO);
    }

    @Override
    public int selectCount(MaAuthVO maAuthVO) {
        return 0;
    }

    @Override
    public MaAuthVO selectContents(MaAuthVO maAuthVO) {
        return maAuthDAO.selectContents(maAuthVO);
    }

    @Override
    public int selectCodeCount(MaAuthVO maAuthVO) {
        return maAuthDAO.selectCodeCount(maAuthVO);
    }

    @Override
    public void insertContents(MaAuthVO maAuthVO) {
        maAuthDAO.insertContents(maAuthVO);
    }
}

