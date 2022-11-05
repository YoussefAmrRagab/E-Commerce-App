package com.example.myapplication;

public class DataItem {

    private String Id;
    private String Title;
    private String Des;
    private String Price;
    private String Img;
    private int Fav;




    public DataItem(String id,String title, String des, String price,String img ,int fav) {
        Id = id;
        Title=title;
        Des = des;
        Price = price;
        Img = img;
        Fav = fav;
    }


    public String getId() {
        return Id;
    }
    public String getTitle() {
        return Title;
    }
    public String getDes() {
        return Des;
    }
    public String getPrice() {
        return Price;
    }
    public int getFav() {
        return Fav;
    }
    public String getImg() {
        return Img;
    }

}

