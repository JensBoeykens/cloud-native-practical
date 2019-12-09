package com.ezgroceries.shoppinglist.database.repository;

import com.ezgroceries.shoppinglist.database.entities.CocktailEntity;
import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.UUID;

public interface CocktailRepository extends Repository<CocktailEntity, UUID> {

	public CocktailEntity findById(UUID id);

	public List<CocktailEntity> findByIdDrinkIn(List<String> idDrinks);

	CocktailEntity save(CocktailEntity newCocktailEntity);
}
