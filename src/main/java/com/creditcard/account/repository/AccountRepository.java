package com.creditcard.account.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.creditcard.account.domain.Account;

@Repository
public interface AccountRepository extends CrudRepository<Account, Integer>{

}
