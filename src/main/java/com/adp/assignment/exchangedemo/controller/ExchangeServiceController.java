package com.adp.assignment.exchangedemo.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.adp.assignment.exchangedemo.model.Coins;
import com.adp.assignment.exchangedemo.model.ExchangeRepository;

@RestController
@RequestMapping("/exchange-demo")
public class ExchangeServiceController {
	
	@Autowired
	private ExchangeRepository exchangeRepository;
	
	public List<Integer> allowedBills = new ArrayList<>(
		    Arrays.asList(1,2,5,10,20,50,100)
		);
	
	
	@GetMapping("/exchange/{billProduced}/{coinPreference}")
	public String getChange(@PathVariable Integer billProduced, @PathVariable Double coinPreference) {
		int calculatedCoins = (int) (billProduced/coinPreference);
		Coins coins = exchangeRepository.findByDenomination(Double.valueOf(coinPreference), Integer.valueOf(billProduced));
		String returnMessage = "";
		if(coins.getCoinCount() >= calculatedCoins) {
			int coinsRemaining = coins.getCoinCount() - calculatedCoins;
			coins.setCoinCount(coinsRemaining);
			exchangeRepository.save(coins);
			returnMessage = "Change can be dispensed in return for $" + billProduced + " produced." + " Remaining coins : " + coinsRemaining + " Denomination: " + coins.getDenomination(); 
		} else if (coins.getCoinCount() < calculatedCoins) {
			returnMessage = "Change unavailable in the desired denomination. Please choose a different denomination. Available : " + coins.getCoinCount();
		} else {
			returnMessage = "Invalid bill Produced";
		}
		return returnMessage; 
	}
	
	@GetMapping("/exchange/{billProduced}")
	public String getChange(@PathVariable Integer billProduced) {
		
		List<Coins> availableCoinsList = exchangeRepository.findByDefault();
		
		int tempBillProduced = billProduced;
		Double coinCounter = 0.0;
		String returnString = "";
		int coinsToReturn = 0;
		//System.out.println("availableCoinsList: "+availableCoinsList);
		
		int totalAvailable = exchangeRepository.findByTotalAvailable();
		
		if(totalAvailable < billProduced || !allowedBills.contains(billProduced)) {
			returnString = "INSUFFICIENT_FUNDS/INCORRECT_BILL_PROVIDED";
		} else {
			for(Coins coins : availableCoinsList) {
				coinCounter = (coins.getDenomination() * coins.getCoinCount());
				if(tempBillProduced <= coinCounter) {
					coinsToReturn = (int) (tempBillProduced / coins.getDenomination());
					returnString += "Returning "+coinsToReturn + "*" +coins.getDenomination() + " coins \n";
					coins.setCoinCount(coins.getCoinCount() - coinsToReturn);
					exchangeRepository.save(coins);
					break;
				} else if (tempBillProduced > coinCounter) {
					coinsToReturn = (int) (tempBillProduced / coins.getDenomination());
					if(coinsToReturn > coins.getCoinCount()) {
						coinsToReturn = coins.getCoinCount();
						returnString += "Returning " + coinsToReturn + "*" +coins.getDenomination() + "\n";
						coins.setCoinCount(coins.getCoinCount() - coinsToReturn);
						exchangeRepository.save(coins);
					}
					tempBillProduced -= coinCounter;
				}
			}
		}
		return returnString; 
	}
}

