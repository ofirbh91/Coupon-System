package com.jb.couponsys.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.jb.couponsys.bean.Coupon;

public interface CouponRepo extends JpaRepository<Coupon, Integer> {

	public List<Coupon> findCouponsByCompanyId(int companyId);

	@Transactional
	@Modifying
	@Query(value = "DELETE FROM couponsys.customers_coupons where coupons_id=:couponID", nativeQuery = true)
	public void deleteCouponPurchase(int couponID);
}
