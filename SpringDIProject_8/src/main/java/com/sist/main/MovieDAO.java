package com.sist.main;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import java.util.*;
// SqlSessionDaoSupport => SqlSessionFactory (MyBatis (Spring))

public class MovieDAO extends SqlSessionDaoSupport{
	//<select id="movieListData" resultType="MovieVO">
	public List<MovieVO> movieListData(){
		return getSqlSession().selectList("movieListData");
	}
	
	//<select id="movieFindData" resultType="MovieVO" parameterType="hashmap">
	public List<MovieVO> movieFindData(Map map){
		return getSqlSession().selectList("movieFindData", map);
	}
}
