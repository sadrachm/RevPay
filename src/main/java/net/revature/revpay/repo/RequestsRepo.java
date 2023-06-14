package net.revature.revpay.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.revature.revpay.model.Requests;
@Repository
public interface RequestsRepo extends JpaRepository<Requests, Long>{

}
