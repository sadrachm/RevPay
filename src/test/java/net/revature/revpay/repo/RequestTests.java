package net.revature.revpay.repo;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import net.revature.revpay.model.Account;
import net.revature.revpay.model.Requests;

@DataJpaTest
public class RequestTests {
	@Autowired
	private RequestsRepo requestRepo;
	
	@Autowired
	private TestEntityManager entityManager;
	
	@Test
	public void FindAllByReceiverId_Completed() {
	}
	
	@Test
	public void FindAllByRequestorId_Completed() {
//		Account account1 = new Account("sadrach12", "password", 1000, "sadrach@gmail.com", "1");
//		Account account2 = new Account("jesus12", "password", 1000, "jesus@gmail.com", "2");
//		account1 = entityManager.persist(account1);
//		account2 = entityManager.persist(account2);
//		Requests req1 = new Requests(1000, account2, account1);
//		Requests req2 = new Requests(2000, account1, account2);
//		Requests req3 = new Requests(3000, account1, account2);
//		entityManager.persist(req1);
//		entityManager.persist(req2);
//		entityManager.persist(req3);
//		Iterable<Requests> requests = requestRepo.findAllByRequestor_IdAndCompleted((long) 1, false);
//		assertThat(requests).hasSize(2).contains(req2, req3);
	}
	
	
}