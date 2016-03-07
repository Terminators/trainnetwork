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
	
	public static void validate(Point p) throws InvalidNetworkException
	{
		for (Section section : p.getNeighList())
		{
			if (section == null)
			{
				throw new InvalidNetworkException("Point " + p + "has to have 3 neighbours.");
			}
			
			if (section instanceof Point)
			{
				throw new InvalidNetworkException("Point " + p + "'s neighbours have to be blocks .");

			}
		}
	}
	
	public static void validateNetwork()
	{
		
	}
	

}
