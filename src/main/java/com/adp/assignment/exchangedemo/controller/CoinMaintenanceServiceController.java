package com.adp.assignment.exchangedemo.controller;

import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.adp.assignment.exchangedemo.model.CoinRepository;
import com.adp.assignment.exchangedemo.model.Coins;

@RestController
@RequestMapping("/exchange-demo")
public class CoinMaintenanceServiceController {
	
	@Autowired
	private CoinRepository coinRepository;

	@GetMapping(value="/coins/all")
	public List<Coins> getAllCoins() {
		return (List<Coins>) coinRepository.findAll();
	}
	
	@GetMapping(value="/coins/{id}")
	public Coins getCoins(@PathVariable String id) {
		System.out.println("Returrn"+coinRepository.findById(Integer.valueOf(id)));
		return coinRepository.findById(Integer.valueOf(id)).orElse(null);
	}
	
	@PostMapping(value="/add/coins")
	public String addCoins(@RequestBody List<Coins> coins) {
		//System.out.println("reqBody" +reqBody);
		coinRepository.saveAll(coins);
		return "Coins Saved";
	}
	
	@PutMapping(value="/coins/{id}")
	public void updateCoins(@RequestBody Coins coins, @PathVariable String id) {
		coinRepository.save(coins);
	}
	
	@DeleteMapping(value="/coins/{id}")
	public void deleteCoins(@PathVariable String id) {
		coinRepository.deleteById(Integer.valueOf(id));
	}
	
	public Coins getJsonObject(String reqString) {
		Coins coins = new Coins();
		try {
			JSONObject inputJSON = new JSONObject(reqString);
			coins.setId(inputJSON.getInt("id"));
			coins.setDenomination(inputJSON.getDouble("denomination"));
			coins.setDenominationDescription(inputJSON.getString("denominationDescription"));
			coins.setCoinCount(inputJSON.getInt("coinCount"));
			System.out.println(coins.toString());
		} catch(Exception e) {
			return coins;
		}
		return coins;
	}
}
