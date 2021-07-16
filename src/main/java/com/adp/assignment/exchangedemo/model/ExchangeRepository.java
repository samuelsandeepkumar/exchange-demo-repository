package com.adp.assignment.exchangedemo.model;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface ExchangeRepository extends CrudRepository<Coins, String>{
	  @Query(value = "SELECT * FROM COINS WHERE DENOMINATION = ?1 AND COIN_COUNT >= ?2", nativeQuery = true)
	  Coins findByDenomination(Double denomination, Integer coinsRequired);
	  
	  @Query(value = "SELECT * FROM COINS ORDER BY DENOMINATION DESC", nativeQuery = true)
	  List<Coins> findByDefault();
	  
	  @Query(value = "SELECT SUM(DENOMINATION * COIN_COUNT) FROM COINS", nativeQuery = true)
	  Integer findByTotalAvailable();
}
