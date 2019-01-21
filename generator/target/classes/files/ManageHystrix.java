package com.sjht.car.manage.feign.${packageName}.fallback;

import com.alibaba.fastjson.JSONObject;
import com.sjht.car.common.util.Response;
import com.sjht.car.manage.feign.${packageName}.${className}FeignClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class ${className}Hystrix implements ${className}FeignClient {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public Response delete(JSONObject params) {
        return new Response().setCode(105).setMessage("删除${tableComment}失败[服务错误]");
    }

    @Override
    public Response edit(JSONObject params) {
        return new Response().setCode(105).setMessage("编辑${tableComment}失败[服务错误]");
    }

    @Override
    public Response list(JSONObject params) {
        return new Response().setCode(105).setMessage("查询${tableComment}列表失败[服务错误]");
    }

    @Override
    public Response get(JSONObject params) {
        return new Response().setCode(105).setMessage("查询${tableComment}详情失败[服务错误]");
    }
}
