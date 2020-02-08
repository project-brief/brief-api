package brief.api.url;

import java.io.UnsupportedEncodingException;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.collections4.MapUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import brief.mapper.brief.UrlMapper;
import wcyoung.spring.mvc.common.base.BaseService;

@Service
public class UrlService extends BaseService {

    @Resource
    private UrlMapper urlMapper;

    public Map<String, Object> selectUrlInfo(Map<String, Object> param) {
        return urlMapper.selectUrlInfo(param);
    }

    public Map<String, Object> insertUrl(Map<String, Object> param) throws UnsupportedEncodingException {
        if (urlMapper.insertUrl(param) < 1) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return null;
        }

        String shortUrl = Base64.encodeBase64URLSafeString(MapUtils.getString(param, "sq").getBytes("UTF-8"));
        param.put("short_url", shortUrl);

        if (urlMapper.updateShortUrl(param) < 1) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return null;
        }

        return selectUrlInfo(param);
    }

    public boolean updateRedirectCount(Map<String, Object> param) {
        if (urlMapper.updateRedirectCount(param) < 1) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return false;
        }

        return true;
    }

}
