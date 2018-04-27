package com.wdjr.mybatis.test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import com.wdjr.mybatis.bean.Department;
import com.wdjr.mybatis.bean.Employee;
import com.wdjr.mybatis.dao.DepartmentMapper;
import com.wdjr.mybatis.dao.EmployeeMapper;
import com.wdjr.mybatis.dao.EmployeeMapperAnnotation;
import com.wdjr.mybatis.dao.EmployeeMapperPlus;

/**
 * 1.�ӿ�ʽ���
 * ԭ����                Dao ==>DaoImp
 * 
 * mybatis  Mapper ==>xxMapper.xml 
 * 2.SqlSession��������ݿ�һ���ԻỰ������ر�
 * 
 * 3.SqlSession��connectionһ�����Ƿ��̰߳�ȫ��ÿ������Ӧ�û�ȡ�µĶ��󣬲�Ӧ�ù���ĳ�Ա������
 * 4.mapper�ӿ�û��ʵ���࣬����mybatis��Ϊ����ӿ�����һ���������
 * (���ӿں�xml��)
 * 5.������Ҫ�������ļ�
 *   mybatis��ȫ�������ļ��������ݿ�����ӳ���Ϣ�����������Ϣ ��ϵͳ����������Ϣ
 *   sqlӳ���ļ���������ÿһ��sql����ӳ����Ϣ
 * 
 * @author Lion.Lee
 *
 * @data 2018��4��20�� ����4:10:28
 */

public class MybatisTest {

	
	/**
	 * 1.����xml�ļ����ã�����sqlSessionFactory����
	 * 2.��ȡsqlSessionʵ����ִ���Ѿ�ӳ���sql���
	 * 3.ʹ��sql��Ψһ��ʶ������Mybatis��ִ���Ǿ�sql
	 * @throws IOException
	 */
	@Test
	public void test() throws IOException {
		String resource = "mybatis-config.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		SqlSessionFactory sqlSessionFactory =new SqlSessionFactoryBuilder().build(inputStream);
		
		// Ψһ��ʶ��Unique identifier matching the statement to use.
		//sql��������A parameter object to pass to the statement.
		SqlSession openSession = sqlSessionFactory.openSession();
		try {
			Employee emp = openSession.selectOne("com.wdjr.mybatis.EmployeeMapper.selectEmp",1);
			System.out.println(emp);
		} finally {
			// �ر�Session
			openSession.close();
		}	
	}
	
	public SqlSessionFactory getSqlSessionFactory() throws IOException{
		
		String resource = "mybatis-config.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		return new SqlSessionFactoryBuilder().build(inputStream);
	}
		
	@Test
	public void test01() throws IOException {
		//1.��ȡsqlSessionFactory����
		SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
		
		//2.��ȡsqlSession����
		SqlSession openSession = sqlSessionFactory.openSession();
		
		//3.��ȡ�ӿڵ�ʵ�������
		//4.��Ϊ�ӿ��Զ��Ĵ���������󣬴������ȥִ�� ��ɾ�Ĳ�
		EmployeeMapper mapper = openSession.getMapper(EmployeeMapper.class);
		
		Employee employee = mapper.getEmpById(1);
		
		System.out.println(mapper.getClass());
		
		System.out.println(employee);
	}
	
