package kata.bankaccount.exceptions;

import java.math.BigDecimal;

public class IncorrectAmountException extends Exception
{
	private BigDecimal amount;
	
	
	public IncorrectAmountException(BigDecimal amount)
	{
		super();
		this.amount = amount;
	}
	
	public String toString()
	{
		return "Incorrect amount (ex: 0 or negative) : " + amount;
	}
	
}
