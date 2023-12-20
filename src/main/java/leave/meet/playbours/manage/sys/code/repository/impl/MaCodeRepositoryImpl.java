package leave.meet.playbours.manage.sys.code.repository.impl;

import leave.meet.playbours.manage.member.user.dto.MaUserDto;
import leave.meet.playbours.manage.support.board.dto.MaBoardDto;
import leave.meet.playbours.manage.sys.code.repository.MaCodeRepository;
import leave.meet.playbours.manage.sys.code.dto.MaCodeDto;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
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
    public int countByCode(String code) {
        Query query = new Query();
        query.addCriteria(Criteria.where("code").is(code));
        query.addCriteria(Criteria.where("delYn").is("N"));
        return (int)mongoTemplate.count(query, MaCodeDto.class);
    }


    @Override
    public void insert(MaCodeDto dto) {
        MaCodeDto maCodeDto = new MaCodeDto();
        maCodeDto.setGroupCode(dto.getGroupCode());
        maCodeDto.setParentCode(dto.getParentCode());
        maCodeDto.setCode(dto.getCode());
        maCodeDto.setName(dto.getName());
        maCodeDto.setUseYn(dto.getUseYn());
        maCodeDto.setOrder(dto.getOrder());
        maCodeDto.setDelYn("N");
        // TODO 로그인한 아이디로 변경
        maCodeDto.setFrstRegrId("admin");
        maCodeDto.setFrstRegrDt(new Date());
        mongoTemplate.insert(maCodeDto);
    }

    @Override
    public void update(MaCodeDto dto) {

        Query query = new Query();
        Update update = new Update();
        query.addCriteria(Criteria.where("code").is(dto.getCode()));
        update.set("groupCode", dto.getGroupCode());
        update.set("parentCode", dto.getParentCode());
        update.set("code", dto.getCode());
        update.set("name", dto.getName());
        update.set("useYn", dto.getUseYn());
        update.set("delYn", "N");
        update.set("order", dto.getOrder());
        // TODO 로그인한 아이디로 변경
        update.set("lstChgId", "admin");
        update.set("lstChgDt", new Date());
        mongoTemplate.upsert(query, update, MaCodeDto.class);
    }

    @Override
    public void delete(MaCodeDto dto) {

        Query query = new Query();
        Update update = new Update();
        query.addCriteria(Criteria.where("code").is(dto.getCode()));
        update.set("delYn", "Y");
        // TODO 로그인한 아이디로 변경
        update.set("lstChgId", "admin");
        update.set("lstChgDt", new Date());
        mongoTemplate.upsert(query, update, MaCodeDto.class);

    }

    @Override
    public List<MaCodeDto> findCodeList1(String parentCode) {

        Query query = new Query();
        query.addCriteria(Criteria.where("parentCode").is(parentCode));
        query.addCriteria(Criteria.where("delYn").is("N"));
        query.with(Sort.by(Sort.Direction.DESC, "order"));
        return mongoTemplate.find(query,MaCodeDto.class);
    }

    @Override
    public List<MaCodeDto> findCodeList2(String parentCode) {

        Query query = new Query();
        query.addCriteria(Criteria.where("parentCode").is(parentCode));
        query.addCriteria(Criteria.where("delYn").is("N"));
        query.addCriteria(Criteria.where("useYn").is("Y"));
        query.with(Sort.by(Sort.Direction.ASC, "order"));
        return mongoTemplate.find(query,MaCodeDto.class);
    }

    @Override
    public MaCodeDto findCodeDetail(String code) {

        Query query = new Query();
        query.addCriteria(Criteria.where("code").is(code));
        query.addCriteria(Criteria.where("delYn").is("N"));
        return mongoTemplate.findOne(query,MaCodeDto.class);
    }
}
