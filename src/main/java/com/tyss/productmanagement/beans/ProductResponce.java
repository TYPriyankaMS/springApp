package com.tyss.productmanagement.beans;

import java.util.List;

import lombok.Data;

@Data
public class ProductResponce {
	private int statusCode;
	private String message;
	private String description;
	private List<Product> products;
}
