package function;

import java.util.List;

import model.Account;
import model.User;

public interface UserFunctions {

	public void addUser(String firstName, String lastName, int age, String adress, String postalZip,
			String town, String country, String phoneNumber);
	public void deleteUser(User user);
	public void updateUser(User user, int age, String adress, String postalZip,
			String town, String country, String phoneNumber);
	public void readUser(User user);
	public List<Account> accountsList(User user);
}
