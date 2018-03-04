package com.rest.repository;

import org.springframework.data.repository.CrudRepository;

import com.rest.domain.Account;

public interface AccountRepository extends CrudRepository<Account, Long> {
}