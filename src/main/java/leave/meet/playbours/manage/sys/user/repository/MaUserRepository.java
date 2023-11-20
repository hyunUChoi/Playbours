package leave.meet.playbours.manage.sys.user.repository;

import leave.meet.playbours.manage.sys.menu.service.MaMenuDto;
import leave.meet.playbours.manage.sys.user.service.dto.MaUserDto;
import org.springframework.data.domain.Page;

public interface MaUserRepository {

    Page<MaUserDto> findByPagingAndFiltering(int page, int size, MaUserDto maUserDto, String procType);
}
