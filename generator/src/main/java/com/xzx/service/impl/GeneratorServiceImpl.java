package com.xzx.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.xzx.entity.ColumnEntity;
import com.xzx.entity.TableEntity;
import com.xzx.mapper.TablesMapper;
import com.xzx.service.IGeneratorService;
import com.xzx.utils.ClassFactory;
import com.xzx.utils.EnvironmentFactory;

@Service
public class GeneratorServiceImpl implements IGeneratorService{
	
	@Autowired
	private TablesMapper tablesMapper;
	@Value("${spring.datasource.url}")
	private String url;
	@Value("${projectName}")
	private String projectName;
	@Value("${packageName}")
	private String packageName;
	@Value("${tableNames}")
	private String tableNames;
	@Value("${port}")
	private String port;
	
	public void generator() {
		Map<String,Object> params = new HashMap();
		params.put("tableSchema", url.substring(url.lastIndexOf("/")+1));
		List<String> list = new ArrayList<String>();
		if(tableNames!=null&&!"".equals(tableNames)){
			list = Arrays.asList(tableNames.split(","));
		}
		params.put("tableNames", list);
		List<TableEntity> tables = tablesMapper.selectColumnsByTableName(params);
		for(TableEntity table : tables){
			ClassFactory.createEntityClass(table,projectName,packageName);
			ClassFactory.createMapperClass(table,projectName,packageName);
			ClassFactory.createServiceClass(table,projectName,packageName);
			ClassFactory.createServiceImplClass(table,projectName,packageName);
			ClassFactory.createContrllerClass(table,projectName,packageName);
			//ClassFactory.createManage(table,projectName,packageName);
		}
		ClassFactory.createApplicationClass(projectName,packageName);
		EnvironmentFactory.createEnvironment(projectName, packageName,port);
	}

}
