package net.revature.revpay.service;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.revature.revpay.model.Account;
import net.revature.revpay.model.Requests;
import net.revature.revpay.repo.AccountRepo;
import net.revature.revpay.repo.RequestsRepo;
import net.revature.revpay.Exceptions.InputException;
@Service
public class AccountService {
	@Autowired
	private AccountRepo accountRepo;
	@Autowired
	private RequestsRepo requestsRepo;
	
	public boolean login(String username, String password) throws InputException {
		Account user = accountRepo.findByUsername(username);
		if (user == null) {
			throw new InputException("User with that username does not Exist"); 
		}
		if (!user.getPassword().equals(password)) {
			throw new InputException("Invalid password for given username");
		}
		return true;
	}
	public Account register(Account account) {
		return accountRepo.save(account);
	}
	public double sendMoney(Long senderId, Long receiverId, double balance) throws InputException {
		Account sender;
		Account receiver;
		try {
			sender = accountRepo.findById(senderId).orElseThrow();
			receiver = accountRepo.findById(receiverId).orElseThrow();			
		} catch (NoSuchElementException e) {
			throw new InputException("Sender ID or Receiver ID does not exist");
		}
		System.out.println(sender.getBalance());
		if (sender.getBalance() - balance < 0) {
			throw new InputException("Insufficient Funds");
		}
		sender.setBalance(sender.getBalance()-balance);
		receiver.setBalance(receiver.getBalance()+balance);
		accountRepo.save(sender);
		accountRepo.save(receiver);
		return sender.getBalance();		
	}
	public Requests requestMoney(Requests request) throws InputException {
		try {
			Account requestor = accountRepo.findById(request.getRequestorId()).orElseThrow();
			Account receiver = accountRepo.findById(request.getReceiverId()).orElseThrow();			
		} catch (NoSuchElementException e) {
			throw new InputException("Sender ID or Receiver ID does not exist");
		}
		return requestsRepo.save(request);		
	}

 
}
