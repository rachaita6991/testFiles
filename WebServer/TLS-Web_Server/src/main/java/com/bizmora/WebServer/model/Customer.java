package com.bizmora.WebServer.model;

public class Customer {

    private int custNo;
    private String name;
    private String country;
    private String bankOutput;
    
	public Customer() {
	
	}

	public Customer(int custNo, String name, String country,String bankOutput) {
		this.custNo = custNo;
		this.name = name;
		this.country = country;
		this.bankOutput = bankOutput;
	}

	public int getCustNo() {
		return custNo;
	}

	public void setCustNo(int custNo) {
		this.custNo = custNo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}
    
	public void setBankOutput(String bankOutput) {
		this.bankOutput = bankOutput;
	}

	public String getBankOutput() {
		return bankOutput;
	}
    
}
