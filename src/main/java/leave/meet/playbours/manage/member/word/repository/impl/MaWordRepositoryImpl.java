package leave.meet.playbours.manage.member.word.repository.impl;

import leave.meet.playbours.manage.member.user.dto.MaUserDto;
import leave.meet.playbours.manage.member.word.dto.MaWordDto;
import leave.meet.playbours.manage.member.word.repository.MaWordRepository;
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

        /* 검색조건 */
        if(dto.getSearchKeyword() != null && !"".equals(dto.getSearchKeyword())) {
            if(dto.getSearchOption() != null && !"".equals(dto.getSearchOption())) {
                Criteria criteria = new Criteria();
                criteria = criteria.orOperator(Criteria.where("name").regex(dto.getSearchKeyword()), Criteria.where("userId").regex( dto.getSearchKeyword()));
                query.addCriteria(criteria);
            }
        }
        return query;
    }


    @Override
    public MaWordDto findOne(MaWordDto dto) {
        return null;
    }

    @Override
    public void insert(MaWordDto dto) {

    }

    @Override
    public void update(MaWordDto dto) {

    }

    @Override
    public void delete(MaWordDto dto) {

    }

    @Override
    public int countById(String id) {
        return 0;
    }
}
