package net.revature.revpay.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Transactions {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	Long id;
	@ManyToOne
	@JoinColumn(nullable=false)
	Account sender;
	@ManyToOne
	@JoinColumn(nullable=false)
	Account receiver;
	Double balance;
	String UTCDate;
	public String getUTCDate() {
		return UTCDate;
	}
	public void setUTCDate(String uTCDate) {
		UTCDate = uTCDate;
	}
	public Transactions() {
		super();
	}
	public Transactions(Account sender, Account receiver, Double balance) {
		super();
		this.sender = sender;
		this.receiver = receiver;
		this.balance = balance;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Account getSender() {
		return sender;
	}
	public void setSender(Account sender) {
		this.sender = sender;
	}
	public Account getReceiver() {
		return receiver;
	}
	public void setReceiver(Account receiver) {
		this.receiver = receiver;
	}
	public Double getBalance() {
		return balance;
	}
	public void setBalance(Double balance) {
		this.balance = balance;
	}
}