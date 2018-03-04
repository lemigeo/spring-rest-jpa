package com.rest.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "account")
public class Account {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "acctno")
	private long acctNo;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "is_block")
	private Boolean isBlock;
	
	@Column(name = "create_dt")
	private Date createDt;
	
	@Column(name = "update_dt")
	private Date updateDt;
	
	public Account() {
	}
	
	public Account(String name) {
		this.name = name;
		this.isBlock = false;
		Date now = new Date();
		this.createDt = now;
		this.updateDt = now;
	}

	public long getAcctNo() {
		return acctNo;
	}

	public void setAcctNo(long acctNo) {
		this.acctNo = acctNo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Boolean getIsBlock() {
		return isBlock;
	}

	public void setIsBlock(Boolean isBlock) {
		this.isBlock = isBlock;
	}

	public Date getCreateDt() {
		return createDt;
	}

	public void setCreateDt(Date createDt) {
		this.createDt = createDt;
	}

	public Date getUpdateDt() {
		return updateDt;
	}

	public void setUpdateDt(Date updateDt) {
		this.updateDt = updateDt;
	}
}
