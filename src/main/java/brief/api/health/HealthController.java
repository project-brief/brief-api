package brief.api.health;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import wcyoung.spring.mvc.bind.annotation.JsonRequestMapping;
import wcyoung.spring.mvc.common.base.BaseController;

@JsonRequestMapping(value = "/health")
@RestController
public class HealthController extends BaseController {

    @GetMapping(value = "/check")
    public ResponseEntity<String> checkHealth() {
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
