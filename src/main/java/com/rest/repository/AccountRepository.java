package com.rest.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.rest.domain.Account;

@Repository
public interface AccountRepository extends CrudRepository<Account, Long> {
}