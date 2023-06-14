package net.revature.revpay.service;

import java.util.List;
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
	
	public Account getAccount(long id) {
		return accountRepo.findById(id).get();
	}
	
	public List<Account> getAllAccounts() {
		return accountRepo.findAll();
	}
	
	public List<Requests> getAllRequests() {
		return requestsRepo.findAll();
	}
	
	public List<Requests> getRequestsByRequestor(long requestorId) {
		return requestsRepo.findAllByRequestor_Id(requestorId);
	}
	public List<Requests> getRequestByReceiver(long receiverId) {
		return requestsRepo.findAllByReceiver_Id(receiverId);
	}
	
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
		Account requestor;
		Account receiver;
		try {
			requestor = accountRepo.findById(request.getRequestor().getId()).orElseThrow();
			receiver = accountRepo.findById(request.getReceiver().getId()).orElseThrow();			
		} catch (NoSuchElementException e) {
			throw new InputException("Sender ID or Receiver ID does not exist");
		}
		request.setCompleted(false);
		request.setRequestor(requestor);
		request.setReceiver(receiver);
		return requestsRepo.save(request);		
	}
	
	public double acceptRequest(Long requestId) throws InputException {
		Requests request;
		try {
			request = requestsRepo.findById(requestId).orElseThrow();
			if (request.isCompleted()) throw new InputException("Request is already completed");
		} catch(NoSuchElementException e) {
			throw new InputException("Request does not exist");
		} 
		double temp = this.sendMoney(request.getReceiver().getId(), request.getRequestor().getId(), request.getBalance());
		request.setCompleted(true);
		requestsRepo.save(request);
		return temp;
		
	}
}
