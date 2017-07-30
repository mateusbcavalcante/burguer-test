package br.com.burguer.test.exception;

public class PirateBurguerException extends RuntimeException {

	public PirateBurguerException(String string) {
		getMessage().concat(string);
	}

}
