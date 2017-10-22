package pl.playerony.exception;

public class RestException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public RestException(String message){
		super(message);
	}

	public RestException(String message, Throwable cause) {
		super(message, cause);
	}
	
}
