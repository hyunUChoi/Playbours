package leave.meet.playbours.manage.sys.auth.service.impl;

import leave.meet.playbours.manage.sys.auth.service.MaAuthService;
import leave.meet.playbours.manage.sys.auth.service.MaAuthVO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MaAuthServiceImpl implements MaAuthService {

    @Override
    public String selectContent() throws Exception {
        return "";
    }

    @Override
    public List<MaAuthVO> selectList() throws Exception {
        return List.of();
    }

    @Override
    public int selectCount() throws Exception {
        return 0;
    }

    @Override
    public int insertContet() throws Exception {
        return 0;
    }

    @Override
    public int updateContet() throws Exception {
        return 0;
    }

    @Override
    public int deleteContent() throws Exception {
        return 0;
    }
}

