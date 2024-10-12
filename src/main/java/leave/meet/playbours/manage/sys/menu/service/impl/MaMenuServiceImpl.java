package leave.meet.playbours.manage.sys.menu.service.impl;

import leave.meet.playbours.common.paging.PagingResult;
import leave.meet.playbours.manage.sys.menu.service.MaMenuDAO;
import leave.meet.playbours.manage.sys.menu.service.MaMenuService;
import leave.meet.playbours.manage.sys.menu.service.MaMenuVO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MaMenuServiceImpl implements MaMenuService {

    private final MaMenuDAO maMenuDAO;

    @Override
    public Page<MaMenuVO> selectList(MaMenuVO maMenuVO, Pageable pageable) {
        PagingResult<?> pagingResult = PagingResult.builder()
                .data(maMenuVO)
                .pageable(pageable)
                .build();

        List<MaMenuVO> resultList = maMenuDAO.selectList(pagingResult);
        int total = maMenuDAO.selectCount(maMenuVO);

        return new PageImpl<>(resultList, pageable, total);
    }

    @Override
    public List<MaMenuVO> selectLowerList(MaMenuVO maMenuVO) {
        return maMenuDAO.selectLowerList(maMenuVO);
    }

    @Override
    public int selectCount(MaMenuVO maMenuVO) {
        return 0;
    }

    @Override
    public MaMenuVO selectContents(MaMenuVO maMenuVO, String procType) {
        if(procType.equalsIgnoreCase("view")) {
            return maMenuDAO.selectContentsByUpperCd(maMenuVO);
        } else {
            return maMenuDAO.selectContents(maMenuVO);
        }
    }

    @Override
    public int selectCodeCount(MaMenuVO maMenuVO) {
        return maMenuDAO.selectCodeCount(maMenuVO);
    }

    @Override
    @Transactional
    public void insertContents(MaMenuVO maMenuVO) {
        maMenuDAO.insertContents(maMenuVO);
    }

    @Override
    @Transactional
    public void updateContents(MaMenuVO maMenuVO) {
        maMenuDAO.updateContents(maMenuVO);
    }

    @Override
    @Transactional
    public void deleteContents(MaMenuVO maMenuVO) {
        maMenuDAO.deleteContents(maMenuVO);
        maMenuDAO.deleteLowerContents(maMenuVO);
    }
}
