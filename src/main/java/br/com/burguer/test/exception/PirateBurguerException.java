package br.com.burguer.test.exception;

public class PirateBurguerException extends RuntimeException {

	private static final long serialVersionUID = 2003606444908748452L;

	public PirateBurguerException(String string) {
		getMessage().concat(string);		
	}

}
