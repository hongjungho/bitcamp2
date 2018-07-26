package challenge.dao;

import java.util.List;
import java.util.Map;

import challenge.domain.Timeline;

public interface TimelineDao {
    int delete(int no);
    List<Timeline> selectList();
    int insert(Timeline timeline);
    int update(Timeline timeline);
    Timeline selectOne(int no);
    int timelineLikeCount(int no);
    int timelineLikeCheck(Map<String,Object> params);
    int timelineLike(Map<String,Object> params);
    int timelineLikeCancle(Map<String,Object> params);
}
