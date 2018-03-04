package com.rest.log.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.rest.domain.Account;

@Entity
@Table(name = "account_lopg")
public class AccountLog {
	
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "logid")
	private long logId;
	
	@Column(name = "acctno")
	private long acctNo;
	
	@Column(name = "is_block")
	private Boolean isBlock;
	
	@Column(name = "create_dt")
	private Date createDt;
	
	public AccountLog() {
	}
	
	public AccountLog(Account acct) {
		this.acctNo = acct.getAcctNo();
		this.isBlock = acct.getIsBlock();
		this.createDt = new Date();
	}

	public long getLogId() {
		return logId;
	}

	public void setLogId(long logId) {
		this.logId = logId;
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
	
}
