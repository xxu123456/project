package com.xzx.utils;

import java.util.List;

import com.xzx.entity.ColumnEntity;
import com.xzx.entity.TableEntity;
import com.xzx.template.ApplicatinTemplate;
import com.xzx.template.ControllerTemplate;
import com.xzx.template.EntityTemplate;
import com.xzx.template.MapperTemplate;
import com.xzx.template.ServiceImplTemplate;
import com.xzx.template.ServiceTemplate;

public class ClassFactory {
	/**
	 * 生成实体类
	 * @param Table
	 * @param projectName
	 * @return
	 */
	public static String createEntityClass(TableEntity Table,String projectName,String packageName){
		String classCode = EntityTemplate.CLASS_TEMP;
		classCode = classCode.replace("${projectName}",projectName);
		classCode = classCode.replace("${className}", NameUtils.getMethodName(Table.getTableName()));
		classCode = classCode.replace("${classComment}",Table.getTableComment());
		StringBuffer columnCodes = new StringBuffer();
		StringBuffer methodCodes = new StringBuffer();
		StringBuffer imports = new StringBuffer();
		for(ColumnEntity column:Table.getColumns()){
			//初始化属性
			String columnCode = EntityTemplate.COLUMN_TEMP;
			if("PRI".equals(column.getColumnKey())){
				columnCode = "	@Id\r\n"+columnCode;
			}
			columnCode = columnCode.replace("${dataType}", DataTypeUtils.getType(column.getDataType().toUpperCase()));
			columnCode = columnCode.replace("${columnName}", NameUtils.getColumnName(column.getColumnName()));
			columnCode = columnCode.replace("${columnComment}",column.getColumnComment());
			columnCodes.append(columnCode);
			if(imports.indexOf(DataTypeUtils.getImport(column.getDataType().toUpperCase()))==-1){
				imports.append(DataTypeUtils.getImport(column.getDataType().toUpperCase()));
			}
			
			//初始化方法
			String methodCode = EntityTemplate.METHOD_TEMP;
			methodCode = methodCode.replace("${dataType}", DataTypeUtils.getType(column.getDataType().toUpperCase()));
			methodCode = methodCode.replace("${columnName}", NameUtils.getColumnName(column.getColumnName()));
			methodCode = methodCode.replace("${methodName}", NameUtils.getMethodName(column.getColumnName()));
			methodCodes.append(methodCode);
			
		}
		classCode = classCode.replace("${columns}", columnCodes);
		classCode = classCode.replace("${methods}", methodCodes);
		classCode = classCode.replace("${imports}", imports);
		FileUtiles.createFile(classCode,"entity/"+projectName,NameUtils.getMethodName(Table.getTableName())+".java");
		return classCode;
	}
	/**
	 * 生成mapper接口
	 * @param Table
	 * @param projectName
	 * @return
	 */
	public static String createMapperClass(TableEntity Table,String projectName,String packageName){
		String classCode = MapperTemplate.CLASS_TEMP;
		classCode = classCode.replace("${packageName}",packageName).replace("${projectName}",projectName);
		classCode=classCode.replace("${className}", NameUtils.getMethodName(Table.getTableName()));
		String path = projectName+"/src/main/java/"+packageName.replace(".","/")+"/mapper";
		FileUtiles.createFile(classCode,path,NameUtils.getMethodName(Table.getTableName())+"Mapper.java");
		return classCode;
	}
	/**
	 * 生成service接口
	 * @param Table
	 * @param projectName
	 * @return
	 */
	public static String createServiceClass(TableEntity Table,String projectName,String packageName){
		String classCode = ServiceTemplate.CLASS_TEMP;
		classCode = classCode.replace("${packageName}",packageName).replace("${projectName}",projectName);
		classCode=classCode.replace("${className}", NameUtils.getMethodName(Table.getTableName()));
		classCode=classCode.replace("${columnName}", NameUtils.getColumnName(Table.getTableName()));
		String path = projectName+"/src/main/java/"+packageName.replace(".","/")+"/service";
		FileUtiles.createFile(classCode,path,"I"+NameUtils.getMethodName(Table.getTableName())+"Service.java");
		return classCode;
	}
	
