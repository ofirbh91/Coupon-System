package com.jb.couponsys.bean;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "companies")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Company {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(updatable = false)
	private int id;
	private String name;
	private String email;
	private String password;
	@OneToMany(cascade = { CascadeType.REMOVE,
			CascadeType.PERSIST }, orphanRemoval = true, fetch = FetchType.EAGER, mappedBy = "companyId")
	private List<Coupon> coupons = new ArrayList<>();

	public Company(String name, String email, String password) {
		this.name = name;
		this.email = email;
		this.password = password;
	}

	public Company(int id, String name, String email, String password) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
	}
}
