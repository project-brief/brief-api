package brief.mapper.brief;

import java.util.Map;

public interface UrlMapper {

    Map<String, Object> selectUrlInfo(Map<String, Object> param);

    int insertUrl(Map<String, Object> param);

    int updateShortUrl(Map<String, Object> param);

    int updateRedirectCount(Map<String, Object> param);

}
