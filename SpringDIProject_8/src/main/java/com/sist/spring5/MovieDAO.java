package com.sist.spring5;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.*;
// SqlSessionDaoSupport => SqlSessionFactory (MyBatis (Spring))
@Repository("dao")
public class MovieDAO extends SqlSessionDaoSupport{
	
	@Autowired
	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		// TODO Auto-generated method stub
		super.setSqlSessionFactory(sqlSessionFactory);
	}

	//<select id="movieListData" resultType="MovieVO">
	public List<MovieVO> movieListData(){
		return getSqlSession().selectList("movieListData");
	}
	
	//<select id="movieFindData" resultType="MovieVO" parameterType="hashmap">
	public List<MovieVO> movieFindData(Map map){
		return getSqlSession().selectList("movieFindData", map);
	}
}
