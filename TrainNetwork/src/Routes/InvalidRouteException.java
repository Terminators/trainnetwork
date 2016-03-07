package Routes;

import java.util.List;

import net.sf.oval.ConstraintViolation;

public class InvalidRouteException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4398919018721071888L;

	public InvalidRouteException(String message)
	{
		super(message);
	}
	
	public InvalidRouteException(List<ConstraintViolation> violations)
	{
		super();
		
		for (ConstraintViolation violation : violations)
		{
			System.out.println(violation.getMessage());
		}
	}

}
