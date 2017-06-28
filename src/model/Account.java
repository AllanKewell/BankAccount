package model;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @author Fabien Buisson
 *
 */
public class Account implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String bank;
	private String accountNumber;
	private String iban;
	private String bic;
	private Date creationDate;
	private Integer balance;
	private Integer limitBalance;

	public Account(String bank, String accountNumber, String iban, 
			String bic, Date creationDate, int balance, int limitBalance) {
		this.bank = bank;
		this.accountNumber = accountNumber;
		this.iban = iban;
		this.bic = bic;
		this.creationDate = creationDate;
		this.balance = balance;
		this.limitBalance = limitBalance;
	}

	/**
	 * @return the bank
	 */
	public String getBank() {
		return bank;
	}

	/**
	 * @param bank the bank to set
	 */
	public void setBank(String bank) {
		this.bank = bank;
	}

	/**
	 * @return the iban
	 */
	public String getIban() {
		return iban;
	}

	/**
	 * @param iban the iban to set
	 */
	public void setIban(String iban) {
		this.iban = iban;
	}

	/**
	 * @return the bic
	 */
	public String getBic() {
		return bic;
	}

	/**
	 * @param bic the bic to set
	 */
	public void setBic(String bic) {
		this.bic = bic;
	}

	/**
	 * @return the creationDate
	 */
	public Date getCreationDate() {
		return creationDate;
	}

	/**
	 * @param creationDate the creationDate to set
	 */
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	/**
	 * @return the balance
	 */
	public Integer getBalance() {
		return balance;
	}

	/**
	 * @param balance the balance to set
	 */
	public void setBalance(Integer balance) {
		this.balance = balance;
	}

	/**
	 * @return the accountNumber
	 */
	public String getAccountNumber() {
		return accountNumber;
	}

	/**
	 * @param accountNumber the accountNumber to set
	 */
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	/**
	 * @return the limiteBalance
	 */
	public Integer getLimitBalance() {
		return limitBalance;
	}

	/**
	 * @param limiteBalance the limiteBalance to set
	 */
	public void setLimitBalance(Integer limitBalance) {
		this.limitBalance = limitBalance;
	}
}
