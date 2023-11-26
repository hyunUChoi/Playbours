package leave.meet.playbours.manage.support.board.repository.impl;

import leave.meet.playbours.manage.support.board.repository.MaBoardRepository;
import leave.meet.playbours.manage.support.board.service.MaBoardDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.CriteriaDefinition;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public class MaBoardRepositoryImpl implements MaBoardRepository {

    private final MongoTemplate mongoTemplate;

    public MaBoardRepositoryImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public Page<MaBoardDto> findByPagingAndFiltering(int page, int size, MaBoardDto dto, String boardDivn) {
        Pageable pageable = PageRequest.of(page == 0 ? 0 : page - 1, size, Sort.by("seq"));
        Query query = new Query()
                .with(pageable)
                .skip((long) pageable.getPageSize() * pageable.getPageNumber())
                .limit(pageable.getPageSize());

        query.addCriteria(Criteria.where("boardDivn").is(boardDivn));
        query.addCriteria(Criteria.where("delYn").is("N"));
        query.addCriteria(searchCondition(dto));
        query.with(Sort.by(Sort.Direction.DESC, "seq"));

        List<MaBoardDto> filterData = mongoTemplate.find(query, MaBoardDto.class);

        return PageableExecutionUtils.getPage(
                filterData, pageable, () -> mongoTemplate.count(query.skip(-1).limit(-1), MaBoardDto.class)
        );
    }

    @Override
    public int countTotalRecords(MaBoardDto dto, String boardDivn) {
        Query query = new Query();
        query.addCriteria(Criteria.where("boardDivn").is(boardDivn));
        query.addCriteria(Criteria.where("delYn").is("N"));
        query.addCriteria(searchCondition(dto));
        return (int) mongoTemplate.count(query, MaBoardDto.class);
    }

    /* 검색 조건문 */
    public Criteria searchCondition(MaBoardDto dto) {
        Criteria criteria = new Criteria();

        if(dto.getSearch1() != null && !"".equals(dto.getSearch1() )) {
            if("1".equals(dto.getSearch1())) {
                criteria = Criteria.where("reply").is(null);
            } else {
                criteria = Criteria.where("reply").ne(null);
            }
        }

        if(dto.getSearchKeyword() != null && !"".equals(dto.getSearchKeyword())) {
            if(dto.getSearchOption() != null && !"".equals(dto.getSearchOption())) {
                switch (dto.getSearchOption()) {
                    case "0" -> {
                        criteria = criteria.orOperator(Criteria.where("title").is(dto.getSearchKeyword()), Criteria.where("cont").is(dto.getSearchKeyword()));
                    }
                    case "1" -> {
                        criteria = Criteria.where("title").is(dto.getSearchKeyword());
                    }
                    case "2" -> {
                        criteria = Criteria.where("cont").is(dto.getSearchKeyword());
                    }
                }
            }
        }

        return criteria;
    }

    @Override
    public MaBoardDto findOne(MaBoardDto dto) {
        Query query = new Query();
        query.addCriteria(Criteria.where("seq").is(dto.getSeq()));
        query.addCriteria(Criteria.where("delYn").is("N"));
        return mongoTemplate.findOne(query, MaBoardDto.class);
    }

    @Override
    public void insert(MaBoardDto dto, String boardDivn) {
        MaBoardDto boardDto = new MaBoardDto();
        boardDto.setBoardDivn(boardDivn);
        boardDto.setTitle(dto.getTitle());
        boardDto.setUseYn(dto.getUseYn());
        boardDto.setCont(dto.getCont());
        boardDto.setAtchFile(dto.getAtchFile());
        boardDto.setViewCnt(dto.getViewCnt());
        // TODO 로그인한 아이디로 변경
        boardDto.setFrstRegrId("admin");
        boardDto.setFrstRegrDt(new Date());
        boardDto.setDelYn(dto.getDelYn());
        mongoTemplate.insert(boardDto);
    }

    @Override
    public void update(MaBoardDto dto) {
        Query query = new Query();
        Update update = new Update();

        query.addCriteria(Criteria.where("seq").is(dto.getSeq()));
        update.set("title", dto.getTitle());
        update.set("cont", dto.getCont());
        update.set("useYn", dto.getUseYn());
        update.set("atchFile", dto.getAtchFile());
        // TODO 로그인한 아이디로 변경
        update.set("lstChgId", "admin");
        update.set("lstChgDt", new Date());
    }

    @Override
    public void updateReply(MaBoardDto dto) {
        Query query = new Query();
        Update update = new Update();

        query.addCriteria(Criteria.where("seq").is(dto.getSeq()));
        update.set("reply", dto.getReply());
        mongoTemplate.upsert(query, update, MaBoardDto.class);
    }

    @Override
    public void delete(MaBoardDto dto) {
        Query query = new Query();
        Update update = new Update();

        query.addCriteria(Criteria.where("seq").is(dto.getSeq()));
        // TODO 로그인한 아이디로 변경
        update.set("lstChgId", "admin");
        update.set("lstChgDt", new Date());
        update.set("delYn", "Y");
        mongoTemplate.upsert(query, update, MaBoardDto.class);
    }

    @Override
    public void deleteReply(MaBoardDto dto) {
        Query query = new Query();
        Update update = new Update();

        query.addCriteria(Criteria.where("seq").is(dto.getSeq()));
        update.set("reply", "");
        mongoTemplate.upsert(query, update, MaBoardDto.class);
    }
}
