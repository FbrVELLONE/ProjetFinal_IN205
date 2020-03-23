package exception;

/**
 * ServiceException is a class created to provide the exceptions linked to service type structures
 */
public class ServiceException extends Exception{
	private static final long serialVersionUID = 1L;

	public ServiceException() {
		super();
    }
    
	/**
	 * Generate one message passed as cause 
	 * @param message
	 * @param cause
	 */
	public ServiceException(String message, Throwable cause) {
		super(message, cause);
		System.out.println("\n");
		cause.printStackTrace();
	}
	/**
	 * Return message passed as exception
	 * @param message
	 */
	public ServiceException(String message) {
		super(message);
	}
    
}