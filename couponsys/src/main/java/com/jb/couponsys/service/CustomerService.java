package com.jb.couponsys.service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.jb.couponsys.bean.Category;
import com.jb.couponsys.bean.Coupon;
import com.jb.couponsys.bean.Customer;
import com.jb.couponsys.exception.ExpiredException;
import com.jb.couponsys.exception.InvalidDetailsException;
import com.jb.couponsys.exception.NotInStockException;

@Service
@Scope("prototype")
public class CustomerService extends ClientService {
	private int customerId;

	public CustomerService() {
		super();
	}

	@Override
	public boolean login(String email, String password) throws InvalidDetailsException {
		List<Customer> customers = customerRepo.findAll();
		for (Customer customer : customers) {
			if (customer.getEmail().equals(email) && customer.getPassword().equals(password)) {
				this.customerId = customer.getId();
				return true;
			}
		}

		return false;
	}

	public void setCustomerID(int customerID) {
		this.customerId = customerID;
	}

	public Customer getCustomerDetails() {
		return customerRepo.getOne(customerId);
	}

	public void purchaseCoupon(Coupon coupon) throws InvalidDetailsException, NotInStockException, ExpiredException {
		for (Coupon coupon2 : customerRepo.getOne(customerId).getCoupons()) {
			if (coupon2.getId() == coupon.getId()) {
				throw new InvalidDetailsException("you already own " + coupon.getTitle() + " coupon");
			}
		}
		if (coupon.getAmount() == 0 || coupon.getEndDate().before(Date.valueOf(LocalDate.now().toString()))) {
			throw new NotInStockException("This coupon is out of stock or expired");
		} else {

			coupon.setAmount(coupon.getAmount() - 1);
			couponRepo.saveAndFlush(coupon);
			Customer customer = customerRepo.getOne(customerId);
			customer.getCoupons().add(coupon);
			customerRepo.saveAndFlush(customer);
		}
	}

	public List<Coupon> getCustomerCoupons() {

		return customerRepo.getOne(customerId).getCoupons();
	}

	public List<Coupon> getCustomerCouponsByCategory(Category category) {
		return getCustomerCoupons().stream().filter(p -> p.getCategory() == (category)).collect(Collectors.toList());
	}

	public List<Coupon> getCustomerCoupons(double price) {
		return getCustomerCoupons().stream().filter(p -> p.getPrice() <= price).collect(Collectors.toList());
	}

	public List<Coupon> getAllCoupons() {
		List<Coupon> allCoupons = couponRepo.findAll();
		return allCoupons;
	}
}
