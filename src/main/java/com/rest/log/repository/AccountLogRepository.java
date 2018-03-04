package com.rest.log.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.rest.log.domain.AccountLog;

@Repository
public interface AccountLogRepository extends CrudRepository<AccountLog, Long> {
}