package leave.meet.playbours.common.service;

import leave.meet.playbours.common.dto.CmmnDto;
import leave.meet.playbours.manage.sys.menu.service.MaMenuDto;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public class PagingService {
    public CmmnDto getPageInfo(Page<MaMenuDto> pageList, int pageNo, int pageSize) {
        pageNo = pageNo == 0 ? 0 : pageNo - 1;

        int totalPage = pageList.getTotalPages();
        int startNum = (int)((Math.floor((double) pageNo / pageSize) * pageSize) + 1 <= totalPage ? (Math.floor((double) pageNo / pageSize) * pageSize) + 1 : totalPage);
        int endNum = Math.min(startNum + pageSize - 1, totalPage);
        boolean hasPrev = pageList.hasPrevious();
        boolean hasNext = pageList.hasNext();
        int prevIdx = pageList.previousOrFirstPageable().getPageNumber() + 1;
        int nextIdx = pageList.nextOrLastPageable().getPageNumber() + 1;
        return new CmmnDto(totalPage, startNum, endNum, hasPrev, prevIdx, hasNext, nextIdx, pageNo);
    }
}
