package com.wdjr.mybatis.dao;

import com.wdjr.mybatis.bean.Department;

public interface DepartmentMapper {

	public Department getDeptById(Integer id);
	//���ݲ��Ų���ڴ˲����µ�����Ա��
	public Department getDeptByIdPlus(Integer id);
	//�ֲ���ѯ
	public Department getDeptByIdStep(Integer id);
}
