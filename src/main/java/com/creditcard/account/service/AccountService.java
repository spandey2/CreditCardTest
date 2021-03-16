package com.creditcard.account.service;

import java.util.ArrayList;
import java.util.List;
import com.creditcard.account.exception.NoRecordFoundException;
import com.creditcard.account.utils.Validation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.creditcard.account.domain.Account;
import com.creditcard.account.repository.AccountRepository;

@Service
public class AccountService {

	// @Autowired annotation provides the automatic dependency injection.
	@Autowired
	AccountRepository accountRepository;

	// Save account entity in the h2 database.
	public Account save(final Account account)  {
		Account accountData = accountRepository.save(account);
		return accountData;
	}

	// Get all account from the h2 database.
	public List<Account> getAll() throws NoRecordFoundException {
		final List<Account> accounts = new ArrayList<>();
		accountRepository.findAll().forEach(account -> accounts.add(account));
		return accounts;
	}
}
