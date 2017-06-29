package function;

import java.util.Date;

import exceptions.UnacceptableAccountOperationException;
import model.Account;
import model.User;

public interface BankAccountFunctions {

	public User addUser(String firstName, String lastName, Integer age, String adress, String zipCode,
			String town, String country, String phoneNumber);
	public User deleteUser(User user);
	public User updateUser(User user, Integer age, String adress, String zipCode,
			String town, String country, String phoneNumber);
	public void readUsers();
	public void accountsList(User user);
	public Account addAccount(User user, String bank, String accountNumber, String iban, 
			String bic, Date creationDate, Integer balance, Integer limitBalance) throws UnacceptableAccountOperationException;
	public Account linkAccount(Account account, User user);
	public Account deleteAccount(Account account);
	public Account updateAccount(Account account, Integer balance, Integer limitBalance);
	public void readAccounts();
	public void withdraw(Account account, Integer money) throws UnacceptableAccountOperationException;
	public void deposit(Account account, Integer money) throws UnacceptableAccountOperationException;
	public Integer balancesSum(User user);
}