	/**
	 * 生成serviceImpl实现类
	 * @param Table
	 * @param projectName
	 * @return
	 */
	public static String createServiceImplClass(TableEntity Table,String projectName,String packageName){
		String classCode = ServiceImplTemplate.CLASS_TEMP;
		if(NameUtils.hasColumn(Table,"add_time")){
			classCode = classCode.replace("${addTime}",ServiceImplTemplate.ADD_TIME);
		}else{
			classCode = classCode.replace("${addTime}","");
		}
		if(NameUtils.hasColumn(Table,"last_op_time")){
			classCode = classCode.replace("${lastOpTime}",ServiceImplTemplate.LAST_OP_TIME);
		}else{
			classCode = classCode.replace("${lastOpTime}","");
		}
		if(NameUtils.hasColumn(Table,"status")){
			classCode = classCode.replace("${delete}",ServiceImplTemplate.DEL_STATUS);
			classCode = classCode.replace("${setStatus}",ServiceImplTemplate.SET_STATUS);
			classCode = classCode.replace("${notStatus}",ServiceImplTemplate.NOT_STATUS);
		}else{
			classCode = classCode.replace("${delete}",ServiceImplTemplate.DELETE);
			classCode = classCode.replace("${setStatus}","");
			classCode = classCode.replace("${notStatus}","");
		}
		StringBuffer notNulls = new StringBuffer();
		List<ColumnEntity> columns = Table.getColumns();
		for(ColumnEntity column:columns){
			if(column.getColumnName().equalsIgnoreCase("status")) continue;
			if(column.getColumnName().equalsIgnoreCase("id")) continue;
			if(column.getColumnName().contains("time")) continue;
			if(column.getColumnName().contains("user")) continue;
			if(column.getColumnName().contains("remark")) continue;
			String notNull = ServiceImplTemplate.NOT_NULL;
			notNull = notNull.replace("${mehtodName}", NameUtils.getMethodName(column.getColumnName())).replace("${columnComment}", column.getColumnComment());
			notNulls.append(notNull);
		}
		classCode = classCode.replace("${packageName}",packageName).replace("${projectName}",projectName);
		classCode = classCode.replace("${noyNull}",notNulls).replace("${className}", NameUtils.getMethodName(Table.getTableName())).
				replace("${columnName}",NameUtils.getColumnName(Table.getTableName())).replace("${tableComment}", Table.getTableComment());
		String path = projectName+"/src/main/java/"+packageName.replace(".","/")+"/service/impl";
		FileUtiles.createFile(classCode,path,NameUtils.getMethodName(Table.getTableName())+"ServiceImpl.java");
		return classCode;
	}
	
