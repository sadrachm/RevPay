package net.revature.revpay.repo;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import net.revature.revpay.model.Transactions;

public interface TransactionsRepo extends JpaRepository<Transactions, Long> {
	public List<Transactions> findAllByReceiver_IdOrSender_Id(long receiver, long sender, Pageable page);
}