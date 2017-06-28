package functionalities;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import data.MapData;
import function.BankAccountFunctions;
import model.Account;
import model.User;

public class BankAccountFunctionalities implements BankAccountFunctions {

	private MapData mapData;
	private Map<User, List<Account>> baMap;
	
	public BankAccountFunctionalities() {
		mapData = new MapData();
		baMap = new HashMap<User, List<Account>>(mapData.getBankAccountMap());
	}

	@Override
	public User addUser(String firstName, String lastName, Integer age, String adress, String zipCode, String town,
			String country, String phoneNumber) {

		User newUser = new User(firstName, lastName, age, adress, zipCode, town, country, phoneNumber);
		
		return newUser;
	}

	@Override
	public void deleteUser(User user) {
		if(baMap.containsKey(user))
			baMap.remove(user);
		
	}

	@Override
	public User updateUser(User user, Integer age, String adress, String zipCode, String town, String country,
			String phoneNumber) {

		User updatedUser = user;

		if(age != null)
			updatedUser.setAge(age);
		if(adress != null)
			updatedUser.setAdress(adress);
		if(zipCode != null)
			updatedUser.setZipCode(zipCode);
		if(town != null)
			updatedUser.setTown(town);
		if(country != null)
			updatedUser.setCountry(country);
		if(phoneNumber != null)
			updatedUser.setPhoneNumber(phoneNumber);

		return updatedUser;
	}

	@Override
	public void readUser(User user) {
		System.out.println("Prénom : " + user.getFirstName());
		System.out.println("Nom : " + user.getLastName());
		System.out.println("Age : " + user.getAge());
		System.out.println("adresse : " + user.getAdress());
		System.out.println("Code postal : " + user.getZipCode());
		System.out.println("Ville : " + user.getTown());
		System.out.println("Pays : " + user.getCountry());
		System.out.println("Numéro téléphone : " + user.getPhoneNumber());
	}

	@Override
	public void accountsList(User user) {
		System.out.println("Compte(s) de " + user.getFirstName() + " " + user.getLastName() + " :");

		for(int i = 0; i < user.getAccountList().size(); i++) {
			Account account = user.getAccountList().get(i);

			System.out.println("Compte n°" + i);
			System.out.println("Banque : " + account.getBank());
			System.out.println("N° de compte : " + account.getAccountNumber());
			System.out.println("IBAN : " + account.getIban());
			System.out.println("BIC : " + account.getBic());
			System.out.println("Date de création : " + account.getCreationDate());
			System.out.println("Solde : " + account.getBalance());
			System.out.println("Plafond : " + account.getLimitBalance());
		}
	}

	@Override
	public Account addAccount(User user, String bank, String accountNumber, String iban, String bic, Date creationDate,
			Integer balance, Integer limitBalance) {

		Account newAccount = new Account(bank, accountNumber, iban, bic, creationDate, balance, limitBalance);
		newAccount = linkAccount(newAccount, user);

		return newAccount;
	}

	@Override
	public Account linkAccount(Account account, User user) {
		user.getAccountList().add(account);
		return account;
	}

	@Override
	public void deleteAccount(Account account) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Account updateAccount(Account account, Integer balance, Integer limitBalance) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void readAccount(Account account) {
		System.out.println("Banque : " + account.getBank());
		System.out.println("N° de compte : " + account.getAccountNumber());
		System.out.println("IBAN : " + account.getIban());
		System.out.println("BIC : " + account.getBic());
		System.out.println("Date de création : " + account.getCreationDate());
		System.out.println("Solde : " + account.getBalance());
		System.out.println("Plafond : " + account.getLimitBalance());
	}

	@Override
	public void withdraw(Account account, Integer money) {
		if(money < 0) {
			System.out.println("Opération impossible ! Vous devez rentrer une valeur positive.");
		}
		
		Integer newBalance = account.getBalance() - money;
		
		if(account.getBalance() < account.getLimitBalance()) {
			System.out.println("Retrait impossible. Vous avez déjà dépassé votre plafond.");
		}
		
	}

	@Override
	public void deposit(Account account, Integer money) {
		account.setBalance(account.getBalance() + money);
	}

	@Override
	public Integer balancesSum(User user) {
		Integer sumBalance = 0;
		
		for(Account account : user.getAccountList()) {
			sumBalance += account.getBalance();
		}

		return sumBalance;
	}

	/**
	 * 
	 * @param user
	 */
	public void addBankAccountEntryMap(User user) {
		baMap.put(user, user.getAccountList());
	}

	/**
	 * 
	 */
	public void saveMap() {
		mapData.setBankAccountMap(baMap);
	}

	/**
	 * @return the mapData
	 */
	public MapData getMapData() {
		return mapData;
	}

	/**
	 * @param mapData the mapData to set
	 */
	public void setMapData(MapData mapData) {
		this.mapData = mapData;
	}

	/**
	 * @return the baMap
	 */
	public Map<User, List<Account>> getBaMap() {
		return baMap;
	}

	/**
	 * @param baMap the baMap to set
	 */
	public void setBaMap(Map<User, List<Account>> baMap) {
		this.baMap = baMap;
	}

}
