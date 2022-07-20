package com.goodee.ex05.domain;

public class ProductDTO {

	private Long no;
	private String name;
	private String maker;
	private Integer price;
	
	public ProductDTO() {
	
	}
	public ProductDTO(Long no, String name, String maker, Integer price) {
		super();
		this.no = no;
		this.name = name;
		this.maker = maker;
		this.price = price;
	}
	
	public Long getNo() {
		return no;
	}
	public void setNo(Long no) {
		this.no = no;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMaker() {
		return maker;
	}
	public void setMaker(String maker) {
		this.maker = maker;
	}
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	
}
