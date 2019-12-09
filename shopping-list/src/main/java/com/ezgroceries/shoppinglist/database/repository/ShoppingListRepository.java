package com.ezgroceries.shoppinglist.database.repository;

import com.ezgroceries.shoppinglist.database.entities.ShoppingListEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface ShoppingListRepository extends CrudRepository<ShoppingListEntity, UUID> {

	//public ShoppingListEntity findById(UUID id);

	//ShoppingListEntity save(ShoppingListEntity newShoppingListEntity);

}
