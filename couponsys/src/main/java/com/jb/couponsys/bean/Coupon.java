package com.jb.couponsys.bean;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "coupons")
@NoArgsConstructor
@AllArgsConstructor
public class Coupon {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(updatable = false)
	private int id;
	private int companyId;
	@Enumerated(EnumType.STRING)
	private Category category;
	private String title;
	private String decription;
	private Date startDate;
	private Date endDate;
	private int amount;
	private double price;
	private String image;

	public Coupon(int companyId, Category category, String title, String decription, Date startDate, Date endDate,
			int amount, double price, String image) {
		this.companyId = companyId;
		this.category = category;
		this.title = title;
		this.decription = decription;
		this.startDate = startDate;
		this.endDate = endDate;
		this.amount = amount;
		this.price = price;
		this.image = image;
	}

	public Coupon(Category category, String title, String decription, Date startDate, Date endDate, int amount,
			double price, String image) {
		this.category = category;
		this.title = title;
		this.decription = decription;
		this.startDate = startDate;
		this.endDate = endDate;
		this.amount = amount;
		this.price = price;
		this.image = image;
	}

}
