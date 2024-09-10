package leave.meet.playbours.manage.sys.auth;

import leave.meet.playbours.manage.sys.auth.dto.MaAuthDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MaAuthRepository extends JpaRepository<MaAuth, String> {

    Optional<MaAuth> findByUsername(String seq);
}
