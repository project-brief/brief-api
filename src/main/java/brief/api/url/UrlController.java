package brief.api.url;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import wcyoung.spring.mvc.bind.annotation.JsonRequestMapping;
import wcyoung.spring.mvc.common.base.BaseController;

@JsonRequestMapping(value = "/url")
@RestController
public class UrlController extends BaseController {

    @Resource
    private UrlService urlService;

    @GetMapping(value = "/{short_url}")
    public ResponseEntity<Map<String, Object>> selectUrlInfo(@PathVariable String short_url) {
        Map<String, Object> body = null;

        try {
            Map<String, Object> param = new HashMap<>();
            param.put("short_url", short_url);
            body = urlService.selectUrlInfo(param);
        } catch (Exception e) {
            log.error("Exception: {}", ExceptionUtils.getStackTrace(e));
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(body, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> insertUrl(@RequestBody Map<String, Object> param) {
        Map<String, Object> body = null;

        try {
            body = urlService.insertUrl(param);

            if (body == null) {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (Exception e) {
            log.error("Exception: {}", ExceptionUtils.getStackTrace(e));
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(body, HttpStatus.CREATED);
    }

    @PatchMapping(value = "/{short_url}")
    public ResponseEntity<Map<String, Object>> updateRedirectCount(@PathVariable String short_url) {
        try {
            Map<String, Object> param = new HashMap<>();
            param.put("short_url", short_url);
            if (!urlService.updateRedirectCount(param)) {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (Exception e) {
            log.error("Exception: {}", ExceptionUtils.getStackTrace(e));
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
