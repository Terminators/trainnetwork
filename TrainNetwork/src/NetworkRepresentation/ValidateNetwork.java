package NetworkRepresentation;

import java.util.ArrayList;
import java.util.HashMap;
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
	
	public static void validateSignals(HashMap<Integer, Block> signalMap) throws InvalidNetworkException
	{
		for (int i = 0; i < signalMap.keySet().size() - 1; i++)
		{
			List<Integer> keyList = new ArrayList<Integer>();
			keyList.addAll(signalMap.keySet());
			
			//if there are two of the same signal ids, then throw an exception
			if (keyList.get(i) == keyList.get(i + 1))
			{
				throw new InvalidNetworkException("Cannot have two of the same signal in the network");
			}

		}
	}
}
