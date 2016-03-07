package NetworkRepresentation;

import java.util.List;

import net.sf.oval.ConstraintViolation;
import net.sf.oval.Validator;

public class ValidateNetwork {
	
	

	public static void validateBlock(Block b) throws InvalidNetworkException
	{
		Validator validator = new Validator();

		List<ConstraintViolation> violations = validator.validate(b);
		
		if(violations.size()>0)
		{
		  System.out.println("Block " + b + " is invalid.");
		  throw new InvalidNetworkException(violations);
		}
	}
	
	public static void validatePoint(Point p) throws InvalidNetworkException
	{
		Validator validator = new Validator();

		List<ConstraintViolation> violations = validator.validate(p);
		
		if(violations.size()>0)
		{
		  System.out.println("Point " + p + " is invalid.");
		  throw new InvalidNetworkException(violations);
		}
	}
	
	public static void validateNetwork()
	{
		
	}
	

}
