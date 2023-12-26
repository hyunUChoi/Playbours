package leave.meet.playbours.manage.member.word.repository;

import leave.meet.playbours.manage.member.user.dto.MaUserDto;
import leave.meet.playbours.manage.member.word.dto.MaWordDto;
import org.springframework.data.domain.Page;

public interface MaWordRepository {

    Page<MaWordDto> findByPagingAndFiltering(int page, int size, MaWordDto maWordDto);

    int countTotalRecords(MaWordDto dto);

    MaWordDto findOne(MaWordDto dto);

    void insert(MaWordDto dto);

    void update(MaWordDto dto);

    void delete(MaWordDto dto);
}
