package exception;

/**
 * DaoException is a class created to provide the exceptions linked to dao type structures
 */
public class DaoException extends Exception{
	private static final long serialVersionUID = 1L;

	public DaoException() {
		super();
    }
    
	/**
	 * Generate one message passed as cause 
	 * @param message
	 * @param cause
	 */
	public DaoException(String message, Throwable cause) {
		super(message, cause);
	}
	/**
	 * Return message passed as exception
	 * @param message
	 */
	public DaoException(String message) {
		super(message);
	}
    
}