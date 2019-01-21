package com.sjht.car.manage.feign.${packageName};

import com.alibaba.fastjson.JSONObject;
import com.sjht.car.common.util.Response;
import com.sjht.car.manage.feign.${packageName}.fallback.${className}Hystrix;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


@FeignClient(value = "${projectName}",fallback= ${className}Hystrix.class)
public interface ${className}FeignClient {
    @RequestMapping("/${packageName}/${url}/delete")
    public Response delete(@RequestBody JSONObject params);

    @RequestMapping("/${packageName}/${url}/edit")
    public Response edit(@RequestBody JSONObject params);

    @RequestMapping("/${packageName}/${url}/list")
    public Response list(@RequestBody JSONObject params);

    @RequestMapping("/${packageName}/${url}/get")
    public Response get(@RequestBody JSONObject params);
}