	/**
	 * 生成controller类
	 * @param Table
	 * @param projectName
	 * @return
	 */
	public static String createContrllerClass(TableEntity table,String projectName,String packageName){
		String classCode = ControllerTemplate.CLASS_TEMP;
		classCode = classCode.replace("${packageName}",packageName);
		classCode=classCode.replace("${className}", NameUtils.getMethodName(table.getTableName()));
		//classCode=classCode.replace("${projectName}", projectName.replace("_","-"));
		classCode=classCode.replace("${projectName}", packageName.substring(packageName.lastIndexOf(".")+1).replace("_", "-"));
		classCode=classCode.replace("${url}",NameUtils.getColumnName(table.getTableName()));
		if(NameUtils.hasColumn(table,"add_user")){
			classCode = classCode.replace("${addUser}",ControllerTemplate.ADD_USER);
		}else{
			classCode = classCode.replace("${addUser}","");
		}
		if(NameUtils.hasColumn(table,"last_op_user")){
			classCode = classCode.replace("${lastOpUser}",ControllerTemplate.LAST_OP_USER);
		}else{
			classCode = classCode.replace("${lastOpUser}","");
		}
		classCode=classCode.replace("${columnName}", NameUtils.getColumnName(table.getTableName()));
		classCode=classCode.replace("${classComment}",table.getTableComment());
		List<ColumnEntity> columns = table.getColumns();
		StringBuffer addColumnDescs = new StringBuffer();
		StringBuffer selectColumnDescs = new StringBuffer();
		StringBuffer columnExampleEdits = new StringBuffer();
		StringBuffer columnExampleLists = new StringBuffer();
		StringBuffer columnExampleGets = new StringBuffer();
		for(ColumnEntity column:columns){
			String addColumnDesc = ControllerTemplate.ADD_COLUMN_DESC;
			String selectColumnDesc = ControllerTemplate.SELECT_COLUMN_DESC;
			String columnExampleEdit = ControllerTemplate.COLUMN_EXAMPLE_EDIT;
			String columnExampleList = ControllerTemplate.COLUMN_EXAMPLE_LIST;
			String columnExampleGet = ControllerTemplate.COLUMN_EXAMPLE_GET;
			String type = DataTypeUtils.getType(column.getDataType().toUpperCase());
			Boolean isType = "Integer".equals(type)||"Float".equals(type)||"Double".equals(type)||"BigDecimal".equals(type)||"Boolean".equals(type);
			type = isType ?type:"String";
			String columnName = column.getColumnName();
			String columnComment = column.getColumnComment();
			addColumnDesc = addColumnDesc.replace("${dateType}", type)
					.replace("${columnName}", NameUtils.getColumnName(columnName))
					.replace("${columnComment}",columnComment);
			
			selectColumnDesc = selectColumnDesc.replace("${dateType}", type)
					.replace("${columnName}", NameUtils.getColumnName(columnName))
					.replace("${columnComment}",columnComment);
			columnComment = isType?columnComment:"\""+columnComment+"\"";
			columnExampleEdit = columnExampleEdit.replace("${columnName}", NameUtils.getColumnName(columnName))
					.replace("${columnComment}",columnComment);
			
			columnExampleList = columnExampleList.replace("${columnName}", NameUtils.getColumnName(columnName))
					.replace("${columnComment}",columnComment);
			
			columnExampleGet = columnExampleGet.replace("${columnName}", NameUtils.getColumnName(columnName))
					.replace("${columnComment}",columnComment);
			
			addColumnDescs.append(addColumnDesc);
			selectColumnDescs.append(selectColumnDesc);
			columnExampleEdits.append(columnExampleEdit);
			columnExampleLists.append(columnExampleList);
			columnExampleGets.append(columnExampleGet);
		}
		
		classCode=classCode.replace("${addColumnDescs}",addColumnDescs);
		classCode=classCode.replace("${selectColumnDescs}",selectColumnDescs);
		classCode=classCode.replace("${columnExampleEdits}", columnExampleEdits);
		classCode=classCode.replace("${columnExamplesLists}",columnExampleLists);
		classCode=classCode.replace("${columnExamplesGets}",columnExampleLists);
		String path = projectName+"/src/main/java/"+packageName.replace(".","/")+"/controller/manage";
		FileUtiles.createFile(classCode,path,NameUtils.getMethodName(table.getTableName())+"Controller.java");
		return classCode;
	}
	/**
	 * 生成启动类
	 * @param projectName
	 * @param packageName
	 */
	public static void createApplicationClass(String projectName, String packageName) {
		String classCode = ApplicatinTemplate.CLASS_TEMP;
		String classNme = packageName.substring(packageName.lastIndexOf(".")+1);
		classCode = classCode.replace("${packageName}", packageName).replace("${className}", NameUtils.getMethodName(classNme));
		String path = projectName+"/src/main/java/"+packageName.replace(".","/");
		FileUtiles.createFile(classCode,path,NameUtils.getMethodName(classNme)+"Application.java");
	}
	
