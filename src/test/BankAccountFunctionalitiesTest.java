package test;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.Random;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import exceptions.UnacceptableAccountOperationException;
import functionalities.BankAccountFunctionalities;
import model.Account;
import model.User;

public class BankAccountFunctionalitiesTest {

	protected BankAccountFunctionalities baf;
	protected User user;
	protected Account account;
	protected Account account2;
	
	@Before
	public void setUp() throws Exception {
		baf = new BankAccountFunctionalities();
		user = baf.addUser("Ethan", "Levasseur", 25, "36, avenue de la liberté", 
				"78990", "Élancourt", "FRANCE", "0678787878");
		Random rd = new Random();
		String accountNumber = "";
		String accountNumber2 = "";

		for(int i = 0; i < 11; i++) {
			Integer num = rd.nextInt(10);
			accountNumber = accountNumber.concat(num.toString());
		}

		try {
			account = baf.addAccount("CREDIT AGRICOLE ILE-DE-FRANCE", 
					accountNumber, "FR781783204170" + accountNumber + "33", "AGRIFRIDF78", new Date(), 400, -500);
			baf.linkAccount(account, user);
		} catch (UnacceptableAccountOperationException e) {
			e.printStackTrace();
		}

		for(int i = 0; i < 11; i++) {
			Integer num = rd.nextInt(10);
			accountNumber2 = accountNumber2.concat(num.toString());
		}
		
		try {
			account2 = baf.addAccount("HSBC SWITZERLAND", accountNumber2, 
					"CH218434001281" + accountNumber2 + "19", "HSCHGEN19", new Date(), 10000, -1000);
			baf.linkAccount(account2, user);
		} catch (UnacceptableAccountOperationException e) {
			e.printStackTrace();
		}
	}

	@After
	public void tearDown() throws Exception {
		baf.saveBankAccountMap();
	}

	@Test
	public void testAddUser() {
		try {
			User tempUser = baf.addUser("Roger", "Milland", 51, "54, rue de la soif", 
					"78300", "Poissy", "FRANCE", "0633333333");
		} catch (UnacceptableAccountOperationException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testAddAccount() {
		Random rd = new Random();
		String accountNumber = "";
		String accountNumber2 = "";

		for(int i = 0; i < 11; i++) {
			Integer num = rd.nextInt(10);
			accountNumber = accountNumber.concat(num.toString());
		}

		try {
			account = baf.addAccount("CREDIT AGRICOLE ILE-DE-FRANCE", 
					accountNumber, "FR781783204170" + accountNumber + "33", "AGRIFRIDF78", new Date(), 400, -500);
			baf.linkAccount(account, user);
		} catch (UnacceptableAccountOperationException e) {
			e.printStackTrace();
		}

		for(int i = 0; i < 11; i++) {
			Integer num = rd.nextInt(10);
			accountNumber2 = accountNumber2.concat(num.toString());
		}
		
		try {
			account2 = baf.addAccount("HSBC SWITZERLAND", accountNumber2, 
					"CH218434001281" + accountNumber2 + "19", "HSCHGEN19", new Date(), 10000, -1000);
			baf.linkAccount(account2, user);
		} catch (UnacceptableAccountOperationException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testReadUsers() {
		baf.readUsers();
	}

	@Test
	public void testReadAccounts() {
		baf.readAccounts();
	}

	@Test
	public void testDeposit() {
		try {
			for(Account account : user.getAccountList()) {
				baf.deposit(account, 145);
			}
		} catch (UnacceptableAccountOperationException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testWithdraw() {
		try {
			for(Account account : user.getAccountList()) {
				baf.withdraw(account, 200);
			}
		} catch (UnacceptableAccountOperationException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testBalancesSum() {
		Integer sum = baf.balancesSum(user);
		System.out.println("Somme totale des comptes de " + user.getFirstName() + " " + user.getLastName() + 
				" : " + sum.toString() + "€");
	}
}
