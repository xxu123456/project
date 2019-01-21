package com.xzx.template;
/**
 * controller类模板
 * @author xu
 *
 */
public class ControllerTemplate {
	//controller类模板
	public static final String CLASS_TEMP = "package ${packageName}.controller.manage;\r\n\r\n"+
											"import com.alibaba.fastjson.JSONObject;\r\n"+
											"import com.sjht.park.common.entity.${projectName}.${className};\r\n"+
											"import ${packageName}.service.I${className}Service;\r\n"+
											"import com.sjht.park.common.exception.CustomException;\r\n"+
											"import com.sjht.park.common.util.CustomPageInfo;\r\n"+
											"import com.sjht.park.common.util.HelperUtils;\r\n"+
											"import com.sjht.park.common.model.Response;\r\n"+
											"import org.springframework.beans.factory.annotation.Autowired;\r\n"+
											"import org.springframework.http.ResponseEntity;\r\n"+
											"import org.springframework.web.bind.annotation.PostMapping;\r\n"+
											"import org.springframework.web.bind.annotation.RequestBody;\r\n"+
											"import org.springframework.web.bind.annotation.RequestMapping;\r\n"+
											"import org.springframework.web.bind.annotation.RestController;\r\n\r\n"+	
											"@RestController\r\n"+
											"@RequestMapping(\"/${projectName}/${url}\")\r\n"+
											"public class ${className}Controller {\r\n"+
											"    @Autowired\r\n"+
											"    private I${className}Service ${columnName}Service;\r\n"+
											"    /**\r\n"+
											"     * @api {POST} /${projectName}/${url}/delete 删除${classComment}信息\r\n"+
											"     * @apiGroup  ${url}\r\n"+
											"     * @apiVersion 1.0.0\r\n"+
											"     * @apiDescription 删除${classComment}信息\r\n"+
											"     * @apiParam {String} id ${classComment}信息主键\r\n"+
											"     * @apiParamExample {json} 请求样例：\r\n"+
											"     * {\r\n"+
											"     * \"id\":\"1234567890\"\r\n"+
											"     * }\r\n"+
											"     * @apiSuccessExample {json} 成功:\r\n"+
											"     * {\r\n"+
											"     *   \"code\": 0,\r\n"+
											"     *   \"message\": \"操作成功\",\r\n"+
											"     * }\r\n"+
											"     */\r\n"+
											"    @PostMapping(\"/delete\")\r\n"+
											"    public ResponseEntity delete(@RequestBody JSONObject params){\r\n"+
											"        if(${columnName}Service.deleteByPrimaryKey(params.getString(\"id\"))<1){\r\n"+
											"            throw new CustomException(\"删除失败！\");\r\n"+
											"        }\r\n"+
											"        return Response.okToRE();\r\n"+
											"    }\r\n"+
											"    /**\r\n"+
											"     * @api {POST} /${projectName}/${url}/edit 添加或修改${classComment}信息\r\n"+
											"     * @apiGroup  ${url}\r\n"+
											"     * @apiVersion 1.0.0\r\n"+
											"     * @apiDescription 添加或修改${classComment}信息\r\n"+
											"${addColumnDescs}"+
											"     * @apiParamExample {json} 请求样例：\r\n"+
											"     * {\r\n"+
											"${columnExampleEdits}"+
											"     * 		\"currentUserId\": \"470901740753911816\"\r\n"+
											"     * }\r\n"+
											"     * @apiSuccessExample {json} 成功:\r\n"+
											"     * {\r\n"+
											"     *     \"message\": \"操作成功\",\r\n"+
											"     *     \"code\": 0\r\n"+
											"     * }\r\n"+
											"     */\r\n"+
											"    @PostMapping(\"/edit\")\r\n"+
											"    public ResponseEntity edit(@RequestBody JSONObject params){\r\n"+
											"        ${className} ${columnName} = params.toJavaObject(${className}.class);\r\n"+
											"${lastOpUser}"+
											"        if(${columnName}.getId()==null) {\r\n"+
											"${addUser}"+
											"            if (${columnName}Service.insertSelective(${columnName}) < 1) {\r\n"+
											"                throw new CustomException(\"添加失败！\");\r\n"+
											"            }\r\n"+
											"        }else{\r\n"+
											"            if(${columnName}Service.updateByPrimaryKey(${columnName})<1){\r\n"+
											"                throw new CustomException(\"更新失败！\");\r\n"+
											"            }\r\n"+
											"        }\r\n"+
											"        return Response.okToRE();\r\n"+
											"    }\r\n"+
											"    /**\r\n"+
											"     * @api {POST} /${projectName}/${url}/list 查询${classComment}信息列表\r\n"+
											"     * @apiGroup  ${url}\r\n"+
											"     * @apiVersion 1.0.0\r\n"+
											"     * @apiDescription 查询${classComment}信息列表\r\n"+
											"     * @apiParam {int} [page] 当前页数\r\n"+
											"     * @apiParam {int} [pageSize] 每页的记录条数\r\n"+
											"${selectColumnDescs}"+
											"     * @apiParamExample {json} 请求样例：\r\n"+
											"     * {\r\n"+
											"     *		\"page\":1,\r\n"+
											"     *  	\"pageSize\":10,\r\n"+
											"${columnExampleEdits}"+
											"     * }\r\n"+
											"     * @apiSuccessExample {json} 成功:\r\n"+
											"     * {\r\n"+
											"     *     \"message\": \"操作成功\",\r\n"+
											"     *     \"code\": 0,\r\n"+
											"     *     \"data\": {\r\n"+
											"     *         \"page\": 1,\r\n"+
											"     *         \"pages\": 1,\r\n"+
											"     *         \"total\": 1,\r\n"+
											"     *         \"list\": [\r\n"+
											"     *             {\r\n"+
											"${columnExamplesLists}"+
											"     *             },\r\n"+
											"     *             ...\r\n"+
											"     *         ]\r\n"+
											"     *     }\r\n"+
											"     * }\r\n"+
											"     */\r\n"+
											"    @PostMapping(\"/list\")\r\n"+
											"    public ResponseEntity list(@RequestBody JSONObject params){\r\n"+
											"        CustomPageInfo<${className}> ${columnName} = ${columnName}Service.selectByExample(params);\r\n"+
											"        return Response.okToRE(${columnName});\r\n"+
											"    }\r\n"+
											"    /**\r\n"+
											"     * @api {POST} /${projectName}/${url}/get 查询${classComment}信息\r\n"+
											"     * @apiGroup  ${url}\r\n"+
											"     * @apiVersion 1.0.0\r\n"+
											"     * @apiDescription 查询${classComment}信息\r\n"+
											"     * @apiParam {String} id ${classComment}信息主键\r\n"+
											"     * @apiParamExample {json} 请求样例：\r\n"+
											"     * {\r\n"+
											"     *   \"id\":\"492296052171341824\"\r\n"+
											"     * }\r\n"+
											"     * @apiSuccessExample {json} 成功:\r\n"+
											"     * {\r\n"+
											"     *     \"message\": \"操作成功\",\r\n"+
											"     *     \"code\": 0,\r\n"+
											"     *     \"data\": {\r\n"+
											"${columnExamplesGets}"+
											"     *     }\r\n"+
											"     * }\r\n"+
											"     */\r\n"+
											"    @PostMapping(\"/get\")\r\n"+
											"    public ResponseEntity get(@RequestBody JSONObject params){\r\n"+
											"        ${className} ${columnName} = ${columnName}Service.selectByPrimaryKey(params.getString(\"id\"));\r\n"+
											"        return Response.okToRE(${columnName});\r\n"+
											"    }\r\n"+
											"}\r\n";

	//添加的接口字段说明
	public static final String ADD_COLUMN_DESC = "     * @apiParam {${dateType}} ${columnName} ${columnComment}\r\n";
	
	//查询的接口字段说明
	public static final String SELECT_COLUMN_DESC = "     * @apiParam {${dateType}} [${columnName}] ${columnComment}\r\n";
	
	//查询的接口字段说明
	public static final String COLUMN_EXAMPLE_EDIT = "     * 		\"${columnName}\":${columnComment},\r\n";
	
	//查询的接口字段说明
	public static final String COLUMN_EXAMPLE_LIST = "     *         		\"${columnName}\": ${columnComment}\r\n";
		
	//查询的接口字段说明
	public static final String COLUMN_EXAMPLE_GET = "     *         \"${columnName}\": ${columnComment}\r\n";
	
	//设置最后操作人
	public static final String LAST_OP_USER = "        ${columnName}.setLastOpUser(HelperUtils.getManagePlatformCurrentUserId(params));\r\n";
	//设置添加人
	public static final String ADD_USER = "            ${columnName}.setAddUser(HelperUtils.getManagePlatformCurrentUserId(params));\r\n";
	
	
	
	
	
}
