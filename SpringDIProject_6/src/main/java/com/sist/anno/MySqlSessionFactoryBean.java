package com.sist.anno;

import javax.sql.DataSource;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

/*
 * <bean id="ssf" class="org.mybatis.spring.SqlSessionFactoryBean"
		p:dataSource-ref="ds"
		p:configLocation="classpath:Config.xml"
	/>
 */
@Component("ssf")
// @Target(value={TYPE})   => 무조건 클래스 구분 (클래스 위에 설정) = 클래스 구분자
public class MySqlSessionFactoryBean extends SqlSessionFactoryBean{

	/* autowired
	 *  단점 => 같은 유형의 클래스를 주입하면 오류
	 *  		------------------
	 *  		=> @Qualifier : 특정 객체 지정
	 *  			@Autowired + @qualifier = @Resource   ==> jDK1.8까지만 사용
	 *  
	 *  @Target(value={ANNOTATION_TYPE, CONSTRUCTOR, FIELD, METHOD, PARAMETER})
	 *  				-------------	-----------	 -----	------	----------
	 *  	ANNOTATION_TYPE
	 *  		@Autowired
	 *  		@Qualifier
	 *  	
	 *  	CONSTRUCTOR : 생성자
	 *  	FIELD : 멤버변수
	 *  
	 *  	METHOD : 메소드
	 *  	PARAMETER : 매개변수
	 *  
	 *  public class A{
	 *  	@Autowired 	=> FIELD
	 *  	B b;
	 *  
	 *  	@Autowired	=> CONSTRUCTOR
	 *  	public A(B b){
	 *  	}
	 *  	
	 *  	@Autowired	=> METHOD
	 *  	public void setB(B b){
	 *  	}
	 *  }
	 */
	@Autowired  // 스프링에서 자동으로 주입 (객체의 주소값 주입)
	public void setDataSource(DataSource dataSource) {
		// TODO Auto-generated method stub
		super.setDataSource(dataSource);
	}
	
	public MySqlSessionFactoryBean() {
		try {
			Resource res = new ClassPathResource("Config.xml");
			setConfigLocation(res);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
}
