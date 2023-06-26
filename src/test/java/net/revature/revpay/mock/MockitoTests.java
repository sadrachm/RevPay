package net.revature.revpay.mock;

import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import net.revature.revpay.Exceptions.InputException;
import net.revature.revpay.model.Account;
import net.revature.revpay.repo.AccountRepo;
import net.revature.revpay.repo.RequestsRepo;
import net.revature.revpay.repo.TransactionsRepo;
import net.revature.revpay.service.AccountService;

@ExtendWith(MockitoExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class MockitoTests {
	@InjectMocks
	private AccountService accountService;
	
	@Mock
	private AccountRepo accountRepo;
	
	@Mock
	private RequestsRepo requestsRepo;
	@Mock
	private TransactionsRepo transactionRepo;
	
	List<Account> accounts = new ArrayList();
	
	@Test
	@Order(1)
	public void MockLoginTest() throws InputException {
		Account a1 = new Account("sadrach", "pass", 1000, "sadrach@gmail.com", "123");
		Account a2 = new Account("jesus", "pass", 1000, "jesus@gmail.com", "456");
		Account a3 = new Account("ana", "pass", 1000, "ana@gmail.com", "789");
		accounts.add(a1);
		accounts.add(a2);
		accounts.add(a3);
		when(accountRepo.findByUsername("sadrach")).thenReturn(Optional.of(a1));
		accountService.login("sadrach", "pass");
		verify(accountRepo, times(1)).findByUsername("sadrach");
		
	}
	@Test
	public void SendMoney_SavesAccountRepo() throws InputException {
		Account a1 = new Account("sadrach", "pass", 1000, "sadrach@gmail.com", "123");
		Account a2 = new Account("jesus", "pass", 1000, "jesus@gmail.com", "456");
		Account a3 = new Account("ana", "pass", 1000, "ana@gmail.com", "789");
		accounts.add(a1);
		accounts.add(a2);
		accounts.add(a3);
		when(accountRepo.findByEmail("sadrach@gmail.com")).thenReturn(Optional.of(accounts.get(0)));
		when(accountRepo.findById((long) 1)).thenReturn(Optional.of(accounts.get(1)));
		accountService.sendMoney((long)1, "sadrach@gmail.com", 100.00);
		verify(accountRepo, times(1)).save(accounts.get(1));
		verify(accountRepo, times(1)).save(accounts.get(0));	
		
	}
	
	

}
