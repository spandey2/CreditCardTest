package com.creditcard.account.service;

import com.creditcard.account.domain.Account;
import com.creditcard.account.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
	public List<Account> getAll()  {
		final List<Account> accounts = new ArrayList<>();
		accountRepository.findAll().forEach(account -> accounts.add(account));
		return accounts;
	}
}
