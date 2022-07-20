package com.goodee.ex11.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.goodee.ex11.domain.Employee;

@Mapper
public interface EmployeeMapper {

	public int selectEmployeeCount();
	public List<Employee> selectEmployees(Map<String, Object> map);
	
	public int selectFindCount(Map<String, Object> map);
	public List<Employee> selectFindList(Map<String, Object> map);
	
	public List<Employee> autoComplete(Map<String, Object> map);
	
}
