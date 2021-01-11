package com.jb.couponsys.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jb.couponsys.bean.Category;
import com.jb.couponsys.bean.Company;
import com.jb.couponsys.bean.Customer;
import com.jb.couponsys.bean.LoginResponse;
import com.jb.couponsys.login.ClientType;
import com.jb.couponsys.login.LoginManager;
import com.jb.couponsys.login.TokensManager;
import com.jb.couponsys.service.AdminService;

@RestController
@RequestMapping("admin")
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*",allowCredentials = "true")
public class AdminController {
	@Autowired
	private AdminService admin;
	@Autowired
	private LoginManager loginManager;
	@Autowired
	private TokensManager tokensManager;

	// http://localhost:8080/admin/signup/email/password
	@PostMapping("signup/{email}/{password}")
	public ResponseEntity<?>login(@PathVariable String email,@PathVariable String password ){
		try {
			String token = loginManager.login(email, password, ClientType.Admin);
			return new ResponseEntity<>(new LoginResponse(token), HttpStatus.ACCEPTED);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_ACCEPTABLE);
		}
	}

	// http://localhost:8080/admin/add-customer
	@PostMapping("add-customer")
	public ResponseEntity<?> addCustomer(@RequestHeader("Authorization") String token, @RequestBody Customer customer) {
		try {
			tokensManager.isTokenExist(token);
			this.admin.addCustomer(customer);
			return new ResponseEntity<>(customer.getId(), HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	// http://localhost:8080/admin/get-customers
	@GetMapping("get-customers")
	public ResponseEntity<?> getAllCustomer(@RequestHeader("Authorization") String token) {
		try {
			tokensManager.isTokenExist(token);		
			List<Customer> customers = this.admin.getAllCustomers();
			return new ResponseEntity<>(customers, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	// http://localhost:8080/admin/get-customer/?
	@GetMapping("get-customer/{id}")
	public ResponseEntity<?> getOneCustomerById(@RequestHeader("Authorization") String token, @PathVariable int id) {
		try {
			tokensManager.isTokenExist(token);
			return new ResponseEntity<>(this.admin.getOneCustomer(id), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	// http://localhost:8080/admin/update-customer
	@PutMapping("update-customer")
	public ResponseEntity<?> updateCustomer(@RequestHeader("Authorization") String token, @RequestBody Customer customer) {
		try {
			tokensManager.isTokenExist(token);
			this.admin.updateCustomer(customer);
			return new ResponseEntity<>(customer.getId(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	// http://localhost:8080/admin/update-company
	@PutMapping("update-company")
	public ResponseEntity<?> updateCompany(@RequestHeader("Authorization") String token, @RequestBody Company company) {
		try {
			tokensManager.isTokenExist(token);
			this.admin.updateCompany(company);
			return new ResponseEntity<>(company.getId(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	// http://localhost:8080/admin/add-company
	@PostMapping("add-company")
	public ResponseEntity<?> addCompanys(@RequestHeader("Authorization") String token, @RequestBody Company company) {
		try {
			tokensManager.isTokenExist(token);
			this.admin.addCompany(company);
			return new ResponseEntity<>(admin.getAllCompanies(), HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	// http://localhost:8080/admin/get-companys
	@GetMapping("get-companys")
	public ResponseEntity<?> getAllCompanys(@RequestHeader("Authorization") String token) {
		try {
			tokensManager.isTokenExist(token);
			return new ResponseEntity<>(this.admin.getAllCompanies(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	// http://localhost:8080/admin/get-company/{id}
	@GetMapping("get-company/{id}")
	public ResponseEntity<?> getOneCompanyById(@RequestHeader("Authorization") String token, @PathVariable int id) {
		try {
			tokensManager.isTokenExist(token);
			return new ResponseEntity<>(this.admin.getOneCompany(id), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	// http://localhost:8080/admin/delete-company/{id}
	@DeleteMapping("delete-company/{id}")
	public ResponseEntity<?> deleteCompany(@RequestHeader("Authorization") String token, @PathVariable int id) {
		try {
			tokensManager.isTokenExist(token);
			this.admin.deleteCompany(id);
			return new ResponseEntity<>( HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	// http://localhost:8080/admin/delete-customer/{id}
	@DeleteMapping("delete-customer/{id}")
	public ResponseEntity<?> deleteCustomer(@RequestHeader("Authorization") String token, @PathVariable int id) {
		try {
			tokensManager.isTokenExist(token);
			this.admin.deleteCustomer(id);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

}