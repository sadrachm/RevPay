package net.revature.revpay.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import net.revature.revpay.Exceptions.InputException;
import net.revature.revpay.model.Account;
import net.revature.revpay.model.Requests;
import net.revature.revpay.service.AccountService;

@RestController
@CrossOrigin("*")
@RequestMapping("/v1")
public class AccountController {
	@Autowired
	AccountService accountService;
	
	@PostMapping("/account")
	public ResponseEntity<Account> register(@RequestBody Account account) {
		return new ResponseEntity<>(accountService.register(account), HttpStatus.ACCEPTED);
	}
	@PostMapping("/credentials")
	public ResponseEntity<String> login(@RequestBody Account account) throws InputException {
		accountService.login(account.getUsername(), account.getPassword());
		return new ResponseEntity<>("Successfully logged in", HttpStatus.FOUND);
	}
	@PostMapping("/transaction")
	public ResponseEntity<Double> sendMoney(@RequestParam long senderId, @RequestParam long receiverId, @RequestParam double balance) throws InputException {
		return new ResponseEntity<> (accountService.sendMoney(senderId, receiverId, balance), HttpStatus.ACCEPTED);
	}
	@PostMapping("/request")
	public ResponseEntity<String> requestMoney(@RequestBody Requests request) throws InputException {
		accountService.requestMoney(request);
		return new ResponseEntity<>("Successfully sent request", HttpStatus.ACCEPTED);
	}	
	@GetMapping("/account") 
	public ResponseEntity<List<Account>> getAccounts() {
		return new ResponseEntity<>(accountService.getAllAccounts(), HttpStatus.OK);
	}
	@GetMapping("/request")
	public ResponseEntity<List<Requests>> getRequests() {
		return new ResponseEntity<>(accountService.getAllRequests(), HttpStatus.OK);
	}
	@PatchMapping("/request")
	public ResponseEntity<String> acceptRequest(@RequestParam long requestId) throws InputException {
		accountService.acceptRequest(requestId);
		return new ResponseEntity<>("Request has been Accepted", HttpStatus.ACCEPTED);
	}	

	

	@GetMapping("/request/requestor")
	public ResponseEntity<List<Requests>> getRequestsByRequestor(@RequestParam long requestorId) {
		return new ResponseEntity<>(accountService.getRequestsByRequestor(requestorId), HttpStatus.OK);
	}
	@GetMapping("request/receiver")
	public ResponseEntity<List<Requests>> getRequestsByReceiver(@RequestParam long requestorId) {
		return new ResponseEntity<>(accountService.getRequestByReceiver(requestorId), HttpStatus.OK);
	}
}
