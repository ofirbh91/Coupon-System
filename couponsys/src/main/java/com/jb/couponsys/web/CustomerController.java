package com.jb.couponsys.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jb.couponsys.bean.Category;
import com.jb.couponsys.bean.Coupon;
import com.jb.couponsys.bean.LoginResponse;
import com.jb.couponsys.login.ClientType;
import com.jb.couponsys.login.LoginManager;
import com.jb.couponsys.login.TokensManager;
import com.jb.couponsys.service.CustomerService;


@RestController
@RequestMapping("customer")
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*",allowCredentials = "true")
public class CustomerController {

	@Autowired
	private LoginManager loginManager;
	@Autowired
	private TokensManager tokensManager;
	  //http://localhost:8080/customer/signup/email/password
		@PostMapping("signup/{email}/{password}")
		public ResponseEntity<?>login(@PathVariable String email , @PathVariable String password){
			try {
				String token = loginManager.login(email, password, ClientType.Customer);
				return new ResponseEntity<>(new LoginResponse(token),HttpStatus.ACCEPTED);
		} catch (Exception e) {
				return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);	
				}
		}
	
	// http://localhost:8080/customer/get-coupons
	@GetMapping("get-coupons")
	public ResponseEntity<?> getCustomerCoupons(@RequestHeader("Authorization") String token) {
		try {
			tokensManager.isTokenExist(token);
			CustomerService customer;
			customer = (CustomerService) tokensManager.GetUserData(token).getClientService();
			return new ResponseEntity<>(customer.getCustomerCoupons(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	// http://localhost:8080/customer/get-all-coupons
		@GetMapping("get-all-coupons")
		public ResponseEntity<?> getAllCoupons(@RequestHeader("Authorization") String token) {
			try {
				tokensManager.isTokenExist(token);
				CustomerService customer;
				customer = (CustomerService) tokensManager.GetUserData(token).getClientService();
				return new ResponseEntity<>(customer.getAllCoupons(), HttpStatus.OK);
			} catch (Exception e) {
				return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
			}
		}

	// http://localhost:8080/customer/get-coupon/{category}
	@GetMapping("get-coupon/{category}")
	public ResponseEntity<?> getCouponsByCategory(@RequestHeader("Authorization") String token, @RequestParam Category category) {
		try {
			tokensManager.isTokenExist(token);
			CustomerService customer;
			customer = (CustomerService) tokensManager.GetUserData(token).getClientService();
			return new ResponseEntity<>(customer.getCustomerCouponsByCategory(category), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	// http://localhost:8080/customer/get-coupon/{price}
	@GetMapping("get-coupon/{price}")
	public ResponseEntity<?> getCouponsByPrice(@RequestHeader("Authorization") String token, @PathVariable Double price) {
		try {
			tokensManager.isTokenExist(token);
			CustomerService customer;
			customer = (CustomerService) tokensManager.GetUserData(token).getClientService();
			return new ResponseEntity<>(customer.getCustomerCoupons(price), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	// http://localhost:8080/customer/purchase-coupon
	@PostMapping("purchase-coupon")
	public ResponseEntity<?> purchasCoupon(@RequestHeader("Authorization") String token, @RequestBody Coupon coupon) {
		try {
			tokensManager.isTokenExist(token);
			CustomerService customer;
			customer = (CustomerService) tokensManager.GetUserData(token).getClientService();
			customer.purchaseCoupon(coupon);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {		
			
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}

	}

	// http://localhost:8080/customer/get-customer-details
	@GetMapping("get-customer-details")
	public ResponseEntity<?> getCustomerDetails(@RequestHeader("Authorization") String token) {
		try {
			tokensManager.isTokenExist(token);
			CustomerService customer;
			customer = (CustomerService) tokensManager.GetUserData(token).getClientService();
			return new ResponseEntity<>(customer.getCustomerDetails(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	
}
