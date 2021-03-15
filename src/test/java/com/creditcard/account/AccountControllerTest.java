 package com.creditcard.account;

 import com.creditcard.account.controller.AccountController;
 import com.creditcard.account.domain.Account;
 import com.creditcard.account.service.AccountService;
 import org.aspectj.lang.annotation.Before;
 import org.junit.jupiter.api.BeforeEach;
 import org.junit.jupiter.api.Test;
 import org.junit.jupiter.api.extension.ExtendWith;
 import org.mockito.Mock;
 import org.mockito.junit.jupiter.MockitoExtension;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.http.HttpStatus;
 import org.springframework.http.ResponseEntity;
 import org.springframework.mock.web.MockHttpServletRequest;
 import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
 import org.springframework.test.web.servlet.setup.MockMvcBuilders;
 import org.springframework.web.context.request.RequestContextHolder;
 import org.springframework.web.context.request.ServletRequestAttributes;

 import java.util.ArrayList;
 import java.util.List;

 import static org.assertj.core.api.Assertions.assertThat;
 import static org.mockito.Mockito.mock;
 import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class AccountControllerTest {


    @Mock
    AccountController accountController;


    AccountService accountService;

    @BeforeEach
    public void setup() {
        accountController=new AccountController();
        accountService=mock(AccountService.class);
        //mockMvcBuilder = MockMvcBuilders.standaloneSetup(new LocationTimesController(locationTimesService)).setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver()).setControllerAdvice(new RestResponseEntityExceptionHandler());
        //RestAssuredMockMvc.standaloneSetup(mockMvcBuilder);

    }
/*
    @Test
    public void testAddAccount()
    {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        Account account = new Account(1, "Smita", "1358954993914435", 0,800);

        Account account1 = new Account(1, "Smita", "1358954993914435", 0,800);
        List<Account> accountList = new ArrayList<>();
        accountList.add(account1);
        ResponseEntity<Object> responseEntity = accountController.save(account);

        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        assertThat(responseEntity.getBody()).isEqualTo(1);
    }

    @Test
    public void testFindAll()
    {
        Account account1 = new Account(1, "Smita", "1358954993914435", 0,800);
        List<Account> accountList = new ArrayList<>();
        accountList.add(account1);
        when(accountService.getAll()).thenReturn(accountList);
        ResponseEntity r=new ResponseEntity(HttpStatus.ACCEPTED);
        ResponseEntity<Object>  result = accountController.getAll();
        assertThat(result.getBody().equals(Account.class));
        assertThat(result.getStatusCodeValue()).isEqualTo(200);


}*/

    @Test
    public void testAddAccountInvalidCard()
    {

        Account account = new Account(1, "Smita", "123", 600,800);
        ResponseEntity<Object> responseEntity = accountController.save(account);

        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(400);

    }

    @Test
    public void testAddAccountInvalidBalance()
    {

        Account account = new Account(1, "Smita", "0", 600,800);
        ResponseEntity<Object> responseEntity = accountController.save(account);

        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(400);

    }
}
