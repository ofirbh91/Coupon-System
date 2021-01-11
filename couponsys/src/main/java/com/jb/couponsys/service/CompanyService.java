package com.jb.couponsys.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.jb.couponsys.bean.Category;
import com.jb.couponsys.bean.Company;
import com.jb.couponsys.bean.Coupon;
import com.jb.couponsys.exception.InvalidDetailsException;
import com.jb.couponsys.exception.NotInStockException;
import com.jb.couponsys.utils.Table100;

@Service
@Scope("prototype")
public class CompanyService extends ClientService {
	private int companyID = 0;

	public CompanyService() {
		super();

	}

	@Override
	public boolean login(String email, String password) throws InvalidDetailsException {
		List<Company> companies = companyRepo.findAll();
		for (Company company : companies) {
			if (company.getEmail().equals(email) && company.getPassword().equals(password)) {
				this.companyID = company.getId();
				return true;
			}
		}

		return false;
	}
	public int getCompanyID() {
		return this.companyID;
	}
	
	public Company getCompanyDetails() {
		return companyRepo.getOne(companyID);

	}
	
	public void setCompanyID(int companyID) {
		this.companyID = companyID; 
	}

	public void addCoupon(Coupon coupon) throws InvalidDetailsException {
		if (coupon.getStartDate().after(coupon.getEndDate())) {
			throw new InvalidDetailsException("A coupon can't have a start date the comes after its end date");
		}
		List<Coupon> coupons = getCompanyCoupons();
		for (Coupon coupon2 : coupons) {
			if (coupon2.getTitle().equals(coupon.getTitle())) {
				throw new InvalidDetailsException("This title already exists");
			}

		}
		coupon.setCompanyId(companyID);

		couponRepo.save(coupon);
	}

	public void updateCoupon(Coupon coupon) throws InvalidDetailsException {
		if (coupon.getStartDate().after(coupon.getEndDate())) {
			throw new InvalidDetailsException("A coupon can't have a start date the comes after its end date");
		}

		couponRepo.saveAndFlush(coupon);
	}

	public void deleteCoupon(int couponID) throws NotInStockException {
		List<Coupon> coupons = getCompanyCoupons();
		for (Coupon coupon : coupons) {
			if (coupon.getId() == couponID) {
				Coupon p1 = couponRepo.getOne(couponID);
				couponRepo.deleteCouponPurchase(couponID);
				couponRepo.delete(p1);
			}
		}
	}

	public Coupon getOneCoupon(int couponID) throws InvalidDetailsException {
		return couponRepo.getOne(couponID);

	}

	
	public List<Coupon> getOneForTheWeb(int couponID) throws InvalidDetailsException {
		return getCompanyCoupons().stream().filter(p -> p.getId() == (couponID)).collect(Collectors.toList());
	}

	public List<Coupon> getCompanyCoupons() {
		return couponRepo.findCouponsByCompanyId(companyID);

	}

	public List<Coupon> getCompanyCouponByCategory(Category category) {
		
		List<Coupon> coups = getCompanyCoupons();
		List<Coupon> coupons = new ArrayList<>();
		for (Coupon coup : coups) {
			if (coup.getCategory() == category) {
				coupons.add(coup);
			}
		}

		return coupons;

	}

	public List<Coupon> getCompanyCouponByPrice(double price) {
		return getCompanyCoupons().stream().filter(p -> p.getPrice() <= price).collect(Collectors.toList());
	}
}
