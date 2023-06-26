package net.revature.revpay.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import net.revature.revpay.Exceptions.InputException;
import net.revature.revpay.model.Account;
import net.revature.revpay.model.Requests;
import net.revature.revpay.model.Transactions;
import net.revature.revpay.service.AccountService;

@RestController
@CrossOrigin("*")
@RequestMapping("/v1")
public class AccountController {
	@Autowired
	AccountService accountService;
	
//	ACCOUNT INFO
	
	@PostMapping("/account")
	public ResponseEntity<Account> register(@RequestBody Account account) throws InputException {
		return new ResponseEntity<>(accountService.register(account), HttpStatus.ACCEPTED);
	}
	@PostMapping("/credentials")
	public ResponseEntity<Account> login(@RequestBody Account account) throws InputException {		
		return new ResponseEntity<>(accountService.login(account.getUsername(), account.getPassword()), HttpStatus.OK);
	}
	@GetMapping("/account") 
	public ResponseEntity<List<Account>> getAccounts() {
		return new ResponseEntity<>(accountService.getAllAccounts(), HttpStatus.OK);
	}
	@GetMapping("/account/single") 
	public ResponseEntity<Account> getAccounts(@RequestParam long accountId) {
		return new ResponseEntity<>(accountService.getAccount(accountId), HttpStatus.OK);
	}

//	GET REQUEST
	
	@GetMapping("/request")
	public ResponseEntity<List<Requests>> getRequests() {
		return new ResponseEntity<>(accountService.getAllRequests(), HttpStatus.OK);
	}
	@GetMapping("/request/requestor")
	public ResponseEntity<List<Requests>> getRequestsByRequestor(@RequestParam long requestorId) {
		return new ResponseEntity<>(accountService.getRequestsByRequestor(requestorId), HttpStatus.OK);
	}
	@GetMapping("request/receiver")
	public ResponseEntity<List<Requests>> getRequestsByReceiver(@RequestParam long requestorId) {
		return new ResponseEntity<>(accountService.getRequestByReceiver(requestorId), HttpStatus.OK);
	}
	@GetMapping("/request/requestor/complete")
	public ResponseEntity<List<Requests>> getCompletedRequestsByRequestor(@RequestParam long requestorId) {
		return new ResponseEntity<>(accountService.getCompletedRequestByRequestor(requestorId), HttpStatus.OK);
	}
	@GetMapping("request/receiver/complete")
	public ResponseEntity<List<Requests>> getCompletedRequestsByReceiver(@RequestParam long requestorId) {
		return new ResponseEntity<>(accountService.getCompletedRequestByReceiver(requestorId), HttpStatus.OK);
	}
	@GetMapping("/request/{id}")
	public ResponseEntity<List<Requests>> getRequestById(@PathVariable long id) {
		return new ResponseEntity<>(accountService.getRequests(id), HttpStatus.OK);
	}
	
//	SEND MONEY / ACCEPT REQUEST
	
	@PatchMapping("/request")
	public ResponseEntity<Requests> acceptRequest(@RequestParam long requestId) throws InputException {
		return new ResponseEntity<Requests>(accountService.acceptRequest(requestId), HttpStatus.ACCEPTED);
	}	
	@PostMapping("/transaction")
	public ResponseEntity<Account> sendMoney(@RequestParam long senderId, @RequestParam String receiverId, @RequestParam double balance) throws InputException {
		return new ResponseEntity<> (accountService.sendMoney(senderId, receiverId, balance), HttpStatus.ACCEPTED);
	}	
	@PostMapping("/request")
	public ResponseEntity<Requests> requestMoney(@RequestBody Requests request) throws InputException {
		return new ResponseEntity<>(accountService.requestMoney(request), HttpStatus.ACCEPTED);
	}	
	
//	Transactions
	
	@GetMapping("/transactions")
	public ResponseEntity<List<Transactions>> getAllTransactions() {
		return new ResponseEntity<>(accountService.getAllTransactions(), HttpStatus.ACCEPTED);
	}
	@GetMapping("/transactions/{id}") 
	public ResponseEntity<List<Transactions>> getAllTransactionsByAccount(@PathVariable long id) {
		return new ResponseEntity<>(accountService.getTransactionsByAccount(id), HttpStatus.ACCEPTED);
	}
}
