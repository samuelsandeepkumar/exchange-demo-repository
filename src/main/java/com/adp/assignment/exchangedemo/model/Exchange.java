package com.adp.assignment.exchangedemo.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "EXCHANGE_MASTER")
public class Exchange {
	
	
	private int id;
	private double denominationPreference;
	private String denominationDescription;
	private int billProduced;
	
	public Exchange() {
		
	}
	
	public Exchange(int id, double denominationPreference, String denominationDescription, int billProduced) {
		super();
		this.id = id;
		this.denominationPreference = denominationPreference;
		this.denominationDescription = denominationDescription;
		this.billProduced = billProduced;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false, precision = 0)
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	@Basic
    @Column(name = "PREFERENCE", nullable = true)
	public double getDenominationPreference() {
		return denominationPreference;
	}
	
	public void setDenominationPreference(double denominationPreference) {
		this.denominationPreference = denominationPreference;
	}
	
	@Basic
    @Column(name = "DESCRIPTION", nullable = true)
	public String getDenominationDescription() {
		return denominationDescription;
	}
	
	public void setDenominationDescription(String denominationDescription) {
		this.denominationDescription = denominationDescription;
	}
	
	@Basic
    @Column(name = "BILL_PRODUCED", nullable = true)
	public int getBillProduced() {
		return billProduced;
	}
	
	public void setBillProduced(int billProduced) {
		this.billProduced = billProduced;
	}
	
}
