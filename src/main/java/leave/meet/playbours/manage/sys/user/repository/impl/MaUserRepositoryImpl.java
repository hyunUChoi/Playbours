package leave.meet.playbours.manage.sys.user.repository.impl;

import leave.meet.playbours.manage.sys.menu.repository.MaMenuRepository;
import leave.meet.playbours.manage.sys.menu.service.MaMenuDto;
import leave.meet.playbours.manage.sys.user.repository.MaUserRepository;
import leave.meet.playbours.manage.sys.user.service.dto.MaUserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;

import java.util.List;

/*@Repository
public class MaUserRepositoryImpl implements MaUserRepository {

    private final MongoTemplate mongoTemplate;

    public MaUserRepositoryImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public Page<MaUserDto> findByPagingAndFiltering(int page, int size, MaUserDto maUserDto, String procType) {
        Pageable pageable = PageRequest.of(page == 0 ? 0 : page - 1, size, Sort.by("seq"));
        Query query = new Query()
                .with(pageable)
                .skip((long) pageable.getPageSize() * pageable.getPageNumber())
                .limit(pageable.getPageSize());

        //query.addCriteria(Criteria.where("useYn").is("Y"));

        *//* 쿼리 조건 *//*
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

        List<MaMenuDto> filterData = mongoTemplate.find(query, MaMenuDto.class);

        return PageableExecutionUtils.getPage(
                filterData, pageable, () -> mongoTemplate.count(query.skip(-1).limit(-1), MaMenuDto.class)
        );
    }
}*/
