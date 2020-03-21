package exception;

/**
 * ServiceException
 */
public class ServiceException extends Exception{
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
	}
	/**
	 * Return message passed as exception
	 * @param message
	 */
	public ServiceException(String message) {
		super(message);
	}
    
}