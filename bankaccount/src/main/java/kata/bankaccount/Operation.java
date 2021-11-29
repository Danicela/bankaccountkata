package kata.bankaccount;

import java.math.BigDecimal;
import java.util.Date;

public class Operation 
{
	private OperationType type;
	private Date date;
	private BigDecimal amount;
	private BigDecimal balance;

	public enum OperationType
	{
		DEPOSIT, WITHDRAWAL
	}

	public Operation(OperationType type, Date date, BigDecimal amount, BigDecimal balance)
	{
		this.type = type;
		this.date = date;
		this.amount = amount;
		this.balance = balance;
	}

	public OperationType getType() {
		return type;
	}

	public Date getDate() {
		return date;
	}

	public double getAmount() {
		return amount.doubleValue();
	}

	public double getBalance() {
		return balance.doubleValue();
	}
	
}
