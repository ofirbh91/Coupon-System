package com.jb.couponsys.clr;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.jb.couponsys.bean.Category;
import com.jb.couponsys.bean.Coupon;

import com.jb.couponsys.exception.InvalidDetailsException;
import com.jb.couponsys.login.ClientType;
import com.jb.couponsys.login.LoginManager;

import com.jb.couponsys.service.CompanyService;
import com.jb.couponsys.utils.Table100;

//@Component
@Lazy
@Order(2)
public class CompanyClr implements CommandLineRunner {

	@Autowired
	private CompanyService companiesService;
	@Autowired
	private LoginManager loginManager;

	@Override
	public void run(String... args) throws Exception {
		
		String tokenString = loginManager.login("nike@coca.com", "5dS45d83", ClientType.Company);

		Coupon p1 = new Coupon(Category.gaming, "cooling pad", "quality artifact", Date.valueOf("2018-08-22"),
				Date.valueOf("2021-08-23"), 5, 55.5, "SDfsd");
		Coupon p2 = new Coupon(Category.resturant, "flora", "farfora", Date.valueOf("2005-02-28"),
				Date.valueOf("2021-08-23"), 5, 54, "dsdf");
		Coupon p3 = new Coupon(Category.food, "ice cream", "choclate", Date.valueOf("2015-05-28"),
				Date.valueOf("2021-12-23"), 7, 50, "dsdf");
		Coupon p4 = new Coupon(Category.electronics, "leptop", "good leptop", Date.valueOf("2014-02-10"),
				Date.valueOf("2021-04-08"), 3, 60, "dsdf");
		Coupon p5 = new Coupon(Category.cloths, "shirt", "good quality", Date.valueOf("2014-02-10"),
				Date.valueOf("2021-04-08"), 5, 60, "dsdf");

		System.out.println("--------------add-coupon---------------");
		try {
			companiesService.addCoupon(p1);
			companiesService.addCoupon(p2);
			companiesService.addCoupon(p3);
			companiesService.addCoupon(p4);
			companiesService.addCoupon(p5);
			System.out.println("--------------error---------------");
			companiesService.addCoupon(p1);
		} catch (InvalidDetailsException e) {
			System.out.println(e.getMessage());
		}

		try {
			p2 = companiesService.getOneCoupon(2);
			p3 = companiesService.getOneCoupon(3);
		} catch (InvalidDetailsException e) {
			System.out.println(e.getMessage());
		}
		p2.setDecription("Charlie and a half");
		p3.setEndDate(Date.valueOf("2014-12-11"));
		System.out.println("--------------update-coupon---------------");
		try {
			companiesService.updateCoupon(p2);
			System.out.println("--------------error---------------");
			companiesService.updateCoupon(p3);
		} catch (InvalidDetailsException e) {
			System.out.println(e.getMessage());
		}
		System.out.println("--------------delete-coupon---------------");
		Table100.print(companiesService.getCompanyCoupons());
		companiesService.deleteCoupon(5);
		System.out.println("--------------get-all-coupons---------------");
		Table100.print(companiesService.getCompanyCoupons());
		System.out.println("--------------get-coupons-by-category---------------");
		Table100.print(companiesService.getCompanyCouponByCategory(Category.gaming));

		System.out.println("--------------get-coupons-by-mac-price---------------");
		Table100.print(companiesService.getCompanyCouponByPrice(54.0));
		Table100.print(companiesService.getCompanyDetails());

	}

}
