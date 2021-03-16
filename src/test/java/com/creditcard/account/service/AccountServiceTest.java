package com.creditcard.account.service;

import com.creditcard.account.domain.Account;
import com.creditcard.account.repository.AccountRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@WebMvcTest(AccountService.class)


public class AccountServiceTest {

    @MockBean
    private AccountRepository accountRepository;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private AccountService accountService;

    //Test Case to check if data saved successfully
    @Test
    public void testAccountDetailsSaveMethod(){

        Account account = new Account(1, "Smita", "1358954993914435", 0,800);
        when(accountRepository.save(account)).thenReturn(account);
        Account result = accountService.save(account);
        Assert.assertEquals(account.getName(),result.getName());
    }


    //Test Case to check if data saved successfully
    @Test
    public void testAccountDetailsGetMethod(){

        Account account = new Account(1, "Smita", "1358954993914435", 0,800);
        List<Account> accounts = new ArrayList<Account>();
        accounts.add(account);
        when(accountRepository.findAll()).thenReturn(accounts);
        List<Account>  result = accountService.getAll();
        Assert.assertEquals(accounts.get(0).getName(),result.get(0).getName());
    }



}
