package function;

import java.util.Date;
import model.Account;
import model.User;

public interface AccountFunctions {

	public void addAccount(String bank, String accountNumber, String iban, 
			String bic, Date creationDate, int balance, int limitBalance);
	public void linkAccount(User user);
	public void deleteAccount(Account account);
	public void updateAccount(Account account, int balance, int limitBalance);
	public void readAccount(User user, String bank);
	public void withdraw(Account account);
	public void deposit(Account account);
	public int balancesSum(User user);
}
