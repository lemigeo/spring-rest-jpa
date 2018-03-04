package com.rest.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.rest.config.RestConfig;
import com.rest.data.RestResponse;
import com.rest.domain.Account;
import com.rest.exception.RestException;
import com.rest.service.AccountLogService;
import com.rest.service.AccountService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value="/account")
@RestController
@CrossOrigin
public class AccountController {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private RestConfig config;
	
	@Autowired
	private AccountService service;
	
	@Autowired
	private AccountLogService logService;
	
	private Gson gson;
	
	public AccountController() {
		gson = new Gson();
	}
	
	@ApiOperation(value="greeting")
	@RequestMapping(value="/account", method=RequestMethod.GET)
	@ResponseBody
	public RestResponse greeting(){
		return new RestResponse(service.check(), config.getGreeting());
	}
	
	@ApiOperation(value="create new account")
	@RequestMapping(value="/account/create/{name}", method=RequestMethod.GET)
	@ResponseBody
	public RestResponse create(@PathVariable("name")String accountName){
		RestResponse resp = null;
		try {
			Account account = service.createAccount(accountName);
			logService.add(account);
			resp = new RestResponse(true, gson.toJson(account));
		} catch (Exception e) {
			logger.error(e.getMessage());
			resp = new RestResponse(false, e.getMessage());
		}
		return resp;
	}
	
	@ApiOperation(value="get account information")
	@RequestMapping(value="/account/get/{acctNo}", method=RequestMethod.GET)
	@ResponseBody
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
	
	@ApiOperation(value="block account")
	@RequestMapping(value="/account/block/{acctNo}", method=RequestMethod.GET)
	@ResponseBody
	public RestResponse block(@PathVariable("acctNo")long acctNo){
		RestResponse resp = null;
		try {
			Account account = service.block(acctNo);
			logService.add(account);
			resp = new RestResponse(true, "");
		} catch (Exception e) {
			logger.error(e.getMessage());
			resp = new RestResponse(false, e.getMessage());
		}
		return resp;
	}
	
	@ApiOperation(value="unblock account")
	@RequestMapping(value="/account/unblock/{acctNo}", method=RequestMethod.GET)
	@ResponseBody
	public RestResponse unblock(@PathVariable("acctNo")long acctNo){
		RestResponse resp = null;
		try {
			Account account = service.unblock(acctNo);
			logService.add(account);
			resp = new RestResponse(true, "");
		} catch (Exception e) {
			logger.error(e.getMessage());
			resp = new RestResponse(false, e.getMessage());
		}
		return resp;
	}
	
}