package leave.meet.playbours.manage.sys.menu.repository.impl;

import leave.meet.playbours.manage.sys.menu.repository.MaMenuRepository;
import leave.meet.playbours.manage.sys.menu.service.MaMenuDto;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public class MaMenuRepositoryImpl implements MaMenuRepository {

    private final MongoTemplate mongoTemplate;

    private final static String COLLECTION_NAME = "TB_MENU";

    public MaMenuRepositoryImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public List<MaMenuDto> findAll() {
        Query query = new Query();
        query.addCriteria(Criteria.where("useYn").is("Y"));
        query.with(Sort.by(Sort.Direction.DESC, "seq"));
        return mongoTemplate.find(query, MaMenuDto.class, COLLECTION_NAME);
    }

    @Override
    public MaMenuDto findOne(String seq) {
        Query query = new Query();
        query.addCriteria(Criteria.where("seq").is(seq));
        return mongoTemplate.findOne(query, MaMenuDto.class, COLLECTION_NAME);
    }

    @Override
    public int countByCode(String menuCd) {
        Query query = new Query();
        query.addCriteria(Criteria.where("menuCd").is(menuCd));
        query.addCriteria(Criteria.where("useYn").is("Y"));
        return (int)mongoTemplate.count(query, COLLECTION_NAME);
    }

    @Override
    public void insert(MaMenuDto dto) {
        MaMenuDto menuDto = new MaMenuDto();
        menuDto.setMenuClCd(dto.getMenuClCd());
        menuDto.setMenuCd(dto.getMenuCd());
        menuDto.setUpperCd(dto.getUpperCd());
        menuDto.setMenuNm(dto.getMenuNm());
        menuDto.setMenuOrd(dto.getMenuOrd());
        menuDto.setMenuUrl(dto.getMenuUrl());
        menuDto.setMenuCmt(dto.getMenuCmt());
        menuDto.setUseYn(dto.getUseYn());
        // TODO 로그인한 아이디로 변경
        menuDto.setFrstRegrId("admin");
        menuDto.setFrstRegrDt(new Date());
        mongoTemplate.insert(menuDto, COLLECTION_NAME);
    }

    @Override
    public void update(MaMenuDto dto) {
        Query query = new Query();
        Update update = new Update();

        query.addCriteria(Criteria.where("seq").is(dto.getSeq()));
        update.set("menuClCd", dto.getMenuClCd());
        update.set("menuCd", dto.getMenuCd());
        update.set("menuNm", dto.getMenuNm());
        update.set("menuOrd", dto.getMenuOrd());
        update.set("menuUrl", dto.getMenuUrl());
        update.set("menuCmt", dto.getMenuCmt());
        // TODO 로그인한 아이디로 변경
        update.set("lstChgId", "admin");
        update.set("lstChgDt", new Date());
        mongoTemplate.upsert(query, update, MaMenuDto.class, COLLECTION_NAME);
    }

    @Override
    public void delete(MaMenuDto dto) {
        Query query = new Query();
        Update update = new Update();

        query.addCriteria(Criteria.where("seq").is(dto.getSeq()));
        update.set("useYn", "N");
        // TODO 로그인한 아이디로 변경
        update.set("lstChgId", "admin");
        update.set("lstChgDt", new Date());
        mongoTemplate.upsert(query, update, MaMenuDto.class, COLLECTION_NAME);
    }
}
