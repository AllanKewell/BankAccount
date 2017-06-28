package function;

import java.util.Date;

import model.Account;
import model.User;

public interface BankAccountFunctions {

	public User addUser(String firstName, String lastName, Integer age, String adress, String zipCode,
			String town, String country, String phoneNumber);
	public void deleteUser(User user);
	public User updateUser(User user, Integer age, String adress, String zipCode,
			String town, String country, String phoneNumber);
	public void readUser(User user);
	public void accountsList(User user);
	public Account addAccount(User user, String bank, String accountNumber, String iban, 
			String bic, Date creationDate, Integer balance, Integer limitBalance);
	public Account linkAccount(Account account, User user);
	public void deleteAccount(Account account);
	public Account updateAccount(Account account, Integer balance, Integer limitBalance);
	public void readAccount(Account account);
	public void withdraw(Account account, Integer money);
	public void deposit(Account account, Integer money);
	public Integer balancesSum(User user);
}
