package net.revature.revpay.Exceptions;

public class InputException extends Exception {
	public InputException(String message) {
		super(message);
	}
	public InputException() {
		super("There was no message!");
	}

}
