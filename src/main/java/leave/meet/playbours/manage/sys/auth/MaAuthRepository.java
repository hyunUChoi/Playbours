package leave.meet.playbours.manage.sys.auth;

import leave.meet.playbours.manage.sys.auth.dto.MaAuthDto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MaAuthRepository extends JpaRepository<MaAuthDto, Long> {

}
