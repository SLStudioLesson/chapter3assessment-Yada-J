package com.recipeapp.datahandler;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import com.recipeapp.model.Ingredient;
import com.recipeapp.model.Recipe;

public class CSVDataHandler implements DataHandler {

    private BufferedWriter writer;
    //レシピデータを格納するCSVファイルのパス。
    private String filePath;

    //コンストラクタ
    public CSVDataHandler() {
        filePath = "app/src/main/resources/recipes.csv";
    }
    public CSVDataHandler(String filePath) {
        this.filePath = filePath;
    }
    
    //メソッドのオーバーライド
    public String getMode() {
        return "CSV";
    }
    public ArrayList<Recipe> readData() throws IOException {
        ArrayList<Recipe> recipeList =  new ArrayList<>();
        //recipes.txtからレシピデータを読み込み
        try  (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            //ファイルの中の行がなくなるまで継続
            while ((line = reader.readLine()) != null) {
                //最初の,でレシピと材料の2分割にして配列に入れる
                String[] keyValue = line.split(",", 2); //

                //配列数が２ならレシピ名と材料を取得
                if (keyValue.length == 2) {
                    String recipeName = keyValue[0];
                    String ingredientsString = keyValue[1];
                    
                    // 材料をArrayList<Ingredient>に変換
                    ArrayList<Ingredient> ingredients = new ArrayList<>(Arrays.asList(
                        new Ingredient(ingredientsString)));

                    // Recipeオブジェクトを作成し、リストに追加
                    Recipe recipe = new Recipe(recipeName, ingredients);
                    recipeList.add(recipe);
                    }

            }
        } catch (IOException e) {
                //IOExceptionが発生したときはError reading file: 例外のメッセージとコンソールに表示します。
            System.out.println("Error reading file:" + e.getMessage());
        }
        //リストを返す
        return recipeList;
    }
    public void writeData(Recipe recipe) throws IOException {
        //レシピ名を取得
        String recipeName = recipe.getName();

        //材料の取得
        ArrayList<Ingredient> ingredients = recipe.getIngredients();
        String str = "";
        //材料を,で結合
        for (int i = 0; i < ingredients.size(); i++) {
            str += ingredients.get(i).getName() + ",";
        }

        // レシピ名と材料をカンマで結合
        String writeString = recipeName + "," + str;

        writer = new BufferedWriter(new FileWriter(filePath, true));

        try {
            //新しいレシピを書き込む
            writer.write(writeString);
            //書き込み後に改行する
            writer.newLine();
        } finally {
            writer.close();
        }
    }

    public ArrayList<Recipe> searchData(String keyword) {
        return null;
    }

}
