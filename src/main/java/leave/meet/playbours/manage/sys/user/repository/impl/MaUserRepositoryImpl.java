package leave.meet.playbours.manage.sys.user.repository.impl;

import leave.meet.playbours.manage.sys.menu.service.MaMenuDto;
import leave.meet.playbours.manage.sys.user.repository.MaUserRepository;
import leave.meet.playbours.manage.sys.user.service.MaUserDto;
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

<<<<<<< HEAD
        /* 쿼리 조건 */
        if(maUserDto.getSearch1() != null && !"".equals(maUserDto.getSearch1() )) {
            query.addCriteria(Criteria.where("userClCd").is(maUserDto.getSearch1()));
=======
        *//* 쿼리 조건 *//*
        if(maMenuDto.getSearch1() != null && !"".equals(maMenuDto.getSearch1() )) {
            query.addCriteria(Criteria.where("menuClCd").is(maMenuDto.getSearch1()));
>>>>>>> upstearm/main
        }

        if("list".equals(procType)) {
            if(maUserDto.getSearchKeyword() != null && !"".equals(maUserDto.getSearchKeyword())) {
                if(maUserDto.getSearchOption() != null && !"".equals(maUserDto.getSearchOption())) {
                    switch (maUserDto.getSearchOption()) {
                        case "0" -> {
                            Criteria criteria = new Criteria();
                            criteria.orOperator(Criteria.where("userCd").is(maUserDto.getSearchKeyword()), Criteria.where("userNm").is(maUserDto.getSearchKeyword()));
                            query.addCriteria(criteria);
                        }
                        case "1" -> {
                            query.addCriteria(Criteria.where("userNm").is(maUserDto.getSearchKeyword()));
                        }
                        case "2" -> {
                            query.addCriteria(Criteria.where("userCd").is(maUserDto.getSearchKeyword()));
                        }
                    }
                }
            }
        }

        query.addCriteria(Criteria.where("upperCd").is("view".equals(procType) ? maUserDto.getUserCd() : ""));
        query.with(Sort.by(Sort.Direction.DESC, "seq"));

        List<MaUserDto> filterData = mongoTemplate.find(query, MaUserDto.class);

        return PageableExecutionUtils.getPage(
                filterData, pageable, () -> mongoTemplate.count(query.skip(-1).limit(-1), MaUserDto.class)
        );
    }
}*/
