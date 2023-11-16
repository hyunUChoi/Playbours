package leave.meet.playbours.manage.sys.menu.repository.impl;

import leave.meet.playbours.manage.sys.menu.repository.MaMenuRepository;
import leave.meet.playbours.manage.sys.menu.service.MaMenuDto;
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
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

@Repository
public class MaMenuRepositoryImpl implements MaMenuRepository {

    private final MongoTemplate mongoTemplate;

    private final static String COLLECTION_NAME = "TB_MENU";

    public MaMenuRepositoryImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public Page<MaMenuDto> findByPagingAndFiltering(int page, int size, MaMenuDto maMenuDto, String procType) { /*HashMap<String, String> param*/
        Pageable pageable = PageRequest.of(page == 0 ? 0 : page - 1, size, Sort.by("seq"));
        Query query = new Query()
                .with(pageable)
                .skip((long) pageable.getPageSize() * pageable.getPageNumber())
                .limit(pageable.getPageSize());

        query.addCriteria(Criteria.where("useYn").is("Y"));

        /* 쿼리 조건 */
        if(maMenuDto.getSearch1() != null && !"".equals(maMenuDto.getSearch1() )) {
            query.addCriteria(Criteria.where("menuClCd").is(maMenuDto.getSearch1()));
        }

        if("list".equals(procType)) {
            if(maMenuDto.getSearchKeyword() != null && !"".equals(maMenuDto.getSearchKeyword())) {
                if(maMenuDto.getSearchOption() != null && !"".equals(maMenuDto.getSearchOption())) {
                    switch (maMenuDto.getSearchOption()) {
                        case "0" -> {
                            Criteria criteria = new Criteria();
                            criteria.orOperator(Criteria.where("menuCd").is(maMenuDto.getSearchKeyword()), Criteria.where("menuNm").is(maMenuDto.getSearchKeyword()));
                            query.addCriteria(criteria);
                        }
                        case "1" -> {
                            query.addCriteria(Criteria.where("menuNm").is(maMenuDto.getSearchKeyword()));
                        }
                        case "2" -> {
                            query.addCriteria(Criteria.where("menuCd").is(maMenuDto.getSearchKeyword()));
                        }
                    }
                }
            }
        }

        query.addCriteria(Criteria.where("upperCd").is("view".equals(procType) ? maMenuDto.getMenuCd() : ""));
        query.with(Sort.by(Sort.Direction.DESC, "seq"));

        List<MaMenuDto> filterData = mongoTemplate.find(query, MaMenuDto.class, COLLECTION_NAME);

        return PageableExecutionUtils.getPage(
                filterData, pageable, () -> mongoTemplate.count(query.skip(-1).limit(-1), MaMenuDto.class)
        );
    }

    @Override
    public MaMenuDto findOne(MaMenuDto maMenuDto) {
        Query query = new Query();
        query.addCriteria(Criteria.where("seq").is(maMenuDto.getSeq()));
        return mongoTemplate.findOne(query, MaMenuDto.class, COLLECTION_NAME);
    }

    @Override
    public MaMenuDto findOneByCode(MaMenuDto maMenuDto) {
        Query query = new Query();
        query.addCriteria(Criteria.where("menuCd").is(maMenuDto.getUpperCd()));
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
        update.set("upperCd", dto.getUpperCd());
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
