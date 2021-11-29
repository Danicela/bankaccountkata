package kata.bankaccount.exceptions;

import java.math.BigDecimal;

public class InsufficientBalanceException extends Exception
{
	private BigDecimal amount;

	
	public InsufficientBalanceException(BigDecimal amount)
	{
		super();
		this.amount = amount;
	}
	
	public String toString()
	{
		return "Insufficient balance in account to withdraw : " + amount;
	}
	
}
