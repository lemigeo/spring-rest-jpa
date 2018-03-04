package com.rest.log.repository;

import org.springframework.data.repository.CrudRepository;

import com.rest.log.domain.AccountLog;

public interface AccountLogRepository extends CrudRepository<AccountLog, Long> {
}