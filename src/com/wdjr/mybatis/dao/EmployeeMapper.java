package com.wdjr.mybatis.dao;


import java.util.Map;

import org.apache.ibatis.annotations.MapKey;

import com.wdjr.mybatis.bean.Employee;

public interface EmployeeMapper {

	//������¼��װmap:Map<Integer ,Employee> :��ʱ��¼��������ֵ�Ǽ�¼���javaBean
	//����mybatis��װ���map��ʹ���ĸ�������Ϊmap��key
	@MapKey("id")
	public Map<Integer,Employee> getEmpByLastNameLikeReturnMap(String lastName);
	
	public Map<String,Object> getEmpByIdReturnMap(Integer id);
	public void addEmp(Employee emp);
	public Boolean deletEmpById(Integer id);
	public Boolean updateEmp(Employee emp);
	public Employee getEmpById(Integer id);
	
	public Employee getEmpByIdAndLastName(Integer id,String LastName);

	
}
