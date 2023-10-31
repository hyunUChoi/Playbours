package leave.meet.playbours.manage.sys.menu.repository.impl;

import leave.meet.playbours.manage.sys.menu.repository.MaMenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class MaMenuRepositoryImpl implements MaMenuRepository {

    @Autowired
    private MongoTemplate mongoTemplate;
}
