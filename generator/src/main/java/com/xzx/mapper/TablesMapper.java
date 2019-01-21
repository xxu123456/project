package com.xzx.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.xzx.entity.TableEntity;

@Mapper
public interface TablesMapper{
	public List<TableEntity> selectColumnsByTableName(Map params);
}