	public static void createManage(TableEntity table,String projectName,String packageName){
		String tableName = table.getTableName();
		String tableComment = table.getTableComment();
		String name = packageName.substring(packageName.lastIndexOf(".")+1);
		String feign = FileUtiles.readFile("ManageFeignClient.java").replace("${projectName}", projectName).replace("${packageName}", name)
				.replace("${className}",NameUtils.getMethodName(tableName)).replace("${url}", tableName.replace("_","-"));
		FileUtiles.createFile(feign,"manage/feign/"+name,NameUtils.getMethodName(tableName)+"FeignClient.java");
		String hystrix = FileUtiles.readFile("ManageHystrix.java").replace("${packageName}", name).replace("${className}",NameUtils.getMethodName(tableName)).replace("${tableComment}", tableComment);
		FileUtiles.createFile(hystrix,"manage/feign/"+name+"/fallback",NameUtils.getMethodName(tableName)+"Hystrix.java");
		String controller = FileUtiles.readFile("ManageController.java").replace("${packageName}", name).replace("${className}",NameUtils.getMethodName(tableName))
				.replace("${tableComment}", tableComment).replace("${url}", tableName.replace("_","-")).replace("${projectName}", packageName.substring(packageName.lastIndexOf(".")+1).replace("_", "-"));
		
		
		List<ColumnEntity> columns = table.getColumns();
		StringBuffer addColumnDescs = new StringBuffer();
		StringBuffer selectColumnDescs = new StringBuffer();
		StringBuffer columnExampleEdits = new StringBuffer();
		StringBuffer columnExampleLists = new StringBuffer();
		StringBuffer columnExampleGets = new StringBuffer();
		for(ColumnEntity column:columns){
			String addColumnDesc = ControllerTemplate.ADD_COLUMN_DESC;
			String selectColumnDesc = ControllerTemplate.SELECT_COLUMN_DESC;
			String columnExampleEdit = ControllerTemplate.COLUMN_EXAMPLE_EDIT;
			String columnExampleList = ControllerTemplate.COLUMN_EXAMPLE_LIST;
			String columnExampleGet = ControllerTemplate.COLUMN_EXAMPLE_GET;
			String type = DataTypeUtils.getType(column.getDataType().toUpperCase());
			Boolean isType = "Integer".equals(type)||"Float".equals(type)||"Double".equals(type)||"BigDecimal".equals(type)||"Boolean".equals(type);
			type = isType ?type:"String";
			String columnName = column.getColumnName();
			String columnComment = column.getColumnComment();
			addColumnDesc = addColumnDesc.replace("${dateType}", type)
					.replace("${columnName}", NameUtils.getColumnName(columnName))
					.replace("${columnComment}",columnComment);
			
			selectColumnDesc = selectColumnDesc.replace("${dateType}", type)
					.replace("${columnName}", NameUtils.getColumnName(columnName))
					.replace("${columnComment}",columnComment);
			columnComment = isType?columnComment:"\""+columnComment+"\"";
			columnExampleEdit = columnExampleEdit.replace("${columnName}", NameUtils.getColumnName(columnName))
					.replace("${columnComment}",columnComment);
			
			columnExampleList = columnExampleList.replace("${columnName}", NameUtils.getColumnName(columnName))
					.replace("${columnComment}",columnComment);
			
			columnExampleGet = columnExampleGet.replace("${columnName}", NameUtils.getColumnName(columnName))
					.replace("${columnComment}",columnComment);
			
			addColumnDescs.append(addColumnDesc);
			selectColumnDescs.append(selectColumnDesc);
			columnExampleEdits.append(columnExampleEdit);
			columnExampleLists.append(columnExampleList);
			columnExampleGets.append(columnExampleGet);
		}
		controller=controller.replace("${addColumnDescs}",addColumnDescs);
		controller=controller.replace("${selectColumnDescs}",selectColumnDescs);
		controller=controller.replace("${columnExampleEdits}", columnExampleEdits);
		controller=controller.replace("${columnExamplesLists}",columnExampleLists);
		controller=controller.replace("${columnExamplesGets}",columnExampleLists);	
		FileUtiles.createFile(controller,"manage/controller/"+name,NameUtils.getMethodName(tableName)+"Controller.java");
		
	}
}
