package leave.meet.playbours.manage.member.user.repository;

import leave.meet.playbours.manage.member.user.service.MaUserDto;
import org.springframework.data.domain.Page;

public interface MaUserRepository {

    Page<MaUserDto> findByPagingAndFiltering(int page, int size, MaUserDto maUserDto);

    int countTotalRecords(MaUserDto dto);

    MaUserDto findOne(MaUserDto dto);

    void insert(MaUserDto dto);

    void update(MaUserDto dto);

    void updateReply(MaUserDto dto);

    void delete(MaUserDto dto);

    void deleteReply(MaUserDto dto);

    int countById(String id);
}
