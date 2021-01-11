package com.jb.couponsys.clr;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.jb.couponsys.bean.Company;

import com.jb.couponsys.bean.Customer;
import com.jb.couponsys.exception.InvalidDetailsException;

import com.jb.couponsys.service.AdminService;

import com.jb.couponsys.utils.Table100;

//@Component
@Lazy
@Order(1)
public class AdminClr implements CommandLineRunner {
	@Autowired
	private AdminService adminService;

	@Override
	public void run(String... args) throws Exception {

		try {
			adminService.login("admin@admin.com", "admin");
		} catch (InvalidDetailsException e) {
			System.out.println(e.getMessage());
		}
		Company c1 = new Company("Isengard operations", "sruman@isen.mo", "rule tham all");
		Company c2 = new Company("nike", "nike@coca.com", "5dS45d83");
		Company c3 = new Company("air jordan", "amj@coca.com", "5M8j545238");
		Company c4 = new Company("coca cola", "coca@coca.com", "585458");
		Company c5 = new Company("parasense productions", "parasense@innerspirit.glob", "123456");
		System.out.println("--------------add-company---------------");
		try {
			adminService.addCompany(c1);
			adminService.addCompany(c2);
			adminService.addCompany(c3);
			adminService.addCompany(c4);
			adminService.addCompany(c5);
			System.out.println("--------------eror---------------");
			adminService.addCompany(c1);
		} catch (InvalidDetailsException e) {
			System.out.println(e.getMessage());
		}
		c1 = adminService.getOneCompany(1);
		c1.setEmail("isen@isen.rule");
		c2 = adminService.getOneCompany(2);
		c2.setName("ddd");
		System.out.println("--------------update-company---------------");
		try {
			adminService.updateCompany(c1);
			System.out.println("--------------eror---------------");
			adminService.updateCompany(c2);
		} catch (InvalidDetailsException e) {
			System.out.println(e.getMessage());
		}
		System.out.println("--------------get-one-company---------------");
		c5 = adminService.getOneCompany(4);
		Table100.print(c5);
		System.out.println("--------------delete-company---------------");
		adminService.deleteCompany(5);
		System.out.println("--------------get-all-companies---------------");
		Table100.print(adminService.getAllCompanies());

		Customer u1 = new Customer("Eadward", "Teach", "blackbeard@sails.kr", "Unlishell1");
		Customer u2 = new Customer("jul", "tiramisu", "sdfsdd@adsfsda.com", "45D4fd5454");
		Customer u3 = new Customer("oli", "merphi", "sd@rt.com", "87g7t7r");
		Customer u4 = new Customer("jojo", "kaplan", "kaplan@ashdod.com", "546654sdf564");
		Customer u5 = new Customer("ilan", "moalem", "timbo@ashdod.com", "54sdf564");

		System.out.println("--------------add-customer---------------");
		try {
			adminService.addCustomer(u1);
			adminService.addCustomer(u2);
			adminService.addCustomer(u3);
			adminService.addCustomer(u4);
			adminService.addCustomer(u5);
			System.out.println("--------------erorr---------------");
			adminService.addCustomer(u1);
		} catch (InvalidDetailsException e) {
			System.out.println(e.getMessage());
		}

		System.out.println("--------------update-customer---------------");
		u1 = adminService.getOneCustomer(1);
		u1.setFirstName("Edward");
		u2 = adminService.getOneCustomer(2);
		u2.setId(0);
		try {
			adminService.updateCustomer(u1);
			System.out.println("--------------erorr---------------");
			adminService.updateCustomer(u2);
		} catch (InvalidDetailsException e) {
			System.out.println(e.getMessage());
		}
		System.out.println("--------------delete-customer---------------");
		adminService.deleteCustomer(5);
		System.out.println("--------------get-one-customer---------------");
		try {
			Table100.print(adminService.getOneCustomer(1));
		} catch (InvalidDetailsException e) {
			System.out.println(e.getMessage());
		}
		System.out.println("--------------get-all-customers---------------");
		Table100.print(adminService.getAllCustomers());
		Customer cu1 = new Customer();
		try {
			cu1 = adminService.getOneCustomer(2);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		cu1.setId(88);
		try {
			adminService.updateCustomer(cu1);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

}
