package kata.bankaccount;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import kata.bankaccount.Operation.OperationType;
import kata.bankaccount.exceptions.IncorrectAmountException;
import kata.bankaccount.exceptions.InsufficientBalanceException;

/**
 * Think of your personal bank account experience :
 * Deposit and Withdrawal
 * Account statement (date, amount, balance)
 * Statement printing
 */
public class BankAccount 
{	
	private BigDecimal balance;
	private List<Operation> operations = new ArrayList<Operation>();
	
	public static final int SCALE = 2;
	public static final RoundingMode ROUNDING_MODE = RoundingMode.HALF_UP;
	
	
	public BankAccount()
	{
		balance = new BigDecimal(0.0);
	}
	
	public double getBalance()
	{
		return balance.doubleValue();
	}
	
	//US 1 - I want to make a deposit in my account
	public void deposit(double amount) throws IncorrectAmountException
	{
		BigDecimal amountBD = new BigDecimal(amount).setScale(SCALE, ROUNDING_MODE);
		
		if(amountBD.compareTo(new BigDecimal(0.0)) <= 0)
			throw new IncorrectAmountException(amountBD);
		
		balance = balance.add(amountBD);
		Operation operation = new Operation(OperationType.DEPOSIT, new Date(), amountBD, balance);
		operations.add(operation);
		
	}
	
	//US 2 - I want to make a withdrawal from my account
	public void withdraw(double amount) throws IncorrectAmountException, InsufficientBalanceException
	{
		BigDecimal amountBD = new BigDecimal(amount).setScale(SCALE, ROUNDING_MODE);
		
		if(amountBD.compareTo(new BigDecimal(0.0)) <= 0)
			throw new IncorrectAmountException(amountBD);
		
		if(amountBD.compareTo(balance) > 0)
			throw new InsufficientBalanceException(amountBD);
		
		balance = balance.subtract(amountBD);
		Operation operation = new Operation(OperationType.WITHDRAWAL, new Date(), amountBD, balance);
		operations.add(operation);
		
	}
	
	//US 2 - to retrieve (some or) all of my savings
	public double withdrawAll()
	{
		BigDecimal previousBalance = balance;
		Double allBalance = balance.doubleValue();
		
		balance = balance.subtract(balance);
		Operation operation = new Operation(OperationType.WITHDRAWAL, new Date(), previousBalance, balance);
		operations.add(operation);
		
		return allBalance;
	}
	
	//US 3 - I want to see the history (operation, date, amount, balance) of my operations
	public List<Operation> getHistory()
	{
		return operations;
	}
	
	public String getHistoryAsString()
	{
		StringBuilder sb = new StringBuilder();
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		
		for(Operation operation : operations)
		{	
			String s = new String(operation.getType() + " - " + sdf.format(operation.getDate()) + " - " 
					+ operation.getAmount()  + " - " + operation.getBalance() + "\n");
			sb.append(s);
		
		}
		
		return sb.toString();
	}
	
}
