package leave.meet.playbours.manage.sys.auth.service;

import jakarta.transaction.Transactional;
import leave.meet.playbours.manage.sys.auth.MaAuth;
import leave.meet.playbours.manage.sys.auth.dto.MaAuthDto;
import leave.meet.playbours.manage.sys.auth.MaAuthRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MaAuthService {
    private final MaAuthRepository maAuthRepository;

    @Transactional
    public Long save(MaAuthDto maAuthDto) {
        return maAuthRepository.save(maAuthDto.toEntity()).getSeq();
    }
}
