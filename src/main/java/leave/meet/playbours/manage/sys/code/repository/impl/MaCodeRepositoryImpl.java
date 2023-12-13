package leave.meet.playbours.manage.sys.code.repository.impl;

import leave.meet.playbours.manage.sys.code.repository.MaCodeRepository;
import leave.meet.playbours.manage.sys.code.service.MaCodeDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public class MaCodeRepositoryImpl implements MaCodeRepository {

    private final MongoTemplate mongoTemplate;

    public MaCodeRepositoryImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public Page<MaCodeDto> findByPagingAndFiltering(int page, int size, MaCodeDto dto, String procType) {
        return null;
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
    public List<MaCodeDto> findCodeList(String code) {

        Query query = new Query();
        query.addCriteria(Criteria.where("groupSeq").is(code));
        query.addCriteria(Criteria.where("delYn").is("N"));
        return mongoTemplate.find(query,MaCodeDto.class);
    }
}
