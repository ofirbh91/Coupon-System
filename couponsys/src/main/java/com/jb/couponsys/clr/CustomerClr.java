package com.jb.couponsys.clr;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.jb.couponsys.bean.Category;
import com.jb.couponsys.bean.Coupon;

import com.jb.couponsys.login.ClientType;
import com.jb.couponsys.login.LoginManager;
import com.jb.couponsys.repository.CouponRepo;
import com.jb.couponsys.service.CustomerService;
import com.jb.couponsys.utils.Table100;

//@Component
@Lazy
@Order(3)
public class CustomerClr implements CommandLineRunner {

	@Autowired
	private CustomerService customerService;
	@Autowired
	private LoginManager loginManager;
	@Autowired
	private CouponRepo couponsRepo;

	@Override
	public void run(String... args) throws Exception {
		String tolenString = loginManager.login("blackbeard@sails.kr", "Unlishell1", ClientType.Customer);
		Coupon p6 = couponsRepo.getOne(1);
		Coupon p7 = couponsRepo.getOne(2);
		Coupon p8 = couponsRepo.getOne(3);
		Coupon p9 = couponsRepo.getOne(4);
		try {
			System.out.println("--------------purchase-coupon---------------");
			customerService.purchaseCoupon(p6);
			customerService.purchaseCoupon(p7);
		
			System.out.println("--------------error---------------");
			customerService.purchaseCoupon(p6);
		} catch (Exception e) {
			System.out.println(e.getMessage());

		}
		System.out.println("--------------get-customer-coupons---------------");
		Table100.print(customerService.getCustomerCoupons());
		System.out.println("--------------get-customer-coupons-by-category---------------");
		Table100.print(customerService.getCustomerCouponsByCategory(Category.gaming));
		System.out.println("--------------get-customer-coupons-by-max-price---------------");
		Table100.print(customerService.getCustomerCoupons(54));
	}

}
