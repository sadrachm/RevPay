package net.revature.revpay.model;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Requests {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	Long id;
	@Column(nullable=false)
	double balance;
	boolean completed;
	@ManyToOne
	Account requestor;
	@ManyToOne
	Account receiver;
	String UTCDate;
	public String getUTCDate() {
		return UTCDate;
	}
	public void setUTCDate(String uTCDate) {
		UTCDate = uTCDate;
	}
	public Requests(double balance, Account requestor, Account receiver) {
		super();
		this.balance = balance;
		this.requestor = requestor;
		this.receiver = receiver;
	}
	public Requests() {
		super();
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	public boolean isCompleted() {
		return completed;
	}
	public void setCompleted(boolean completed) {
		this.completed = completed;
	}
	public Account getRequestor() {
		return requestor;
	}
	public void setRequestor(Account requestor) {
		this.requestor = requestor;
	}
	public Account getReceiver() {
		return receiver;
	}
	public void setReceiver(Account receiver) {
		this.receiver = receiver;
	}
}
