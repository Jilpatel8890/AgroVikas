package com.example.register.model;

public class item {
    private static int id;

    public item() {
    }
    public item(int a){
        id = a;
    }

    public static int getId(){
        return id;
    }
}
