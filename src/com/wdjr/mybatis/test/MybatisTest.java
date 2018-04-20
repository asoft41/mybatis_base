package com.wdjr.mybatis.test;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import com.wdjr.mybatis.bean.Employee;


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
}
