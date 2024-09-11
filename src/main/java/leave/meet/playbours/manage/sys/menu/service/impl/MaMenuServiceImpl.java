/*
package leave.meet.playbours.manage.sys.menu.repository.impl;

import leave.meet.playbours.manage.sys.menu.service.MaMenuEntity;
import leave.meet.playbours.manage.sys.menu.repository.MaMenuRepository;
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
public class MaMenuRepositoryImpl implements MaMenuRepository {

    private final MongoTemplate mongoTemplate;

    public MaMenuRepositoryImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public Page<MaMenuEntity> findByPagingAndFiltering(int page, int size, MaMenuEntity dto, String procType) {
        Pageable pageable = PageRequest.of(page == 0 ? 0 : page - 1, size, Sort.by("seq"));
        Query query = new Query()
                .with(pageable)
                .skip((long) pageable.getPageSize() * pageable.getPageNumber())
                .limit(pageable.getPageSize());

        query.addCriteria(Criteria.where("delYn").is("N"));

        */
/* 쿼리 조건 *//*

        if(dto.getSearch1() != null && !"".equals(dto.getSearch1() )) {
            query.addCriteria(Criteria.where("menuClCd").is(dto.getSearch1()));
        }

        if("list".equals(procType)) {
            if(dto.getSearchKeyword() != null && !"".equals(dto.getSearchKeyword())) {
                if(dto.getSearchOption() != null && !"".equals(dto.getSearchOption())) {
                    switch (dto.getSearchOption()) {
                        case "0" -> {
                            Criteria criteria = new Criteria();
                            criteria.orOperator(Criteria.where("menuCd").regex(dto.getSearchKeyword()), Criteria.where("menuNm").regex(dto.getSearchKeyword()));
                            query.addCriteria(criteria);
                        }
                        case "1" -> {
                            query.addCriteria(Criteria.where("menuNm").regex(dto.getSearchKeyword()));
                        }
                        case "2" -> {
                            query.addCriteria(Criteria.where("menuCd").regex(dto.getSearchKeyword()));
                        }
                    }
                }
            }
        }

        query.addCriteria(Criteria.where("upperCd").is("view".equals(procType) ? dto.getMenuCd() : ""));
        query.with(Sort.by(Sort.Direction.DESC, "seq"));

        List<MaMenuEntity> filterData = mongoTemplate.find(query, MaMenuEntity.class);

        return PageableExecutionUtils.getPage(
                filterData, pageable, () -> mongoTemplate.count(query.skip(-1).limit(-1), MaMenuEntity.class)
        );
    }

    @Override
    public MaMenuEntity findOne(MaMenuEntity dto) {
        Query query = new Query();
        query.addCriteria(Criteria.where("seq").is(dto.getSeq()));
        query.addCriteria(Criteria.where("delYn").is("N"));
        return mongoTemplate.findOne(query, MaMenuEntity.class);
    }

    @Override
    public MaMenuEntity findOneByCode(MaMenuEntity dto) {
        Query query = new Query();
        query.addCriteria(Criteria.where("menuCd").is(dto.getUpperCd()));
        query.addCriteria(Criteria.where("delYn").is("N"));
        return mongoTemplate.findOne(query, MaMenuEntity.class);
    }

    @Override
    public int countByCode(String menuCd) {
        Query query = new Query();
        query.addCriteria(Criteria.where("menuCd").is(menuCd));
        query.addCriteria(Criteria.where("delYn").is("N"));
        return (int) mongoTemplate.count(query, MaMenuEntity.class);
    }

    @Override
    public void insert(MaMenuEntity dto) {
        MaMenuEntity menuDto = new MaMenuEntity();
        menuDto.setUpperCd(dto.getUpperCd());
        menuDto.setMenuClCd(dto.getMenuClCd());
        menuDto.setMenuOrd(dto.getMenuOrd());
        menuDto.setUseYn(dto.getUseYn());
        menuDto.setMenuNm(dto.getMenuNm());
        menuDto.setMenuCd(dto.getMenuCd());
        menuDto.setMenuUrl(dto.getMenuUrl());
        menuDto.setMenuCmt(dto.getMenuCmt());
        // TODO 로그인한 아이디로 변경
        menuDto.setFrstRegrId("admin");
        menuDto.setFrstRegrDt(new Date());
        menuDto.setDelYn(dto.getDelYn());
        mongoTemplate.insert(menuDto);
    }

    @Override
    public void update(MaMenuEntity dto) {
        Query query = new Query();
        Update update = new Update();

        query.addCriteria(Criteria.where("seq").is(dto.getSeq()));
        update.set("upperCd", dto.getUpperCd());
        update.set("menuClCd", dto.getMenuClCd());
        update.set("menuOrd", dto.getMenuOrd());
        update.set("useYn", dto.getUseYn());
        update.set("menuNm", dto.getMenuNm());
        update.set("menuCd", dto.getMenuCd());
        update.set("menuUrl", dto.getMenuUrl());
        update.set("menuCmt", dto.getMenuCmt());
        // TODO 로그인한 아이디로 변경
        update.set("lstChgId", "admin");
        update.set("lstChgDt", new Date());
        mongoTemplate.upsert(query, update, MaMenuEntity.class);
    }

    @Override
    public void delete(MaMenuEntity dto) {
        Query query = new Query();
        Update update = new Update();

        query.addCriteria(Criteria.where("seq").is(dto.getSeq()));
        // TODO 로그인한 아이디로 변경
        update.set("lstChgId", "admin");
        update.set("lstChgDt", new Date());
        update.set("delYn", "Y");
        mongoTemplate.upsert(query, update, MaMenuEntity.class);
    }

    @Override
    public List<MaMenuEntity> findMenuList(MaMenuEntity dto) {
        Query query = new Query();
        query.addCriteria(Criteria.where("menuClCd").is(dto.getMenuClCd()));
        query.addCriteria(Criteria.where("upperCd").is(dto.getUpperCd()));
        query.addCriteria(Criteria.where("useYn").is("Y"));
        query.addCriteria(Criteria.where("delYn").is("N"));
        query.with(Sort.by(Sort.Direction.ASC, "menuOrd"));
        return mongoTemplate.find(query, MaMenuEntity.class);
    }
}
*/
