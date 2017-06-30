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
	
	@Before
	public void setUp() throws Exception {
		baf = new BankAccountFunctionalities();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testAddUser() {
		try {
			user = baf.addUser("Ethan", "Levasseur", 25, "36, avenue de la liberté", 
					"78990", "Élancourt", "FRANCE", "0678787878");
		} catch (UnacceptableAccountOperationException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testAddAccount() {
		Random rd = new Random();
		String accountNumber = null;

		for(int i = 0; i < 11; i++) {
			Integer num = rd.nextInt(10);
			accountNumber = accountNumber.concat(num.toString());
		}

		try {
			account = baf.addAccount(user, "CREDIT AGRICOLE ILE-DE-FRANCE", 
					accountNumber, "FR781783204170" + accountNumber + "33", "AGRIFRIDF78", new Date(), 400, -500);
		} catch (UnacceptableAccountOperationException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void test() {
		fail("Not yet implemented");
	}

}
