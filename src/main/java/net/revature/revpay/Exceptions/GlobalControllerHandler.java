package net.revature.revpay.Exceptions;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@ControllerAdvice
class GlobalControllerHandler {
	@ExceptionHandler(InputException.class)
	public ResponseEntity<String> handleConflict(InputException e) {
		return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);		
	}
}