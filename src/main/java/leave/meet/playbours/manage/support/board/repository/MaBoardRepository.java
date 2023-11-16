package leave.meet.playbours.manage.support.board.repository;

import leave.meet.playbours.manage.support.board.service.MaBoardDto;
import org.springframework.data.domain.Page;

public interface MaBoardRepository {
    Page<MaBoardDto> findByPagingAndFiltering(int page, int size, MaBoardDto maBoardDto, String boardDivn);
}
