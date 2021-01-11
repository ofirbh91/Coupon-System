package com.jb.couponsys.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jb.couponsys.bean.Customer;

public interface CustomerRepo extends JpaRepository<Customer, Integer> {

}
