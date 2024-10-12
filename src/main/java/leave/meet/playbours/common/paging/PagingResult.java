package leave.meet.playbours.common.paging;

import lombok.Builder;
import lombok.Data;
import org.apache.ibatis.type.Alias;
import org.springframework.data.domain.Pageable;

@Builder
@Data
@Alias("pagingResult")
public class PagingResult<T>{

    private T data;
    private Pageable pageable;
}
