package net.revature.revpay;
import org.junit.Assert;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertThrows;

import net.revature.revpay.model.Requests;
import net.revature.revpay.service.AccountService;
import net.revature.revpay.Exceptions.InputException;
import net.revature.revpay.model.Account;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class RevPayApplicationTests {
	@Autowired
	AccountService accountService;
	@Test
	@Order(1)
	void registerAccount() {
		Account qwe = new Account( "sadrach", "password",1000.98,"gmail.com", "323803");
		Account copy = new Account("sadrach", "password",1000.98,"gmail.com", "323803");
		copy.setId((long) 1);
		Account temp = accountService.register(qwe);
		Assert.assertEquals(copy,temp );
	}
	@Test
	@Order(2)
	void login() throws InputException {
		Account sadrach = new Account( "firstUsername", "password",1000.98,"gmail.com", "323803");
		Account sadrach2 = new Account( "secondUsername", "password",1000.98,"gmail.com", "323803");
		accountService.register(sadrach);
		accountService.register(sadrach2);
		Assert.assertTrue(accountService.login("firstUsername", "password"));
		InputException thrown = assertThrows(
				InputException.class, 
				() -> accountService.login("sadrach", "Wrong Password"), 
				"Expected login to Throw but didn't");
		Assert.assertTrue(thrown.getMessage().contains("Invalid password for given username"));
	}
	@Test
	@Order(3)
	void sendMoney() throws InputException {
		Assert.assertEquals(800.98, accountService.sendMoney((long)1 ,(long) 2, 200), 1);
		InputException thrown = assertThrows(InputException.class,
				() -> accountService.sendMoney((long)1,(long)2, 1000),
				"Expected Send Money to have Insufficient funds but didn't");
		Assert.assertTrue(thrown.getMessage().contains("Insufficient Funds"));		
	}
	@Test
	@Order(4)
	void requestMoney() throws InputException {
		Requests request = new Requests((long)1, (long)2, 200);
		Requests temp = accountService.requestMoney(request);
		request.setId((long)1);
		Assert.assertEquals(request, temp);
	}
	
	

}
