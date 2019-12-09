package com.ezgroceries.shoppinglist.database.repository;

import com.ezgroceries.shoppinglist.database.entities.CocktailShoppingListEntity;
import com.ezgroceries.shoppinglist.database.entities.CocktailShoppingListId;
import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.UUID;

public interface CocktailShoppingListRepository extends Repository<CocktailShoppingListEntity, CocktailShoppingListId> {

	public List<CocktailShoppingListEntity> findByShoppingListId(UUID shoppingListId);

	CocktailShoppingListEntity save(CocktailShoppingListEntity newCocktailShoppingListEntity);

}
