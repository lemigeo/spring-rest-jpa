package com.rest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.rest.domain.Account;
import com.rest.exception.RestException;
import com.rest.repository.AccountRepository;

@Service
public class AccountService {

	@Autowired
	private AccountRepository repository;
	
	@Autowired
	@Qualifier("transactionManager")
	private PlatformTransactionManager transMgr;
	
	public Boolean check() {
		return true;
	}
	
	public Account createAccount(String name) throws Exception {
		Account entity = new Account(name);
		try {
			entity = repository.save(entity);
		}
		catch(Exception e) {
			throw new RestException(e.getMessage(), e);
		}
		return entity;
	}
	
	public Account getInfo(long acctNo) throws Exception {
		Account entity;
		try {
			entity = repository.findOne(acctNo);
		}
		catch(Exception e) {
			throw new RestException(e.getMessage(), e);
		}
		return entity;
	}
	
	public Account block(long acctNo) throws Exception {
		TransactionStatus status = getTransaction();
		Account entity;
		try{
			entity = repository.findOne(acctNo);
			if(entity == null) {
				throw new RestException("account not found");
			}
			entity.setIsBlock(true);
			repository.save(entity);
			transMgr.commit(status);
		}
		catch(RestException e) {
			transMgr.rollback(status);
			throw e;
		}
		catch(Exception e) {
			transMgr.rollback(status);
			throw new RestException(e.getMessage(), e);
		}
		return entity;
	}
	
	public Account unblock(long acctNo) throws Exception {
		TransactionStatus status = getTransaction();
		Account entity;
		try{
			entity = repository.findOne(acctNo);
			if(entity == null) {
				throw new RestException("account not found");
			}
			entity.setIsBlock(false);
			repository.save(entity);
			transMgr.commit(status);
		}
		catch(RestException e) {
			transMgr.rollback(status);
			throw e;
		}
		catch(Exception e) {
			transMgr.rollback(status);
			throw new RestException(e.getMessage(), e);
		}
		return entity;
	}
	
	private TransactionStatus getTransaction() {
		DefaultTransactionDefinition def = new DefaultTransactionDefinition(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
		def.setIsolationLevel(TransactionDefinition.ISOLATION_READ_COMMITTED);
		def.setTimeout(10);
		return transMgr.getTransaction(def);
	}
}