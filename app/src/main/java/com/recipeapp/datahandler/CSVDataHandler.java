package com.recipeapp.datahandler;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import com.recipeapp.model.Recipe;

public class CSVDataHandler implements DataHandler {

    //レシピデータを格納するCSVファイルのパス。
    private String filePath;

    //コンストラクタ
    public CSVDataHandler() {
        this.filePath = "src/main/resources/recipes.csv";
    }
    public CSVDataHandler(String filePath) {
        this.filePath = filePath;
    }
    
    //メソッドのオーバーライド
    public String getMode() {
        return "CSV";
    }
    public ArrayList<Recipe> readData() {
        ArrayList<Recipe> recipe =  new ArrayList<>();
        //recipes.txtからレシピデータを読み込み
        try  (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            //ファイルの中の行がなくなるまで継続
            while ((line = reader.readLine()) != null) {
                //リストに追加
                recipe.add(line);
            }
        } catch (IOException e) {
                //IOExceptionが発生したときはError reading file: 例外のメッセージとコンソールに表示します。
            System.out.println("Error reading file:" + e.getMessage());
        }
        //リストを返す
        return recipe;
    }
    public void writeData(Recipe recipe){

    }
    public ArrayList<Recipe> searchData(String keyword) {
        return null;
    }

}
