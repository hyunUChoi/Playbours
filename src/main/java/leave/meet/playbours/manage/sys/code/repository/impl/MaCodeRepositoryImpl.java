package leave.meet.playbours.manage.sys.code.repository.impl;

import leave.meet.playbours.manage.sys.code.repository.MaCodeRepository;
import leave.meet.playbours.manage.sys.code.dto.MaCodeDto;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MaCodeRepositoryImpl implements MaCodeRepository {

    private final MongoTemplate mongoTemplate;

    public MaCodeRepositoryImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public List<MaCodeDto> findAll(MaCodeDto dto) {

        Query query = new Query();
        query.addCriteria(Criteria.where("code").regex("CD"));
        query.addCriteria(Criteria.where("delYn").is("N"));
        query.with(Sort.by(Sort.Direction.ASC, "code"));
        return mongoTemplate.find(query,MaCodeDto.class);
    }

    @Override
    public MaCodeDto findOne(MaCodeDto dto) {
        return null;
    }

    @Override
    public MaCodeDto findOneByCode(MaCodeDto dto) {
        return null;
    }

    @Override
    public int countByCode(String menuCd) {
        return 0;
    }

    @Override
    public void insert(MaCodeDto dto) {

    }

    @Override
    public void update(MaCodeDto dto) {

    }

    @Override
    public void delete(MaCodeDto dto) {

    }

    @Override
    public List<MaCodeDto> findCodeList(String groupCode) {

        Query query = new Query();
        query.addCriteria(Criteria.where("groupCode").is(groupCode));
        query.addCriteria(Criteria.where("delYn").is("N"));
        query.with(Sort.by(Sort.Direction.ASC, "code"));
        return mongoTemplate.find(query,MaCodeDto.class);
    }
}
