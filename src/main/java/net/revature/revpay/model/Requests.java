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
	@JsonBackReference
	@ManyToOne
	Account Requestor;
	@JsonBackReference
	@ManyToOne
	Account Receiver;
	public Requests(double balance, Account requestor, Account receiver) {
		super();
		this.balance = balance;
		Requestor = requestor;
		Receiver = receiver;
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
		return Requestor;
	}
	public void setRequestor(Account requestor) {
		Requestor = requestor;
	}
	public Account getReceiver() {
		return Receiver;
	}
	public void setReceiver(Account receiver) {
		Receiver = receiver;
	}
}
