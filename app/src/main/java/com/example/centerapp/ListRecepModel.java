package com.example.centerapp;

public class ListRecepModel {
    private String textView1;
    private String textView2;
    private String textView3;
    private String textView4;

    ListRecepModel(String textView1,
                   String textView2,
                   String textView3,
                   String textView4) {
        this.textView1 = textView1;
        this.textView2 = textView2;
        this.textView3 = textView3;
        this.textView4 = textView4;
    }

    public String getLastName() {
        return textView1;
    }

    public String getName() {
        return textView2;
    }

    public String getVaccine() {return textView3;}

    public String getTime() {
        return textView4;
    }






}
