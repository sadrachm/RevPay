package net.revature.revpay.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.revature.revpay.model.Account;
import net.revature.revpay.model.Requests;
import net.revature.revpay.repo.AccountRepo;
import net.revature.revpay.repo.RequestsRepo;
import net.revature.revpay.repo.TransactionsRepo;
import net.revature.revpay.Exceptions.InputException;

import net.revature.revpay.model.Transactions;

@Service
public class AccountService {
	@Autowired
	private AccountRepo accountRepo;
	@Autowired
	private RequestsRepo requestsRepo;
	@Autowired
	private TransactionsRepo transactionsRepo;
	
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
		return requestsRepo.findAllByRequestor_IdAndCompleted(requestorId,false);
	}
	public List<Requests> getRequestByReceiver(long receiverId) {
		return requestsRepo.findAllByReceiver_IdAndCompleted(receiverId, false);
	}
	public List<Requests> getCompletedRequestByRequestor(long receiverId) {
		return requestsRepo.findAllByRequestor_IdAndCompleted(receiverId, true);
	}
	public List<Requests> getCompletedRequestByReceiver(long receiverId) {
		return requestsRepo.findAllByReceiver_IdAndCompleted(receiverId, true);
	}
	
	public Account login(String username, String password) throws InputException {
		Account user;
		try {
			user = accountRepo.findByUsername(username).orElseThrow();			
		} catch(NoSuchElementException e) {
			throw new InputException("User does not Exist");
		}
		if (!user.getPassword().equals(password)) {
			throw new InputException("Invalid password for given username");
		}
		Account copy = user;
		copy.setPassword("");
		return copy;
	}
	
	public Account register(Account account) throws InputException {
		String errMessage = "";
		if (account.getUsername() == "") throw new InputException("Please fill out all Entries");
		if (account.getPassword() == "") throw new InputException("Please fill out all Entries");
		if (account.getEmail() == "") throw new InputException("Please fill out all Entries");
		if (account.getPhone() == "") throw new InputException("Please fill out all Entries");
		try {
			return accountRepo.save(account);			
		} catch (Exception e) {
			throw new InputException(e.getMessage());
		}
	}
	
	public Account sendMoney(Long senderId, String receiverId, double balance) throws InputException {
		Account sender;
		Account receiver;
		try {
			sender = accountRepo.findById(senderId).orElseThrow();
			String regex="([0-9]+-*)*";
			if (receiverId.contains("@")) {
				System.out.print("HELLO");
				receiver = accountRepo.findByEmail(receiverId).orElseThrow();
			} else if (receiverId.matches(regex)) {
				receiver=accountRepo.findByPhone(receiverId).orElseThrow();
			} else {
				receiver = accountRepo.findByUsername(receiverId).orElseThrow();
			}		
		} catch (NoSuchElementException e) {
			throw new InputException("Recipient does not exist");
		}
		if (sender.getBalance() - balance < 0) {
			throw new InputException("Insufficient Funds");
		}
		sender.setBalance(sender.getBalance()-balance);
		receiver.setBalance(receiver.getBalance()+balance);
		accountRepo.save(sender);
		accountRepo.save(receiver);
		Transactions trans = new Transactions(sender, receiver, balance);
		transactionsRepo.save(trans);
		return sender;		
	}
	
	public Requests requestMoney(Requests request) throws InputException {
		Account requestor;
		Account receiver = request.getReceiver();
		String identifier = request.getReceiver().getUsername();
		try {
			requestor = accountRepo.findById(request.getRequestor().getId()).orElseThrow();
			String regex="([0-9]+-*)*";
			if (identifier.contains("@")) {
				receiver = accountRepo.findByEmail(identifier).orElseThrow();
			} else if (identifier.matches(regex)) {
				receiver=accountRepo.findByPhone(identifier).orElseThrow();
			} else {
				receiver = accountRepo.findByUsername(identifier).orElseThrow();
			}				
		} catch (NoSuchElementException e) {
			throw new InputException("Recipient does not exist");
		}
		request.setCompleted(false);
		request.setRequestor(requestor);
		request.setReceiver(receiver);
		return requestsRepo.save(request);		
	}
	
	public Requests acceptRequest(Long requestId) throws InputException {
		Requests request;
		try {
			request = requestsRepo.findById(requestId).orElseThrow();
			if (request.isCompleted()) throw new InputException("Request is already completed");
		} catch(NoSuchElementException e) {
			throw new InputException("Request does not exist");
		} 
		Account temp = this.sendMoney(request.getReceiver().getId(), request.getRequestor().getUsername(), request.getBalance());
		request.setCompleted(true);
		return requestsRepo.save(request);
		
	}
	public List<Requests> getRequests(long accountId) {
		Account temp = accountRepo.findById(accountId).get();
		return temp.getRequests();
	}
	
	public List<Transactions> getAllTransactions() {
		return transactionsRepo.findAll();
	}
	
	public List<Transactions> getTransactionsByAccount(Long account) {
		return transactionsRepo.findAllByReceiver_IdOrSender_Id(account, account);
	}
}
