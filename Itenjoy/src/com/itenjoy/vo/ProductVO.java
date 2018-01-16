package com.itenjoy.vo;

import java.sql.Timestamp;

public class ProductVO {
	
	private int com_id;
	private String com_kind;
	private String com_title;
	private int com_price;
	private short com_count;
	private String manufacture_com;
	private String manufacture_date;
	private String com_image;
	private String com_content;
	private Timestamp reg_date;

	public int getCom_id() {
		return com_id;
	}

	public void setCom_id(int com_id) {
		this.com_id = com_id;
	}

	public String getCom_kind() {
		return com_kind;
	}

	public void setCom_kind(String com_kind) {
		this.com_kind = com_kind;
	}

	public String getCom_title() {
		return com_title;
	}

	public void setCom_title(String com_title) {
		this.com_title = com_title;
	}

	public int getCom_price() {
		return com_price;
	}

	public void setCom_price(int com_price) {
		this.com_price = com_price;
	}

	public short getCom_count() {
		return com_count;
	}

	public void setCom_count(short com_count) {
		this.com_count = com_count;
	}

	public String getManufacture_com() {
		return manufacture_com;
	}

	public void setManufacture_com(String manufacture_com) {
		this.manufacture_com = manufacture_com;
	}

	public String getManufacture_date() {
		return manufacture_date;
	}

	public void setManufacture_date(String manufacture_date) {
		this.manufacture_date = manufacture_date;
	}

	public String getCom_image() {
		return com_image;
	}

	public void setCom_image(String com_image) {
		this.com_image = com_image;
	}

	public String getCom_content() {
		return com_content;
	}

	public void setCom_content(String com_content) {
		this.com_content = com_content;
	}

	public Timestamp getReg_date() {
		return reg_date;
	}

	public void setReg_date(Timestamp reg_date) {
		this.reg_date = reg_date;
	}
}
