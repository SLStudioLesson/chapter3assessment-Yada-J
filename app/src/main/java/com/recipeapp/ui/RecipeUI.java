package com.recipeapp.ui;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import com.recipeapp.datahandler.DataHandler;
import com.recipeapp.model.Ingredient;
import com.recipeapp.model.Recipe;

public class RecipeUI {
    private BufferedReader reader;
    private DataHandler dataHandler;

    public RecipeUI(DataHandler dataHandler) {
        reader = new BufferedReader(new InputStreamReader(System.in));
        this.dataHandler = dataHandler;
    }
    
    public void displayMenu() {

        System.out.println("Current mode: " + dataHandler.getMode());

        while (true) {
            try {
                System.out.println();
                System.out.println("Main Menu:");
                System.out.println("1: Display Recipes");
                System.out.println("2: Add New Recipe");
                System.out.println("3: Search Recipe");
                System.out.println("4: Exit Application");
                System.out.print("Please choose an option: ");

                String choice = reader.readLine();

                switch (choice) {
                    case "1":
                        displayRecipes();
                        break;
                    case "2":
                        addNewRecipe();
                        break;
                    case "3":
                        break;
                    case "4":
                        System.out.println("Exiting the application.");
                        return;
                    default:
                        System.out.println("Invalid choice. Please select again.");
                        break;
                }
            } catch (IOException e) {
                System.out.println("Error reading input from user: " + e.getMessage());
            }
        }
    }

    // displayRecipesメソッドの実装
    private void displayRecipes() {
        try {
            // DataHandlerからレシピデータを読み込み
                ArrayList<Recipe> recipes = dataHandler.readData();

                // レシピデータが存在しない場合No recipes available.と出力
                if (recipes.isEmpty()) {
                    System.out.println("No recipes available.");
                } else {
                    System.out.println("Recipes:");
                    System.out.println("-----------------------------------");
                    //存在する場合レシピデータの表示
                    for (Recipe recipe : recipes) {
                        // レシピ名を表示
                        System.out.println("Recipe Name: " + recipe.getName());
                        // 材料の表示
                        System.out.println("Main Ingredients: " + recipe.getIngredients().get(0).getName());
                        System.out.println("-----------------------------------");
                    }
                }
            } catch (IOException e) {
            //IOExceptionが発生したときはError reading file: 例外のメッセージとコンソールに表示します。
            System.out.println("Error reading file:" + e.getMessage());
        }
    }

    //addNewRecipe()メソッドの実装
    private void addNewRecipe() {
        try {
            //レシピ名を入力
            System.out.println("Adding a new recipe.");
            System.out.print("Enter recipe name: ");
            String recipeName = reader.readLine();

            System.out.println("Enter ingredients (type 'done' when finished):");
            //受け付けた材料を格納するリスト
            ArrayList<Ingredient> ingredients = new ArrayList<>();
            while (true) {
                //材料の入力
                System.out.print("Ingredient: ");
                String ingredientName = reader.readLine();

                //入力はdoneと入力するまで入力を受け付ける
                if (ingredientName.equals("done")) {
                    break;
                } else {
                    //入力された情報をリストに追加
                    ingredients.add(new Ingredient(ingredientName));
                }
            }
                // インスタンスの作成
                Recipe newRecipe = new Recipe(recipeName, ingredients);
                // CSVDataHandlerを使用してレシピをcsvに保存
                dataHandler.writeData(newRecipe);
                System.out.println("Recipe added successfully.");
                
                //IOExceptionを受け取った場合はFailed to add new recipe: 例外のメッセージとコンソールに表示
            } catch (IOException e) {
                System.out.println("Failed to add new recipe: " + e.getMessage());
        }
    }
}
