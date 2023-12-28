package leave.meet.playbours.manage.sys.auth.repository;

import leave.meet.playbours.manage.sys.auth.dto.MaAuthDto;
import leave.meet.playbours.manage.sys.menu.dto.MaMenuDto;
import org.springframework.data.domain.Page;

import java.util.List;

public interface MaAuthRepository {

    Page<MaAuthDto> findByPagingAndFiltering(int page, int size, MaAuthDto dto);
    List<MaAuthDto> findAll(MaAuthDto dto);

    void insert(MaAuthDto dto);

    void update(MaAuthDto dto);

    void delete(MaAuthDto dto);

}
