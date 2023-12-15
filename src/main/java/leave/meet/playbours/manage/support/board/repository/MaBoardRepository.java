package leave.meet.playbours.manage.support.board.repository;

import leave.meet.playbours.manage.support.board.dto.MaBoardDto;
import org.springframework.data.domain.Page;

public interface MaBoardRepository {
    Page<MaBoardDto> findByPagingAndFiltering(int page, int size, MaBoardDto dto, String boardDivn);

    int countTotalRecords(MaBoardDto dto, String boardDivn);

    MaBoardDto findOne(MaBoardDto dto);

    void insert(MaBoardDto dto, String boardDivn);

    void update(MaBoardDto dto, String boardDivn);

    void updateReply(MaBoardDto dto);

    void delete(MaBoardDto dto);

    void deleteReply(MaBoardDto dto);
}
