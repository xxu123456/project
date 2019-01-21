package ${packageName}.feign.fallback;

import ${packageName}.feign.SnowflakeFeignClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SnowflakeHystrix implements SnowflakeFeignClient {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Override
    public Long getId() {
        logger.error("获取全局id失败");
        return null;
    }

    @Override
    public List<Long> getIds(Integer num) {
        logger.error("获取全局ids失败");
        return null;
    }
}
