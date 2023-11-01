package leave.meet.playbours.manage.sys.user.repository.impl;

import leave.meet.playbours.manage.sys.menu.repository.MaMenuRepository;
import leave.meet.playbours.manage.sys.user.repository.MaUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class MaUserRepositoryImpl implements MaUserRepository {

    @Autowired
    private MongoTemplate mongoTemplate;
}
