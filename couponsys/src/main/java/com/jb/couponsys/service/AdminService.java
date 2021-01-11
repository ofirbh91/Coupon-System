package com.jb.couponsys.service;

import java.util.List;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.jb.couponsys.bean.Company;
import com.jb.couponsys.bean.Customer;
import com.jb.couponsys.exception.InvalidDetailsException;

@Service
@Lazy
public class AdminService extends ClientService {
	public AdminService() {
		super();
	}

	@Override
	public boolean login(String email, String password)  {
		if (email.equals("admin@admin.com") && password.equals("Admin1234")) {
			return true;
		}
		return false;
	}
	public void addCompany(Company company) throws InvalidDetailsException {
		List<Company> companies = companyRepo.findAll();
		for (Company company2 : companies) {
			if (company2.getName().equals(company.getName()) || company2.getEmail().equals(company.getEmail())) {
				throw new InvalidDetailsException("Adding a Company with an existing name/email is not allowed");
			}
		}
		companyRepo.save(company);
	}

	public void updateCompany(Company company) throws InvalidDetailsException {
				
		companyRepo.saveAndFlush(company);
	}

	public void deleteCompany(int companyID) {
		companyRepo.delete(companyRepo.getOne(companyID));
	}

	public List<Company> getAllCompanies() {
		return companyRepo.findAll();
	}

	public Company getOneCompany(int companyID) {
		return companyRepo.getOne(companyID);
	}

	public void addCustomer(Customer customer) throws InvalidDetailsException {
		List<Customer> customers = customerRepo.findAll();
		for (Customer customer2 : customers) {
			if (customer2.getEmail().equals(customer.getEmail())) {
				throw new InvalidDetailsException("Sorry,This email address already belongs to an existing user");
			}
		}
		customerRepo.save(customer);
	}

	public void updateCustomer(Customer customer) throws InvalidDetailsException {
		customerRepo.saveAndFlush(customer);
	}
	public void deleteCustomer(int customerID) {
		customerRepo.delete(customerRepo.getOne(customerID));
	}

	public List<Customer> getAllCustomers() {

		return customerRepo.findAll();

	}

	public Customer getOneCustomer(int customerID) throws InvalidDetailsException {
		return customerRepo.getOne(customerID);
	}
}
