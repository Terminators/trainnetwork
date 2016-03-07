package NetworkRepresentation;

import java.util.List;

import net.sf.oval.ConstraintViolation;

public class InvalidNetworkException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1469467796994390104L;

	public InvalidNetworkException(String message)
	{
		super(message);
	}
	

	public InvalidNetworkException(List<ConstraintViolation> violations)
	{
		super();
		
		for (ConstraintViolation violation : violations)
		{
			System.out.println(violation.getMessage());
		}
	}

}
