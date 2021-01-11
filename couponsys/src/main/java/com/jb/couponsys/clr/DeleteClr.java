package com.jb.couponsys.clr;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.jb.couponsys.bean.Coupon;
import com.jb.couponsys.login.ClientType;
import com.jb.couponsys.login.LoginManager;
import com.jb.couponsys.repository.CouponRepo;
import com.jb.couponsys.service.AdminService;
import com.jb.couponsys.service.CompanyService;
import com.jb.couponsys.service.CustomerService;

//@Component
@Order(4)
public class DeleteClr implements CommandLineRunner{

	@Autowired
	private CompanyService companiesService;
	@Autowired
	private AdminService adminService;
	@Autowired
	private CustomerService customerService;
	@Autowired
	private LoginManager loginManager;
	@Autowired
	private CouponRepo couponsRepo;
	
	
	
	@Override
	public void run(String... args) throws Exception {
		
//		companiesService = (CompaniesService) loginManager.login("nike@coca.com", "5ds45d8", ClientType.Company);
//		Coupon p1 = companiesService.getOneCoupon(1);
//		companiesService.deleteCoupon(p1.getId());
//		adminService.deleteCustomer(1);
//		adminService.deleteCompany(2);
	}

}
