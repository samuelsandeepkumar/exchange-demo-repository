package com.adp.assignment.exchangedemo.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Coins {
	
	@Id
	private int id;
	
	private double denomination;
	private String denominationDescription;
	private int coinCount;
	
	public Coins() {
		
	}
	
	public Coins(int id, double denomination, String denominationDescription, int coinCount) {
		this.id = id;
		this.denomination = denomination;
		this.denominationDescription = denominationDescription;
		this.coinCount = coinCount;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public double getDenomination() {
		return denomination;
	}
	
	public void setDenomination(double denomination) {
		this.denomination = denomination;
	}
	
	public String getDenominationDescription() {
		return denominationDescription;
	}
	
	public void setDenominationDescription(String denominationDescription) {
		this.denominationDescription = denominationDescription;
	}

	public int getCoinCount() {
		return coinCount;
	}

	public void setCoinCount(int coinCount) {
		this.coinCount = coinCount;
	}
	
	@Override
	public String toString() {
		return "id : "+this.id + " coinType : "+this.denomination + " coinDesc : "+this.denominationDescription + " coinCount : "+this.coinCount;
	}
	
}
