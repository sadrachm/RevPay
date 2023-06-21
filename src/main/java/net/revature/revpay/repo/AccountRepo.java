package net.revature.revpay.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.revature.revpay.model.Account;

public interface AccountRepo extends JpaRepository<Account, Long>{
	Optional<Account> findByUsername(String username);
	Optional<Account> findByEmail(String email);
	Optional<Account> findByPhone(String phone);

}
