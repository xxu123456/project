package ${packageName}.feign;

import ${packageName}.feign.config.Retry;
import ${packageName}.feign.fallback.SnowflakeHystrix;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;


@FeignClient(value = "car-service-snowflake",configuration = Retry.class, fallback= SnowflakeHystrix.class)
public interface SnowflakeFeignClient {
    //获取全局唯一id
    @GetMapping("/getId")
    Long getId();


    @GetMapping("/getIds/{num}")
    List<Long> getIds(@PathVariable("num") Integer num);
}
