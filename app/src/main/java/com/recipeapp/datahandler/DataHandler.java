package com.recipeapp.datahandler;

import java.io.IOException;
import java.util.ArrayList;

import com.recipeapp.model.Recipe;

//インターフェース
public interface DataHandler {

    //現在のモードを返す
    public String getMode();

    //レシピデータを読み込み、Recipeオブジェクトのリストとして返す
    public ArrayList<Recipe> readData() throws IOException;

    //指定されたRecipeオブジェクトを追加する
    public void writeData(Recipe recipe) throws IOException;

    //指定されたキーワードでレシピを検索し、一致するRecipeオブジェクトのリストを返す
    public ArrayList<Recipe> searchData(String keyword) throws IOException;
}
