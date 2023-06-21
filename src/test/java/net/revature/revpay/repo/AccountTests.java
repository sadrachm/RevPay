package net.revature.revpay.repo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.NoSuchElementException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import net.revature.revpay.model.Account;



// DataJpaTest is going to load only the persistence layer (anything annotated with @Repository/@Entity)
// it will ignore (and not load) @Component, @Service, @Controller...
// by default - DataJpaTest applies the @Transactional annotation - which rollsback any transactions
	// against the database

@DataJpaTest
public class AccountTests {

	
	@Autowired
	private AccountRepo accountRepo;
	
	@Autowired
	private TestEntityManager entityManager;
	
	@Test
	public void shouldNotFindAnyAccounts() {
		Iterable<Account> accounts = accountRepo.findAll();

		assertThat(accounts).isEmpty();
	}
	@Test
	public void shouldStoreAnAccount() {
		Account account = accountRepo.save(new Account("sadrach", "password", 1000, "sadrach@gmail.com", "1"));
		assertThat(account).hasFieldOrPropertyWithValue("username", "sadrach");
		assertThat(account).hasFieldOrPropertyWithValue("password", "password");
		assertThat(account).hasFieldOrPropertyWithValue("email", "sadrach@gmail.com");		
	}
	@Test
	public void shouldFindAllAccounts() {
		Account account1 = new Account("sadrach", "password", 1000, "sadrach@gmail.com", "1");
		Account account2 = new Account("jesus", "password", 1000, "jesus@gmail.com", "2");
		Account account3 = new Account("ana", "password", 1000, "ana@gmail.com", "3");
		entityManager.persist(account1);
		entityManager.persist(account2);
		entityManager.persist(account3);
		Iterable<Account> accounts = accountRepo.findAll();
		assertThat(accounts).hasSize(3).contains(account1, account2, account3);
	}
	@Test
	public void shouldFindByUsername() {
		Account account1 = new Account("sadrach", "password", 1000, "sadrach@gmail.com", "1");
		Account account2 = new Account("jesus", "password", 1000, "jesus@gmail.com", "2");
		Account account3 = new Account("ana", "password", 1000, "ana@gmail.com", "3");
		entityManager.persist(account1);
		entityManager.persist(account2);
		entityManager.persist(account3);
		Account account = accountRepo.findByUsername("sadrach").get();
		assertEquals(account, account1);
	}
	
	@Test
	public void shouldFindByEmail() {
		Account account1 = new Account("sadrach", "password", 1000, "sadrach@gmail.com", "1");
		Account account2 = new Account("jesus", "password", 1000, "jesus@gmail.com", "2");
		Account account3 = new Account("ana", "password", 1000, "ana@gmail.com", "3");
		entityManager.persist(account1);
		entityManager.persist(account2);
		entityManager.persist(account3);
		Account account = accountRepo.findByEmail("jesus@gmail.com").get();
		assertEquals(account, account2);
	}
	@Test
	public void shouldFindByPhone() {
		Account account1 = new Account("sadrach", "password", 1000, "sadrach@gmail.com", "1");
		Account account2 = new Account("jesus", "password", 1000, "jesus@gmail.com", "2");
		Account account3 = new Account("ana", "password", 1000, "ana@gmail.com", "3");
		entityManager.persist(account1);
		entityManager.persist(account2);
		entityManager.persist(account3);
		Account account = accountRepo.findByEmail("ana@gmail.com").get();
		assertEquals(account, account3);		
	}
	@Test
	public void findByInvalidUsernameReturnError() {
		assertThrows(NoSuchElementException.class,()-> accountRepo.findByUsername("sadrach").get());
	}
	
}