	@Test
	public void test02() throws IOException {
		//1.��ȡsqlSessionFactory����
		SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
		
		//2.��ȡsqlSession����
		SqlSession openSession = sqlSessionFactory.openSession();
		//ע�����ע�����
		EmployeeMapperAnnotation mapper = openSession.getMapper(EmployeeMapperAnnotation.class);
		Employee emp = mapper.getEmpById(1);
		System.out.println(emp);
		
	}
	/**
	 * ��ɾ�Ĳ����
	 * ������ɾ�������������͵ķ���ֵ
	 * 	Integer Long Boolean
	 * SqlSession openSession = sqlSessionFactory.openSession(true);==>�Զ��ύ
	 * @throws IOException
	 */
	@Test
	public void test03() throws IOException {
		//1.��ȡsqlSessionFactory����
		SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
		
		//2.��ȡsqlSession����(��ȡ���Ĳ����Զ��ύ����)
		SqlSession openSession = sqlSessionFactory.openSession();
		try {
			
			EmployeeMapper mapper = openSession.getMapper(EmployeeMapper.class);
			/*
			//1.���Ա��
			Employee emp = new Employee(null,"test01","test01@163.com","0");
			mapper.addEmp(emp);
			System.out.println(emp.getId());*/
			//2.�޸�Ա��
//			Employee emp = new Employee(1,"LionLee","jerry@163.com","1");
//			Boolean updateEmp = mapper.updateEmp(emp);
//			System.out.println(updateEmp);
			//3.ɾ��
			//mapper.deletEmpById(3);
			//�ֶ��ύ����
//			Map<String, Object> empByIdReturnMap = mapper.getEmpByIdReturnMap(1);
//			System.out.println(empByIdReturnMap);
			Map<Integer, Employee> empByLastNameLikeReturnMap = mapper.getEmpByLastNameLikeReturnMap("%r%");
			
			System.out.println(empByLastNameLikeReturnMap);
			openSession.commit();
		} finally {
			openSession.close();
		}
		
		
	}
	@Test
	public void test04() throws IOException {
		//1.��ȡsqlSessionFactory����
		SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
		
		//2.��ȡsqlSession����(��ȡ���Ĳ����Զ��ύ����)
		SqlSession openSession = sqlSessionFactory.openSession();
		try {
			
			EmployeeMapperPlus mapper = openSession.getMapper(EmployeeMapperPlus.class);
//			Employee emp = mapper.getEmpById(1);
			Employee empAndDept = mapper.getEmpAndDept(1);
			System.out.println(empAndDept);
			System.out.println(empAndDept.getDept());
			
			openSession.commit();
		}finally {
			openSession.close();
		}
	}
	@Test
	public void test05() throws IOException {
		SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
		
		//2.��ȡsqlSession����(��ȡ���Ĳ����Զ��ύ����)
		SqlSession openSession = sqlSessionFactory.openSession();
		try {
			DepartmentMapper mapper = openSession.getMapper(DepartmentMapper.class);
			Department dept = mapper.getDeptById(1);
			
			System.out.println(dept);
		} finally {
			openSession.close();
		}
	}
	
	@Test
	public void test06() throws IOException {
		
		SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
		
		//2.��ȡsqlSession����(��ȡ���Ĳ����Զ��ύ����)
		SqlSession openSession = sqlSessionFactory.openSession();
		try {
			EmployeeMapperPlus mapper = openSession.getMapper(EmployeeMapperPlus.class);
			Employee emp = mapper.getEmpByIdStep(1);
			System.out.println("++++++++");
		
		} finally {
			openSession.close();
		}
	}
	@Test
	public void test07() throws IOException{
		SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
		
		//2.��ȡsqlSession����(��ȡ���Ĳ����Զ��ύ����)
		SqlSession openSession = sqlSessionFactory.openSession();
		try {
			DepartmentMapper mapper = openSession.getMapper(DepartmentMapper.class);
			Department dept = mapper.getDeptByIdPlus(1);
			System.out.println(dept);
			
		} finally {
			openSession.close();
		}
	}
	@Test
	public void test08() throws IOException {
		SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
		
		//2.��ȡsqlSession����(��ȡ���Ĳ����Զ��ύ����)
		SqlSession openSession = sqlSessionFactory.openSession();
		try {
			DepartmentMapper mapper = openSession.getMapper(DepartmentMapper.class);
			Department dept = mapper.getDeptByIdStep(1);
			System.out.println(dept);
			
		} finally {
			openSession.close();
		}
	}
	
}
