package com.sist.temp;

public interface DataBase {
	public void getConnection();
	public void disConnection();
	public void selectOne();
	public void selectList();
	// 구현된 메소드 추가 (유지보수)
	public default void insert() {}
}
