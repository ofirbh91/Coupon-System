package com.jb.couponsys.thread;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.jb.couponsys.bean.Coupon;
import com.jb.couponsys.repository.CouponRepo;

@Component
@Order(2)
public class DailyExpiredCouponTask {

	@Autowired
	CouponRepo couponsRepo;

	@Scheduled(fixedRate = 1000 * 60 * 60 * 24)
	public void delteExpiredCoupons() {
		List<Coupon> coupons = couponsRepo.findAll();
		List<Coupon> expiredCoupons = new ArrayList<>();
		for (Coupon coupon : coupons) {
			if (coupon.getEndDate().before(Date.valueOf(LocalDate.now()))) {
				expiredCoupons.add(coupon);

			}
		}
		if (expiredCoupons.size() > 0) {
			for (Coupon coupon : expiredCoupons) {
				couponsRepo.deleteCouponPurchase(coupon.getId());
				couponsRepo.delete(coupon);
				System.out.println("Expired coupon-" + coupon.getTitle() + " has been deleted");
			}
		} else {
			System.out.println("There was no expired coupon to delete.");
		}
	}
}
