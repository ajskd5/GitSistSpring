package com.sist.dao;
import java.util.*;

import org.apache.ibatis.annotations.Select;
public interface EmpMapper {
	@Select("SELECT empno, ename, job, hiredate, sal, deptno FROM emp")
	public List<EmpVO> empListData();
	// 		resultType		ID		parameterType
	
	// JPA => SQL문장 없이 자동 구현
	// public List<EmpVO> findAll();
	
	@Select("SELECT * FROM emp WHERE empno=#{empno}")
	public EmpVO empDetailData(int empno);
	
}
