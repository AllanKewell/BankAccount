package data;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.Account;
import model.User;

/**
 * 
 * @author Fabien Buisson
 *
 */
public class MapData {

	/**
	 * 
	 */
	public static final String BANK_ACCOUNT_FILEPATH = "serialize/bank_account.ser";

	private Map<User, List<Account>> bankAccountMap = null;
	private File dir = new File("serialize");

	/**
	 * 
	 * @return
	 */
	public void initBankAccountSer() {

		dir.mkdirs();

		if(bankAccountMap == null) {
			unserialize();
		}

		if(bankAccountMap == null) {
			bankAccountMap = new HashMap<User, List<Account>>();
		}
	}

	/**
	 * 
	 */
	public MapData() {
		initBankAccountSer();
	}

	/**
	 * 
	 */
	public void serialize() {
		try {
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(BANK_ACCOUNT_FILEPATH));
			oos.writeObject(bankAccountMap);
			oos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*
	 * 
	 */
	@SuppressWarnings("unchecked")
	private void unserialize() {

		ObjectInputStream ois = null;
		try {
			ois = new ObjectInputStream(new FileInputStream(BANK_ACCOUNT_FILEPATH));
			bankAccountMap = (Map<User, List<Account>>) ois.readObject();
			ois.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(ois == null) {
				try {
					ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(BANK_ACCOUNT_FILEPATH));
					oos.writeObject(null);
					oos.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * @return the bankAccountMap
	 */
	public Map<User, List<Account>> getBankAccountMap() {
		return bankAccountMap;
	}

	/**
	 * @param bankAccountMap the bankAccountMap to set
	 */
	public void setBankAccountMap(Map<User, List<Account>> bankAccountMap) {
		this.bankAccountMap = bankAccountMap;
	}


}
