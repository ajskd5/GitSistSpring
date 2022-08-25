package com.sist.main;
// 실제 서버 ==> 221.63.89.131
public class MovieDAO {
	private DataBase db;
	public DataBase getDb() {
		return db;
	}
	public void setDb(DataBase db) {
		this.db = db;
	}

	public void movieListData() {
		db.getConnection();
		System.out.println("영화 목록 가져오기...");
		db.disConnection();
	}
	
	public void movieDetailData() {
		db.getConnection();
		System.out.println("영화 상세보기 데이터 가져오기...");
		db.disConnection();
	}
	
	public void movieFindData() {
		db.getConnection();
		System.out.println("영화 검색 데이터 가져오기...");
		db.disConnection();
	}
}
