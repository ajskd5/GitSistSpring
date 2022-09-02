package com.sist.dao;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartVO {
	private int no, account, total;
	private String name, poster, price;
}
