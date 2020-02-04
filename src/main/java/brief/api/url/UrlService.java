package brief.api.url;

import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.collections4.MapUtils;
import org.springframework.stereotype.Service;

import brief.mapper.brief.UrlMapper;
import wcyoung.spring.mvc.common.base.BaseService;

@Service
public class UrlService extends BaseService {

    @Resource
    private UrlMapper urlMapper;

    public Map<String, Object> selectOriginalUrl(Map<String, Object> param) {
        return urlMapper.selectOriginalUrl(param);
    }

    public Map<String, Object> insertUrl(Map<String, Object> param) throws Exception {
        if (urlMapper.insertUrl(param) < 1) {
            return null;
        }

        String shortUrl = Base64.encodeBase64URLSafeString(MapUtils.getString(param, "sq").getBytes("UTF-8"));
        param.put("short_url", shortUrl);

        if (urlMapper.updateShortUrl(param) < 1) {
            return null;
        }

        return selectOriginalUrl(param);
    }

}
