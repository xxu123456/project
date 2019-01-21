package ${packageName}.config;

import com.sjht.car.common.exception.CustomExceptionHandle;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 异常处理配置
 */
@Configuration
public class ExceptionHandleConfig {
    @Bean
    public CustomExceptionHandle customExceptionHandle() {
        return new CustomExceptionHandle();
    }
}
