package com.example.myapplication;

import java.util.ArrayList;

class HomeCollection {
    public String date="";
    public String name="";
    public String subject="";
    public String description="";
    public  String image="";
    public  String freeze="";




    public static ArrayList<HomeCollection> date_collection_arr;
    public HomeCollection(String date, String name, String subject, String description, String image,String freeze){

        this.date=date;
        this.name=name;
        this.subject=subject;
        this.description= description;
        this.image=image;

        this.freeze = freeze;
    }
}
