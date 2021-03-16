package com.creditcard.account.controller;


import com.creditcard.account.domain.Account;
import com.creditcard.account.service.AccountService;
import com.creditcard.account.utils.Validation;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import io.restassured.RestAssured;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static io.restassured.RestAssured.basic;
import static io.restassured.RestAssured.given;

@RunWith(SpringRunner.class)
@WebMvcTest(AccountController.class)
public class AccountControllerTest {

    @Autowired
    private AccountController accountController;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AccountService accountService;

    @MockBean
    private Validation validation;


    // test case to get a account details
    @Test
    @WithMockUser
    public void testGetAccountDetailGetAllMethod() throws Exception {

        Account account1 = new Account(1, "Smita", "1358954993914435", 0,800);
        List<Account> accounts = new ArrayList<Account>();
        accounts.add(account1);
        when(accountService.getAll()).thenReturn(accounts);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/account/getall"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    // test case for basic authentication failure for getAll method
    @Test
    public void testToReturnUnauthorizedGetAllMethod() throws Exception {

        Account account1 = new Account(1, "Smita", "1358954993914435", 0,800);
        List<Account> accounts = new ArrayList<Account>();
        accounts.add(account1);
        when(accountService.getAll()).thenReturn(accounts);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/account/getall").contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isUnauthorized());
    }


    // test case for basic authentication failure for save method
    @Test
    public void testToReturnUnauthorizedSaveMethod() throws Exception {
        Account account = new Account(1, "Smita", "1358954993914435", 0,800);
        List<Account> accounts = new ArrayList<Account>();
        accounts.add(account);
        when(accountService.save(account)).thenReturn(account);
        mockMvc.perform(MockMvcRequestBuilders.request(HttpMethod.POST, "api/account/add").contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isUnauthorized());
    }

    // test case for invalid card number failure for save method
    @Test
    @WithMockUser
    public void testToInvalidCardNumberSaveMethod()throws Exception {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        Account account = new Account(1, "Smita", "13589549939144351111", 0,800);
        String json = ow.writeValueAsString(account);
        when(validation.isValidCreditCardNumber(account.getCard())).thenReturn(true);
        when(accountService.save(account)).thenReturn(account);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/account/add").contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    // test case for invalid balance failure for save method
    @Test
    @WithMockUser
    public void testToInvalidBalanceSaveMethod()throws Exception {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        Account account = new Account(1, "Smita", "1358954993914435", 5,800);
        String json = ow.writeValueAsString(account);
        when(validation.isValidCreditCardNumber(account.getCard())).thenReturn(true);
        when(accountService.save(account)).thenReturn(account);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/account/add").contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    // test case for valid data for save method
    @Test
    @WithMockUser
    public void testToValidCardNumberAuthorizedSaveMethod()throws Exception {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        Account account = new Account(1, "Smita", "1358954993914435", 0,800);
        String json = ow.writeValueAsString(account);
        when(validation.isValidCreditCardNumber(account.getCard())).thenReturn(true);
        when(accountService.save(account)).thenReturn(account);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/account/add").contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }



}
