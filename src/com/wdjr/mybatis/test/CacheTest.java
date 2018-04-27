package com.wdjr.mybatis.test;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import com.wdjr.mybatis.bean.Employee;
import com.wdjr.mybatis.dao.EmployeeMapper;

public class CacheTest {

	public SqlSessionFactory getSqlSessionFactory() throws IOException{
		
		String resource = "mybatis-config.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		return new SqlSessionFactoryBuilder().build(inputStream);
	}
	/**
	 * ��������
	 * һ�����棨���ػ��棩
	 * 	         �����ݿ�ͬһ�λỰ�ڼ��ѯ�������ݻ�ŵ����ػ�����
	 *     ����Ժ�����ͬ������ֱ�Ӵӻ������ã�û��Ҫ��ѯ���ݿ�
	 * �������棨ȫ�ֻ��棩
	 * @throws IOException 
	 */
	@Test
	public void testFirstLevelCache() throws IOException {
		
		SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
		SqlSession openSession = sqlSessionFactory.openSession();
		try {
			EmployeeMapper mapper = openSession.getMapper(EmployeeMapper.class);
			Employee emp = mapper.getEmpById(1);
			openSession.clearCache();
			System.out.println(emp);
			Employee emp2 = mapper.getEmpById(1);
			System.out.println(emp2);
		} finally {
			openSession.close();
		}
	}
	@Test
	public void testSecondLevelCache() throws IOException {
		
		SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
		SqlSession openSession = sqlSessionFactory.openSession();
		SqlSession openSession2 = sqlSessionFactory.openSession();
		try {
			EmployeeMapper mapper = openSession.getMapper(EmployeeMapper.class);
			EmployeeMapper mapper2 = openSession2.getMapper(EmployeeMapper.class);
			Employee emp = mapper.getEmpById(1);
			System.out.println(emp);
			openSession.close();
			Employee emp2 = mapper2.getEmpById(1);
			System.out.println(emp2);
			openSession2.close();	
		} finally {
			
		}
	}
}
