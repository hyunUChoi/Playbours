package leave.meet.playbours.manage.member.user.repository.impl;

import leave.meet.playbours.manage.member.user.service.MaUserDto;
import leave.meet.playbours.manage.member.user.repository.MaUserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public class MaUserRepositoryImpl implements MaUserRepository {

    private final MongoTemplate mongoTemplate;

    public MaUserRepositoryImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public Page<MaUserDto> findByPagingAndFiltering(int page, int size, MaUserDto dto) {
        Pageable pageable = PageRequest.of(page == 0 ? 0 : page - 1, size, Sort.by("seq"));
        Query query = new Query()
                .with(pageable)
                .skip((long) pageable.getPageSize() * pageable.getPageNumber())
                .limit(pageable.getPageSize());

        query.addCriteria(Criteria.where("delYn").is("N"));
        searchCondition(query, dto);
        query.with(Sort.by(Sort.Direction.DESC, "seq"));

        List<MaUserDto> filterData = mongoTemplate.find(query, MaUserDto.class);

        return PageableExecutionUtils.getPage(
                filterData, pageable, () -> mongoTemplate.count(query.skip(-1).limit(-1), MaUserDto.class)
        );
    }

    /* 검색 조건문 */
    public Query searchCondition(Query query, MaUserDto dto) {

        /* 구분 */
        if(dto.getUserClCd() != null) {
            query.addCriteria(Criteria.where("userClCd").gte(dto.getUserClCd()));
        }

        if(dto.getUseYn() != null) {
            query.addCriteria(Criteria.where("useYn").gte(dto.getUseYn()));
        }

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
    public int countTotalRecords(MaUserDto dto) {
        Query query = new Query();
        query.addCriteria(Criteria.where("delYn").is("N"));
        searchCondition(query, dto);
        return (int) mongoTemplate.count(query, MaUserDto.class);
    }

    @Override
    public MaUserDto findOne(MaUserDto dto) {
        Query query = new Query();
        query.addCriteria(Criteria.where("seq").is(dto.getSeq()));
        query.addCriteria(Criteria.where("delYn").is("N"));
        return mongoTemplate.findOne(query, MaUserDto.class);
    }

    @Override
    public void insert(MaUserDto dto) {
        MaUserDto userDto = new MaUserDto();
        userDto.setUserClCd(dto.getUserClCd());
        userDto.setUseYn(dto.getUseYn());
        userDto.setUserId(dto.getUserId());
        userDto.setUserPwd(dto.getUserPwd());
        userDto.setPwdFailCnt("O");
        userDto.setUserNm(dto.getUserNm());
        userDto.setUserAge(dto.getUserAge());
        userDto.setUserSex(dto.getUserSex());
        userDto.setUserPhone(dto.getUserPhone());
        userDto.setUserEmail(dto.getUserEmail());
        userDto.setInterest(dto.getInterest());
        userDto.setActiveArea(dto.getActiveArea());
        userDto.setUserCmt(dto.getUserCmt());
        userDto.setFrstRegrDt(new Date());
        userDto.setDelYn("N");
        mongoTemplate.insert(userDto);
    }

    @Override
    public void update(MaUserDto dto) {

    }

    @Override
    public void updateReply(MaUserDto dto) {

    }

    @Override
    public void delete(MaUserDto dto) {

    }

    @Override
    public void deleteReply(MaUserDto dto) {

    }

    @Override
    public int countById(String id) {
        Query query = new Query();
        query.addCriteria(Criteria.where("userId").is(id));
        query.addCriteria(Criteria.where("delYn").is("N"));
        return (int)mongoTemplate.count(query, MaUserDto.class);
    }
}
