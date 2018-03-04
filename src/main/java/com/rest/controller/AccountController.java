package com.rest.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.rest.config.RestConfig;
import com.rest.data.RestResponse;
import com.rest.domain.Account;
import com.rest.exception.RestException;
import com.rest.service.AccountService;

@EnableAsync
@RestController(value="/account")
public class AccountController {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private RestConfig config;
	
	@Autowired
	private AccountService service;
	
	private Gson gson;
	
	public AccountController() {
		gson = new Gson();
	}
	
	@RequestMapping(value="/account", method=RequestMethod.GET)
	public RestResponse greeting(){
		return new RestResponse(service.check(), config.getGreeting());
	}
	
	@RequestMapping(value="/account/create/{name}", method=RequestMethod.GET)
	public RestResponse create(@PathVariable("name")String accountName){
		RestResponse resp = null;
		try {
			Account account = service.createAccount(accountName);
			resp = new RestResponse(true, gson.toJson(account));
		} catch (Exception e) {
			logger.error(e.getMessage());
			resp = new RestResponse(false, e.getMessage());
		}
		return resp;
	}
	
	@RequestMapping(value="/account/get/{acctNo}", method=RequestMethod.GET)
	public RestResponse get(@PathVariable("acctNo")long acctNo){
		RestResponse resp = null;
		try {
			Account account = service.getInfo(acctNo);
			if(account == null) {
				throw new RestException("account not found");
			}
			resp = new RestResponse(true, gson.toJson(account));
		} catch (Exception e) {
			logger.error(e.getMessage());
			resp = new RestResponse(false, e.getMessage());
		}
		return resp;
	}
	
	@RequestMapping(value="/account/block/{acctNo}", method=RequestMethod.GET)
	public RestResponse block(@PathVariable("acctNo")long acctNo){
		RestResponse resp = null;
		try {
			service.block(acctNo);
			resp = new RestResponse(true, "");
		} catch (Exception e) {
			logger.error(e.getMessage());
			resp = new RestResponse(false, e.getMessage());
		}
		return resp;
	}
	
	@RequestMapping(value="/account/unblock/{acctNo}", method=RequestMethod.GET)
	public RestResponse unblock(@PathVariable("acctNo")long acctNo){
		RestResponse resp = null;
		try {
			service.unblock(acctNo);
			resp = new RestResponse(true, "");
		} catch (Exception e) {
			logger.error(e.getMessage());
			resp = new RestResponse(false, e.getMessage());
		}
		return resp;
	}
	
}