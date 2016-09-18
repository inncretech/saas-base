package com.inncretech.encryption.bean;

import java.io.Serializable;

public class CardNumber implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String number;

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

}
