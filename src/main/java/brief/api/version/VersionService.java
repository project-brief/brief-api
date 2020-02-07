package brief.api.version;

import java.io.IOException;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import brief.bean.ApplicationInitializer;
import wcyoung.spring.mvc.common.base.BaseService;

@Service
public class VersionService extends BaseService {

    @Resource
    private ApplicationInitializer applicationInitializer;

    public String getManifestVersion() throws IOException {
        return applicationInitializer.getManifestInfo("Implementation-Version");
    }

}
