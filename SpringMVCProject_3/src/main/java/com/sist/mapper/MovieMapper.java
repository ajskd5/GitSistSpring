package com.sist.mapper;
import java.util.*;
import org.apache.ibatis.annotations.Select;
import com.sist.dao.MovieVO;

public interface MovieMapper {
	@Select("SELECT * FROM project_movie")
	public List<MovieVO> movieListData();
	
	@Select("SELECT * FROM project_movie WHERE mno=#{mno}")
	public MovieVO movieDetailData(int mno); // 매개변수는 반드시 하나만 사용 가능
}
