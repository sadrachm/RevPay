package net.revature.revpay.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import net.revature.revpay.model.Account;
import net.revature.revpay.model.Requests;

public interface RequestsRepo extends JpaRepository<Requests, Long>{
	public List<Requests> findAllByRequestor_Id(long id);
	public List<Requests> findAllByReceiver_Id(long receiverId);
}
