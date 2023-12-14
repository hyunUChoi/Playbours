package leave.meet.playbours.manage.member.user.repository.impl;

import leave.meet.playbours.manage.member.user.service.MaUserDto;
import leave.meet.playbours.manage.member.user.repository.MaUserRepository;
import leave.meet.playbours.manage.support.board.service.MaBoardDto;
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
        userDto.setUserYmd(dto.getUserYmd());
        userDto.setUserSex(dto.getUserSex());
        userDto.setUserPhone(dto.getUserPhone());
        userDto.setUserEmail(dto.getUserEmail());
        userDto.setInterest(dto.getInterest());
        userDto.setAreaSido(dto.getAreaSido());
        userDto.setAreaGungu(dto.getAreaGungu());
        userDto.setUserCmt(dto.getUserCmt());
        // TODO 로그인한 아이디로 변경
        userDto.setFrstRegrId("admin");
        userDto.setFrstRegrDt(new Date());
        userDto.setDelYn("N");
        mongoTemplate.insert(userDto);
    }

    @Override
    public void update(MaUserDto dto) {

        Query query = new Query();
        Update update = new Update();

        query.addCriteria(Criteria.where("seq").is(dto.getSeq()));
        update.set("userClCd", dto.getUserClCd());
        update.set("useYn", dto.getUseYn());
        update.set("userId", dto.getUserId());
        update.set("userPwd", dto.getUserPwd());
        update.set("userNm", dto.getUserNm());
        update.set("userYmd", dto.getUserYmd());
        update.set("userSex", dto.getUserSex());
        update.set("userPhone", dto.getUserPhone());
        update.set("userEmail", dto.getUserPhone());
        update.set("interest", dto.getInterest());
        update.set("areaSido", dto.getAreaSido());
        update.set("areaGungu", dto.getAreaGungu());
        update.set("userCmt", dto.getUserCmt());
        // TODO 로그인한 아이디로 변경
        update.set("lstChgId", "admin");
        update.set("lstChgDt", new Date());
        mongoTemplate.upsert(query, update, MaUserDto.class);

    }

    @Override
    public void delete(MaUserDto dto) {
        Query query = new Query();
        Update update = new Update();
        query.addCriteria(Criteria.where("seq").is(dto.getSeq()));
        // TODO 로그인한 아이디로 변경
        update.set("lstChgId", "admin");
        update.set("lstChgDt", new Date());
        update.set("delYn", "Y");
        mongoTemplate.upsert(query, update, MaUserDto.class);
    }

    @Override
    public int countById(String id) {
        Query query = new Query();
        query.addCriteria(Criteria.where("userId").is(id));
        query.addCriteria(Criteria.where("delYn").is("N"));
        return (int)mongoTemplate.count(query, MaUserDto.class);
    }
}
