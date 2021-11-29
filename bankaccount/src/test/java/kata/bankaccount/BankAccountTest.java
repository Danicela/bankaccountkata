package kata.bankaccount;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

import kata.bankaccount.Operation.OperationType;
import kata.bankaccount.exceptions.IncorrectAmountException;
import kata.bankaccount.exceptions.InsufficientBalanceException;

public class BankAccountTest
{
	@Test
	public void testDeposit() throws IncorrectAmountException
	{
		BankAccount account = new BankAccount();
		account.deposit(20.21);
		assertEquals(20.21, account.getBalance(), 0.0);
		
		account.deposit(15.23);
		assertEquals(35.44, account.getBalance(), 0.0);
		
		account.deposit(2); //integer
		assertEquals(37.44, account.getBalance(), 0.0);
		
		try{account.deposit(0);}
		catch(Exception e) {assertEquals(IncorrectAmountException.class, e.getClass());}
		
		try{account.deposit(-1);}
		catch(Exception e) {assertEquals(IncorrectAmountException.class, e.getClass());}
		
		assertEquals(37.44, account.getBalance(), 0.0);
		
		//testGetHistory
		
		List<Operation> operations = account.getHistory();
		assertEquals(OperationType.DEPOSIT, operations.get(0).getType());
		assertEquals(20.21, operations.get(0).getAmount(), 0.0);
		assertEquals(20.21, operations.get(0).getBalance(), 0.0);
		
		assertEquals(OperationType.DEPOSIT, operations.get(1).getType());
		assertEquals(15.23, operations.get(1).getAmount(), 0.0);
		assertEquals(35.44, operations.get(1).getBalance(), 0.0);
		
		assertEquals(OperationType.DEPOSIT, operations.get(2).getType());
		assertEquals(2.0, operations.get(2).getAmount(), 0.0);
		assertEquals(37.44, operations.get(2).getBalance(), 0.0);
		
		assertEquals(3, operations.size());
		
		//System.out.println(account.getHistoryAsString());
	}
	
	@Test
	public void testWithdraw() throws IncorrectAmountException, InsufficientBalanceException
	{
		BankAccount account = new BankAccount();
		account.deposit(80.33); //requires testDeposit() is OK
		
		try{account.withdraw(0);}
		catch(Exception e) {assertEquals(IncorrectAmountException.class, e.getClass());}
		
		try{account.withdraw(-2.4);}
		catch(Exception e) {assertEquals(IncorrectAmountException.class, e.getClass());}
		
		try{account.withdraw(80.34);}
		catch(Exception e) {assertEquals(InsufficientBalanceException.class, e.getClass());}
		
		assertEquals(80.33, account.getBalance(), 0.0);
		
		account.withdraw(20.11);
		assertEquals(60.22, account.getBalance(), 0.0);
		
		account.withdraw(10); //integer
		assertEquals(50.22, account.getBalance(), 0.0);
		
		//testGetHistory
		
		List<Operation> operations = account.getHistory();
		assertEquals(OperationType.DEPOSIT, operations.get(0).getType());
		assertEquals(80.33, operations.get(0).getAmount(), 0.0);
		assertEquals(80.33, operations.get(0).getBalance(), 0.0);
		
		assertEquals(OperationType.WITHDRAWAL, operations.get(1).getType());
		assertEquals(20.11, operations.get(1).getAmount(), 0.0);
		assertEquals(60.22, operations.get(1).getBalance(), 0.0);
		
		assertEquals(OperationType.WITHDRAWAL, operations.get(2).getType());
		assertEquals(10.0, operations.get(2).getAmount(), 0.0);
		assertEquals(50.22, operations.get(2).getBalance(), 0.0);
		
		assertEquals(3, operations.size());
				
		//System.out.println(account.getHistoryAsString());
	}
	
	@Test
	public void testWithdrawAll() throws IncorrectAmountException
	{
		BankAccount account = new BankAccount();
		double all = account.withdrawAll();
		
		assertEquals(0, all, 0.0);
		assertEquals(0, account.getBalance(), 0.0);
		
		account.deposit(62.89); //requires testDeposit() is OK
		
		all = account.withdrawAll();
		
		assertEquals(62.89, all, 0.0);
		assertEquals(0, account.getBalance(), 0.0);
		
		//testGetHistory
		
		List<Operation> operations = account.getHistory();
		assertEquals(OperationType.WITHDRAWAL, operations.get(0).getType());
		assertEquals(0, operations.get(0).getAmount(), 0.0);
		assertEquals(0.0, operations.get(0).getBalance(), 0.0);
		
		assertEquals(OperationType.WITHDRAWAL, operations.get(2).getType());
		assertEquals(62.89, operations.get(2).getAmount(), 0.0);
		assertEquals(0.0, operations.get(2).getBalance(), 0.0);
		
		assertEquals(3, operations.size());
		
		//System.out.println(account.getHistoryAsString());
	}
	
}
