package leave.meet.playbours.manage.sys.auth.service;

import java.util.List;

public interface MaAuthService {

    public String selectContent()throws Exception;

    public List<MaAuthVO> selectList()throws Exception;

    public int  selectCount()throws Exception;

    public int insertContet()throws Exception;

    public int updateContet()throws Exception;

    public int deleteContent()throws Exception;
}
