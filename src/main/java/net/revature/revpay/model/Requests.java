package net.revature.revpay.model;

import java.util.Objects;

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
	Long requestorId;
	@Column(nullable=false)
	Long receiverId;
	@Column(nullable=false)
	double balance;

	@Override
	public int hashCode() {
		return Objects.hash(balance, id, receiverId, requestorId);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Requests other = (Requests) obj;
		return Double.doubleToLongBits(balance) == Double.doubleToLongBits(other.balance)
				&& Objects.equals(id, other.id) && Objects.equals(receiverId, other.receiverId)
				&& Objects.equals(requestorId, other.requestorId);
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getRequestorId() {
		return requestorId;
	}
	public void setRequestorId(Long requestorId) {
		this.requestorId = requestorId;
	}
	public Long getReceiverId() {
		return receiverId;
	}
	public void setReceiverId(Long receiverId) {
		this.receiverId = receiverId;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	public Requests( Long requestor, Long receiver, double balance) {
		super();
		this.requestorId = requestor;
		this.receiverId = receiver;
		this.balance = balance;
	}
	public Requests() {
		super();
	}
}
