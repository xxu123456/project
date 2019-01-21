package ${packageName}.config;

import com.sjht.car.common.json.MyJsonConvert;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 自定义json转换器，格式化时间，转换长度大于16的long型数据为String
 */
@Configuration
public class JsonConvertConfig {

    @Bean
    public MyJsonConvert jsonConvert() {
        return new MyJsonConvert();
    }
}
