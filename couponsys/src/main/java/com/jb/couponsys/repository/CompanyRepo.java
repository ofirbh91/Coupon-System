package com.jb.couponsys.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jb.couponsys.bean.Company;

public interface CompanyRepo extends JpaRepository<Company, Integer> {

}
