package com.ezgroceries.shoppinglist.database;

import java.util.ArrayList;
import java.util.List;

public class CocktailDBResponse {

    private List<DrinkResource> drinks;

    public List<DrinkResource> getDrinks() {
        return drinks;
    }

    public void setDrinks(List<DrinkResource> drinks) {
        this.drinks = drinks;
    }

    public static class DrinkResource {
        public String idDrink;
        public String strDrink;
        public String strDrinkAlternate;
        public String strDrinkES;
        public String strDrinkDE;
        public String strDrinkFR;
        public String strTags;
        public String strVideo;
        public String strCategory;
        public String strIBA;
        public String strAlcoholic;
        public String strGlass;
        public String strInstructions;
        public String strInstructionsES;
        public String strInstructionsDE;
        public String strInstructionsFR;
        public String strDrinkThumb;
        public String strIngredient1;
        public String strIngredient2;
        public String strIngredient3;
        public String strIngredient4;
        public String strIngredient5;
        public String strIngredient6;
        public String strIngredient7;
        public String strIngredient8;
        public String strIngredient9;
        public String strIngredient10;
        public String strIngredient11;
        public String strIngredient12;
        public String strIngredient13;
        public String strIngredient14;
        public String strIngredient15;
        public String strMeasure1;
        public String strMeasure2;
        public String strMeasure3;
        public String strMeasure4;
        public String strMeasure5;
        public String strMeasure6;
        public String strMeasure7;
        public String strMeasure8;
        public String strMeasure9;
        public String strMeasure10;
        public String strMeasure11;
        public String strMeasure12;
        public String strMeasure13;
        public String strMeasure14;
        public String strMeasure15;
        public String strCreativeCommonsConfirmed;
        public String dateModified;

        public List<String> getIngredients() {
            List<String> ingredients = new ArrayList<String>();

            if (strIngredient1 != null) {
                ingredients.add(strIngredient1);
            }
            if (strIngredient2 != null) {
                ingredients.add(strIngredient2);
            }
            if (strIngredient3 != null) {
                ingredients.add(strIngredient3);
            }
            if (strIngredient4 != null) {
                ingredients.add(strIngredient4);
            }
            if (strIngredient5 != null) {
                ingredients.add(strIngredient5);
            }
            if (strIngredient6 != null) {
                ingredients.add(strIngredient6);
            }
            if (strIngredient7 != null) {
                ingredients.add(strIngredient7);
            }
            if (strIngredient8 != null) {
                ingredients.add(strIngredient8);
            }

            return ingredients;
        }

        public static String getIdDrink(DrinkResource drinkResource) {
            return drinkResource.idDrink;
        }

        public String getIdDrink() {
            return idDrink;
        }

        public String getStrDrink() {
            return strDrink;
        }

        public String getStrDrinkThumb() {
            return strDrinkThumb;
        }

        public String getStrInstructions() {
            return strInstructions;
        }

        public String getStrGlass() {
            return strGlass;
        }
    }
}
