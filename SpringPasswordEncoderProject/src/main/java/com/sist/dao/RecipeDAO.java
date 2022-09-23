package com.sist.dao;
import java.util.*;

import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sist.mapper.*;
import com.sist.vo.*;

@Repository
public class RecipeDAO {
	@Autowired
	//@Qualifier("recipeMapper") => 특정 객체
	private RecipeMapper mapper;
	
	// 레시피 리스트
//	@Select("SELECT no, title, poster, chef, num "
//			+ "FROM (SELECT no, title, poster, chef, rownum as num "
//			+ "FROM (SELECT /*+ INDEX_ASC(recipe recipe_no_pk)*/no, title, poster, chef "
//			+ "FROM recipe)) "
//			+ "WHERE num BETWEEN #{start} AND #{end}")
	public List<RecipeVO> recipeListData(Map map){
		return mapper.recipeListData(map);
	}
	//@Select("SELECT CEIL(COUNT(*)/12.0) FROM recipe")
	public int recipeTotalPage() {
		return mapper.recipeTotalPage();
	}
	
	// 레시피 검색 => 동적 쿼리
//	@Select("SELECT no, title, poster, chef, num "
//			+ "FROM (SELECT no, title, poster, chef, rownum as num "
//			+ "FROM (SELECT /*+ INDEX_ASC(recipe recipe_no_pk)*/no, title, poster, chef "
//			+ "FROM recipe WHERE REGEXP_LIKE(title, #{ss}))) "
//			+ "WHERE num BETWEEN #{start} AND #{end}")
	public List<RecipeVO> recipeFindData(Map map){
		return mapper.recipeFindData(map);
	}
//	@Select("SELECT CEIL(COUNT(*)/12.0) FROM recipe WHERE REGEXP_LIKE(title, #{title}")
	public int recipeFindTotalPage(Map map) {
		return mapper.recipeFindTotalPage(map);
	}
	
	
	// 쉐프 리스트
//	@Select("SELECT no, chef, poster, mem_const1, mem_const2, mem_const3, mem_const7, num "
//			+ "FROM (SELECT no, chef, poster, mem_const1, mem_const2, mem_const3, mem_const7, rownum as num "
//			+ "FROM (SELECT /*+ INDEX_ASC(chef chef_no_pk)*/no, chef, poster, mem_const1, mem_const2, mem_const3, mem_const7 "
//			+ "FROM chef)) "
//			+ "WHERE num BETWEEN #{start} AND #{end}")
	public List<ChefVO> chefListData(Map map){
		return mapper.chefListData(map);
	}
//	@Select("SELECT CEIL(COUNT(*)/30.0) FROM chef")
	public int chefTotalPage() {
		return mapper.chefTotalPage();
	}
	
	// 쉐프 레시피 리스트
//	@Select("SELECT no, title, chef, poster, num "
//			+ "FROM (SELECT no, title, chef, poster, rownum as num "
//			+ "FROM (SELECT /*+ INDEX_ASC(recipe recipe_no_pk)*/no, title, chef, poster "
//			+ "FROM recipe WHERE chef=(SELECT chef FROM chef WHERE no=#{no}))) "
//			+ "WHERE num BETWEEN #{start} AND #{end}")
	public List<RecipeVO> chefMakeRecipeData(Map map){
		return mapper.chefMakeRecipeData(map);
	}
//	@Select("SELECT CEIL(COUNT(*)/12.0) FROM recipe "
//			+ "WHERE chef=(SELECT chef FROM chef WHERE no=#{no})")
	public int chefMakeTotalPage(Map map) {
		return mapper.chefMakeTotalPage(map);
	}
	
	// 쉐프 레시피 검색
//	@Select("SELECT no, title, chef, poster, num "
//			+ "FROM (SELECT no, title, chef, poster, rownum as num "
//			+ "FROM (SELECT /*+ INDEX_ASC(recipe recipe_no_pk)*/no, title, chef, poster "
//			+ "FROM recipe WHERE chef=(SELECT chef FROM chef WHERE no=#{no}) AND REGEXP_LIKE(title, #{ss}))) "
//			+ "WHERE num BETWEEN #{start} AND #{end}")
	public List<RecipeVO> chefMakeRecipeFindData(Map map){
		return mapper.chefMakeRecipeFindData(map);
	}
//	@Select("SELECT CEIL(COUNT(*)/12.0) FROM recipe "
//			+ "WHERE chef=(SELECT chef FROM chef WHERE no=#{no}) AND REGEXP_LIKE(title, #{ss})")
	public int chefMakeFindTotalPage(Map map) {
		return mapper.chefMakeFindTotalPage(map);
	}
	
}
