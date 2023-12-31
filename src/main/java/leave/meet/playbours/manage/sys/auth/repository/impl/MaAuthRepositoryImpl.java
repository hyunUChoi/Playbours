package leave.meet.playbours.manage.sys.auth.repository.impl;

import leave.meet.playbours.manage.member.user.dto.MaUserDto;
import leave.meet.playbours.manage.sys.auth.dto.MaAuthDto;
import leave.meet.playbours.manage.sys.auth.repository.MaAuthRepository;
import leave.meet.playbours.manage.sys.menu.dto.MaMenuDto;
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
public class MaAuthRepositoryImpl implements MaAuthRepository {

    private final MongoTemplate mongoTemplate;

    public MaAuthRepositoryImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public Page<MaAuthDto> findByPagingAndFiltering(int page, int size, MaAuthDto dto) {
        Pageable pageable = PageRequest.of(page == 0 ? 0 : page - 1, size, Sort.by("seq"));
        Query query = new Query()
                .with(pageable)
                .skip((long) pageable.getPageSize() * pageable.getPageNumber())
                .limit(pageable.getPageSize());

        query.addCriteria(Criteria.where("delYn").is("N"));
        searchCondition(query, dto);
        query.with(Sort.by(Sort.Direction.DESC, "seq"));

        List<MaAuthDto> filterData = mongoTemplate.find(query, MaAuthDto.class);

        return PageableExecutionUtils.getPage(
                filterData, pageable, () -> mongoTemplate.count(query.skip(-1).limit(-1), MaAuthDto.class)
        );
    }

    public Query searchCondition(Query query, MaAuthDto dto) {

        /* 검색조건 */
        if(dto.getSearchKeyword() != null && !"".equals(dto.getSearchKeyword())) {
            if(dto.getSearchOption() != null && !"".equals(dto.getSearchOption())) {
                switch (dto.getSearchOption()) {
                    case "0" -> {
                        Criteria criteria = new Criteria();
                        criteria = criteria.orOperator(Criteria.where("userNm").regex(dto.getSearchKeyword()), Criteria.where("userId").regex( dto.getSearchKeyword()));
                        query.addCriteria(criteria);
                    }
                    case "1" -> {
                        query.addCriteria(Criteria.where("userId").regex(dto.getSearchKeyword()));
                    }
                    case "2" -> {
                        query.addCriteria(Criteria.where("userNm").regex(dto.getSearchKeyword()));
                    }
                }
            }
        }

        return query;
    }

    @Override
    public MaAuthDto findOne(MaAuthDto dto) {

        Query query = new Query();
        query.addCriteria(Criteria.where("code").regex("CD"));
        query.addCriteria(Criteria.where("delYn").is("N"));
        query.with(Sort.by(Sort.Direction.ASC, "code"));
        return mongoTemplate.findOne(query, MaAuthDto.class);
    }



    @Override
    public void insert(MaAuthDto dto) {
        MaAuthDto maCodeDto = new MaAuthDto();
        maCodeDto.setAuthNm(dto.getAuthNm());
        maCodeDto.setAuthRank(dto.getAuthRank());
        maCodeDto.setAuthCont(dto.getAuthCont());
        maCodeDto.setAuthOrder(dto.getAuthOrder());
        maCodeDto.setUseYn(dto.getUseYn());
        maCodeDto.setDelYn("N");
        // TODO 로그인한 아이디로 변경
        maCodeDto.setFrstRegrId("admin");
        maCodeDto.setFrstRegrDt(new Date());
        mongoTemplate.insert(maCodeDto);
    }

    @Override
    public void update(MaAuthDto dto) {

        Query query = new Query();
        Update update = new Update();
        query.addCriteria(Criteria.where("seq").is(dto.getSeq()));
        update.set("authNm", dto.getAuthNm());
        update.set("authRank", dto.getAuthRank());
        update.set("authCont", dto.getAuthCont());
        update.set("authOrder", dto.getAuthOrder());
        update.set("useYn", dto.getUseYn());
        update.set("delYn", "N");
        // TODO 로그인한 아이디로 변경
        update.set("lstChgId", "admin");
        update.set("lstChgDt", new Date());
        mongoTemplate.upsert(query, update, MaAuthDto.class);
    }

    @Override
    public void delete(MaAuthDto dto) {

        Query query = new Query();
        Update update = new Update();
        query.addCriteria(Criteria.where("seq").is(dto.getSeq()));
        update.set("delYn", "Y");
        // TODO 로그인한 아이디로 변경
        update.set("lstChgId", "admin");
        update.set("lstChgDt", new Date());
        mongoTemplate.upsert(query, update, MaAuthDto.class);

    }
}
