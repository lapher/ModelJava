package com.system.aShiro.mapper;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.system.aShiro.bean.Account;

@Repository
public interface AccountMapper{
	
	public Account findByUsername(@Param("username") String username);
	public void insertAccount(Account account);
	
}