package leave.meet.playbours.manage.support.board.repository.impl;

import leave.meet.playbours.manage.support.board.repository.MaBoardRepository;
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

        /* 쿼리 조건 /*
        /*if(maBoardDto.getSearch1() != null && !"".equals(maBoardDto.getSearch1() )) {
            query.addCriteria(Criteria.where("menuClCd").is(maBoardDto.getSearch1()));
        }*/

        /*if(maBoardDto.getSearchKeyword() != null && !"".equals(maBoardDto.getSearchKeyword())) {
            if(maBoardDto.getSearchOption() != null && !"".equals(maBoardDto.getSearchOption())) {
                switch (maBoardDto.getSearchOption()) {
                    case "0" -> {
                        Criteria criteria = new Criteria();
                        criteria.orOperator(Criteria.where("menuCd").is(maBoardDto.getSearchKeyword()), Criteria.where("menuNm").is(maBoardDto.getSearchKeyword()));
                        query.addCriteria(criteria);
                    }
                    case "1" -> {
                        query.addCriteria(Criteria.where("menuNm").is(maBoardDto.getSearchKeyword()));
                    }
                    case "2" -> {
                        query.addCriteria(Criteria.where("menuCd").is(maBoardDto.getSearchKeyword()));
                    }
                }
            }
        }*/

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
        return (int) mongoTemplate.count(query, MaBoardDto.class);
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
        boardDto.setFrstRegrId("admin");
        boardDto.setFrstRegrDt(new Date());
        boardDto.setDelYn(dto.getDelYn());
        mongoTemplate.insert(boardDto);
    }

    @Override
    public void delete(MaBoardDto dto) {
        Query query = new Query();
        Update update = new Update();

        query.addCriteria(Criteria.where("seq").is(dto.getSeq()));
        update.set("lstChgId", "admin");
        update.set("lstChgDt", new Date());
        update.set("delYn", "Y");
        mongoTemplate.upsert(query, update, MaBoardDto.class);
    }
}
