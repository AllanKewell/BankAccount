package exceptions;

public class UnacceptableAccountOperationException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UnacceptableAccountOperationException() {
		super();
	}

	public UnacceptableAccountOperationException(String message, Throwable cause) {
		super(message, cause);
	}

	public UnacceptableAccountOperationException(String message) {
		super(message);
	}

	public UnacceptableAccountOperationException(Throwable cause) {
		super(cause);
	}

	
}
