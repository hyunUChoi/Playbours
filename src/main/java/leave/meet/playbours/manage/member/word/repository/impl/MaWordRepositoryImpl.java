package leave.meet.playbours.manage.member.word.repository.impl;

import leave.meet.playbours.manage.member.user.dto.MaUserDto;
import leave.meet.playbours.manage.member.word.dto.MaWordDto;
import leave.meet.playbours.manage.member.word.repository.MaWordRepository;
import leave.meet.playbours.manage.sys.code.dto.MaCodeDto;
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
public class MaWordRepositoryImpl implements MaWordRepository {

    private final MongoTemplate mongoTemplate;

    public MaWordRepositoryImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }


    @Override
    public Page<MaWordDto> findByPagingAndFiltering(int page, int size, MaWordDto dto) {
        Pageable pageable = PageRequest.of(page == 0 ? 0 : page - 1, size, Sort.by("seq"));
        Query query = new Query()
                .with(pageable)
                .skip((long) pageable.getPageSize() * pageable.getPageNumber())
                .limit(pageable.getPageSize());

        query.addCriteria(Criteria.where("delYn").is("N"));
        searchCondition(query, dto);
        query.with(Sort.by(Sort.Direction.DESC, "seq"));

        List<MaWordDto> filterData = mongoTemplate.find(query, MaWordDto.class);

        return PageableExecutionUtils.getPage(
                filterData, pageable, () -> mongoTemplate.count(query.skip(-1).limit(-1), MaWordDto.class)
        );
    }

    @Override
    public int countTotalRecords(MaWordDto dto) {
        Query query = new Query();
        query.addCriteria(Criteria.where("delYn").is("N"));
        searchCondition(query, dto);
        return (int) mongoTemplate.count(query, MaWordDto.class);
    }

    public Query searchCondition(Query query, MaWordDto dto) {

        /* 사용여부 */
        if(dto.getSearch1() != null && !"".equals(dto.getSearch1())){
            query.addCriteria(Criteria.where("useYn").is(dto.getSearch1()));
        }
        /* 금칙어 검색 */
        if(dto.getSearchKeyword() != null && !"".equals(dto.getSearchKeyword())) {
            query.addCriteria(Criteria.where("word").regex(dto.getSearchKeyword()));
        }
        return query;
    }


    @Override
    public MaWordDto findOne(MaWordDto dto) {
        return null;
    }

    @Override
    public void insert(MaWordDto dto) {
        MaWordDto maWordDto = new MaWordDto();
        maWordDto.setWord(dto.getWord());
        maWordDto.setUseYn(dto.getUseYn());
        maWordDto.setDelYn("N");
        // TODO 로그인한 아이디로 변경
        maWordDto.setFrstRegrId("admin");
        maWordDto.setFrstRegrDt(new Date());
        mongoTemplate.insert(maWordDto);
    }

    @Override
    public void update(MaWordDto dto) {
        Query query = new Query();
        Update update = new Update();
        query.addCriteria(Criteria.where("seq").is(dto.getSeq()));
        update.set("word", dto.getWord());
        update.set("useYn", dto.getUseYn());
        // TODO 로그인한 아이디로 변경
        update.set("lstChgId", "admin");
        update.set("lstChgDt", new Date());
        mongoTemplate.upsert(query, update, MaWordDto.class);

    }

    @Override
    public void delete(MaWordDto dto) {
        Query query = new Query();
        Update update = new Update();
        query.addCriteria(Criteria.where("seq").is(dto.getSeq()));
        update.set("delYn", dto.getDelYn());
        // TODO 로그인한 아이디로 변경
        update.set("lstChgId", "admin");
        update.set("lstChgDt", new Date());
        mongoTemplate.upsert(query, update, MaWordDto.class);
    }
}
