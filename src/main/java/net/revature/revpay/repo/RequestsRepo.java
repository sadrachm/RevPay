package net.revature.revpay.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import net.revature.revpay.model.Requests;

public interface RequestsRepo extends JpaRepository<Requests, Long>{
	public List<Requests> findAllByRequestor_IdAndCompleted(long id, boolean completed);
	public List<Requests> findAllByReceiver_IdAndCompleted(long receiverId, boolean completed);
}
