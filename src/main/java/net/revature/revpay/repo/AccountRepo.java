package net.revature.revpay.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.revature.revpay.model.Account;
@Repository
public interface AccountRepo extends JpaRepository<Account, Long>{
	Account findByUsername(String username);

}
