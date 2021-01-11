package com.jb.couponsys.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jb.couponsys.exception.InvalidDetailsException;
import com.jb.couponsys.repository.CompanyRepo;
import com.jb.couponsys.repository.CouponRepo;
import com.jb.couponsys.repository.CustomerRepo;

@Service
public abstract class ClientService {

	@Autowired
	protected CompanyRepo companyRepo;
	@Autowired
	protected CustomerRepo customerRepo;
	@Autowired
	protected CouponRepo couponRepo;

	public abstract boolean login(String email, String password) throws InvalidDetailsException;
}
