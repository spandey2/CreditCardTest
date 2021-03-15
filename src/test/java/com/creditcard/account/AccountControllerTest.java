 package com.creditcard.account;

 import com.creditcard.account.controller.AccountController;
 import com.creditcard.account.domain.Account;
 import com.creditcard.account.service.AccountService;
 import org.junit.jupiter.api.Test;
 import org.junit.jupiter.api.extension.ExtendWith;
 import org.mockito.junit.jupiter.MockitoExtension;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.http.HttpStatus;
 import org.springframework.http.ResponseEntity;
 import org.springframework.mock.web.MockHttpServletRequest;
 import org.springframework.web.context.request.RequestContextHolder;
 import org.springframework.web.context.request.ServletRequestAttributes;

 import java.util.ArrayList;
 import java.util.List;

 import static org.assertj.core.api.Assertions.assertThat;
 import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class AccountControllerTest {


    @Autowired
    AccountController accountController;

    @Autowired
    AccountService accountService;

    @Test
    public void testAddEmployee()
    {

        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        Account account = new Account(1, "Smita", "1358954993914435", 600,800);
        ResponseEntity<Object> responseEntity = accountController.save(account);

        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        assertThat(responseEntity.getBody()).isEqualTo(1);
    }

    @Test
    public void testFindAll()
    {
        Account account1 = new Account(1, "Smita", "1358954993914435", 600,800);
        List<Account> accountList = new ArrayList<>();
        accountList.add(account1);
        when(accountService.getAll()).thenReturn(accountList);
        ResponseEntity r=new ResponseEntity(HttpStatus.ACCEPTED);
        // when
        ResponseEntity<Object>  result = accountController.getAll();
        // then
        assertThat(result.getBody().equals(Account.class));
        assertThat(result.getStatusCodeValue()).isEqualTo(200);


}

    @Test
    public void testAddEmployeeFail()
    {

        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        Account account = new Account(1, "Smita", "123", 600,800);
        ResponseEntity<Object> responseEntity = accountController.save(account);

        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(400);

    }
}
