package leave.meet.playbours.manage.sys.auth.service;

import leave.meet.playbours.manage.sys.auth.MaAuth;
import leave.meet.playbours.manage.sys.auth.MaAuthRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class MaAuthService {
    private final MaAuthRepository maAuthRepository;


    public MaAuth save(MaAuth maAuth) {
        return maAuthRepository.save(maAuth);
    }

    public MaAuth delete(MaAuth maAuth) {
        retrun maAuthRepository.delete(maAuth);
    }

    public Optional<MaAuth> findByMaAuth(String maAuth) {
        return maAuthRepository.findByUsername(maAuth);
    }
}
