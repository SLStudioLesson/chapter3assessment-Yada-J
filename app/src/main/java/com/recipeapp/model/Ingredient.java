package com.recipeapp.model;

public class Ingredient {
    private String name;

    //コンストラクタ
    public Ingredient(String name) {
        this.name = name;
    }

    //ゲッター
    public String getName() {
        return name;
    }
}
