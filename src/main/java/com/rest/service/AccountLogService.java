package com.rest.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rest.domain.Account;
import com.rest.log.domain.AccountLog;
import com.rest.log.repository.AccountLogRepository;

@Service
public class AccountLogService {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private AccountLogRepository repository;
	
	public AccountLogService() {
	}
	
	public void add(Account acct) throws Exception{
		try {
			AccountLog entity = new AccountLog(acct);
			repository.save(entity);
		}
		catch (Exception e) {
			logger.error(e.getMessage());
			throw e;
		}
	}
}