package com.sjht.car.manage.controller.${packageName};

import com.alibaba.fastjson.JSONObject;
import com.sjht.car.manage.feign.${packageName}.${className}FeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/${projectName}/${url}")
public class ${className}Controller {
    @Autowired
    ${className}FeignClient feignClient;

    /**
     * @api {POST} /${projectName}/${url}/delete 删除${tableComment}信息
     * @apiGroup  ${url}
     * @apiVersion 1.0.0
     * @apiDescription 删除${tableComment}信息
     * @apiParam {String} id ${tableComment}主键
     * @apiParamExample {json} 请求样例：
     * {
     * "id":"1234567890"
     * }
     * @apiSuccessExample {json} 成功:
     * {
     *   "code": 0,
     *   "message": "成功",
     * }
     */
    @PostMapping("/delete")
    public ResponseEntity delete(@RequestBody JSONObject params){
        return ResponseEntity.ok(feignClient.delete(params));
    }
    /**
     * @api {POST} /${projectName}/${url}/edit 添加或修改${tableComment}信息
     * @apiGroup  ${url}
     * @apiVersion 1.0.0
     * @apiDescription 添加或修改${tableComment}信息
${addColumnDescs}     * @apiParamExample {json} 请求样例：
     * {
${columnExampleEdits}     * 		"currentUserId": "470901740753911816"
     * }
     * @apiSuccessExample {json} 成功:
     * {
     *     "message": "操作成功",
     *     "code": 0
     * }
     */
    @PostMapping("/edit")
    public ResponseEntity edit(@RequestBody JSONObject params){
        return ResponseEntity.ok(feignClient.edit(params));
    }
    /**
     * @api {POST} /${projectName}/${url}/list 查询${tableComment}信息列表
     * @apiGroup  ${url}
     * @apiVersion 1.0.0
     * @apiDescription 查询${tableComment}列表
     * @apiParam {int} [page] 当前页数
     * @apiParam {int} [pageSize] 每页的记录条数
${selectColumnDescs}     * @apiParam {String} [sortKey] 排序字段
     * @apiParamExample {json} 请求样例：
     * {
     * 		"page":1,
     *  	"pageSize":10,
${columnExampleEdits}     * }
     * @apiSuccessExample {json} 成功:
     * {
     *     "message": "操作成功",
     *     "code": 0,
     *     "data": {
     *         "page": 1,
     *         "pages": 1,
     *         "total": 1,
     *         "list": [
     *             {
${columnExamplesLists}     *             },
     *             ...
     *         ]
     *     }
     * }
     */
    @PostMapping("/list")
    public ResponseEntity list(@RequestBody JSONObject params){
        return ResponseEntity.ok(feignClient.list(params));
    }
    /**
     * @api {POST} /${projectName}/${url}/get 查询${tableComment}信息
     * @apiGroup  ${url}
     * @apiVersion 1.0.0
     * @apiDescription 查询${tableComment}信息
     * @apiParam {String} id ${tableComment}主键
     * @apiParamExample {json} 请求样例：
     * {
     *   "id":492296052171341824
     * }
     * @apiSuccessExample {json} 成功:
     * {
     *     "message": "操作成功",
     *     "code": 0,
     *     "data": {
${columnExamplesGets}     *     }
     * }
     */
    @PostMapping("/get")
    public ResponseEntity get(@RequestBody JSONObject params){
        return ResponseEntity.ok(feignClient.get(params));
    }
}
