package functionalities;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import data.MapData;
import exceptions.UnacceptableAccountOperationException;
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
			String country, String phoneNumber) throws UnacceptableAccountOperationException {

		User newUser = new User(firstName, lastName, age, adress, zipCode, town, country, phoneNumber);

		for (Map.Entry<User, List<Account>> entry : baMap.entrySet()) {
			User currentUser = entry.getKey();

			if(currentUser.equals(newUser))
				throw new UnacceptableAccountOperationException("Création impossible. Un utilisateur existe déjà avec ces données.");
		}

		return newUser;
	}

	@Override
	public User deleteUser(User user) {
		if(baMap.containsKey(user))
			baMap.remove(user);

		return null;
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

		if(baMap.containsKey(user)) {
			baMap.remove(user);
			baMap.put(updatedUser, updatedUser.getAccountList());
		}

		return updatedUser;
	}

	@Override
	public void readUsers() {

		for (Map.Entry<User, List<Account>> entry : baMap.entrySet()) {

			User user = entry.getKey();

			System.out.println("Prénom : " + user.getFirstName());
			System.out.println("Nom : " + user.getLastName());
			System.out.println("Age : " + user.getAge());
			System.out.println("adresse : " + user.getAdress());
			System.out.println("Code postal : " + user.getZipCode());
			System.out.println("Ville : " + user.getTown());
			System.out.println("Pays : " + user.getCountry());
			System.out.println("Numéro téléphone : " + user.getPhoneNumber());
			System.out.println("");
		}
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
			Integer balance, Integer limitBalance) throws UnacceptableAccountOperationException {

		Account newAccount = new Account(bank, accountNumber, iban, bic, creationDate, balance, limitBalance);

		for (Map.Entry<User, List<Account>> entry : baMap.entrySet()) {
			List<Account> accounts = entry.getValue();

			for(Account account : accounts) {
				if(account.getAccountNumber().equals(accountNumber) || account.getIban().equals(iban) || account.getBic().equals(bic)) {
					throw new UnacceptableAccountOperationException("Création impossible ! Un compte "
							+ "existe déjà avec une des informations suivantes : n° de compte, IBAN, BIC");
				}
			}
		}

		newAccount = linkAccount(newAccount, user);

		return newAccount;
	}

	@Override
	public Account linkAccount(Account account, User user) {
		user.getAccountList().add(account);
		return account;
	}

	@Override
	public Account deleteAccount(Account account) {

		for (Map.Entry<User, List<Account>> entry : baMap.entrySet()) {
			User currentUser = entry.getKey();
			List<Account> accounts = entry.getValue();

			for(int i = 0; i < accounts.size(); i++) {
				if(accounts.get(i).getAccountNumber().equals(account.getAccountNumber()))
					accounts.remove(account);
			}

			if(!accounts.equals(currentUser.getAccountList())) {
				currentUser.setAccountList(accounts);
				baMap.replace(currentUser, currentUser.getAccountList());
				break;
			}
		}
		return null;
	}

	@Override
	public Account updateAccount(Account account, Integer balance, Integer limitBalance) {

		Account updatedAccount = account;
		
		if(balance != null)
			updatedAccount.setBalance(balance);
		if(limitBalance != null)
			updatedAccount.setLimitBalance(limitBalance);
		
		for (Map.Entry<User, List<Account>> entry : baMap.entrySet()) {
			
			User currentUser = entry.getKey();
			List<Account> accounts = entry.getValue();

			for(int i = 0; i < accounts.size(); i++) {
				if(accounts.get(i).getAccountNumber().equals(updatedAccount.getAccountNumber()))
					accounts.set(i, updatedAccount);
			}

			if(!accounts.equals(currentUser.getAccountList())) {
				currentUser.setAccountList(accounts);
				baMap.replace(currentUser, currentUser.getAccountList());
				break;
			}
			
		}
		
		return null;
	}

	@Override
	public void readAccounts() {

		for (Map.Entry<User, List<Account>> entry : baMap.entrySet()) {

			List<Account> accounts = entry.getValue();

			for(Account account : accounts) {
				System.out.println("Banque : " + account.getBank());
				System.out.println("N° de compte : " + account.getAccountNumber());
				System.out.println("IBAN : " + account.getIban());
				System.out.println("BIC : " + account.getBic());
				System.out.println("Date de création : " + account.getCreationDate());
				System.out.println("Solde : " + account.getBalance());
				System.out.println("Plafond : " + account.getLimitBalance());
				System.out.println("");
			}
		}
	}

	@Override
	public void withdraw(Account account, Integer money) throws UnacceptableAccountOperationException {
		
		// Si le montant du retrait est négatif ou 0
		if(money <= 0) {
			throw new UnacceptableAccountOperationException("Opération impossible ! Vous devez rentrer une valeur positive et supérieur à 0.");
		}

		Integer newBalance = account.getBalance() - money;
		
		// Si le nouveau solde est inférieur au plafond du compte
		if(newBalance < account.getLimitBalance()) {
			throw new UnacceptableAccountOperationException("Retrait impossible. Vous dépassé votre plafond.");
		}

		account.setBalance(newBalance);
		
	}

	@Override
	public void deposit(Account account, Integer money) throws UnacceptableAccountOperationException {

		// Si le montant du retrait est négatif ou 0
		if(money <= 0) {
			throw new UnacceptableAccountOperationException("Opération impossible ! Vous devez rentrer une valeur positive et supérieur à 0.");
		}

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
	public void saveBankAccountMap() {
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
