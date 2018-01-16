package com.itenjoy.vo;

import java.sql.Timestamp;

public class BuyVO {

	private long buy_id;
	private String buyer;
	private int com_id;
	private String com_title;
	private int buy_price;
	private byte buy_count;
	private String com_image;
	private Timestamp buy_date;
	private String deliveryName;
	private String deliveryTel;
	private String deliveryAddress;
	private String memo;
	private String sanction;

	public long getBuy_id() {
		return buy_id;
	}

	public void setBuy_id(long buy_id) {
		this.buy_id = buy_id;
	}

	public String getBuyer() {
		return buyer;
	}

	public void setBuyer(String buyer) {
		this.buyer = buyer;
	}

	public int getCom_id() {
		return com_id;
	}

	public void setCom_id(int com_id) {
		this.com_id = com_id;
	}

	public String getCom_title() {
		return com_title;
	}

	public void setCom_title(String com_title) {
		this.com_title = com_title;
	}

	public int getBuy_price() {
		return buy_price;
	}

	public void setBuy_price(int buy_price) {
		this.buy_price = buy_price;
	}

	public byte getBuy_count() {
		return buy_count;
	}

	public void setBuy_count(byte buy_count) {
		this.buy_count = buy_count;
	}

	public String getCom_image() {
		return com_image;
	}

	public void setCom_image(String com_image) {
		this.com_image = com_image;
	}

	public Timestamp getBuy_date() {
		return buy_date;
	}

	public void setBuy_date(Timestamp buy_date) {
		this.buy_date = buy_date;
	}

	public String getDeliveryName() {
		return deliveryName;
	}

	public void setDeliveryName(String deliveryName) {
		this.deliveryName = deliveryName;
	}

	public String getDeliveryTel() {
		return deliveryTel;
	}

	public void setDeliveryTel(String deliveryTel) {
		this.deliveryTel = deliveryTel;
	}

	public String getDeliveryAddress() {
		return deliveryAddress;
	}

	public void setDeliveryAddress(String deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getSanction() {
		return sanction;
	}

	public void setSanction(String sanction) {
		this.sanction = sanction;
	}

}
