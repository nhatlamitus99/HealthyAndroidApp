package com.example.healthy;

public class Item_RV {
    int pic;
    String title;
    int number;
    String time;
    int id;

    public int getId()
    {
        return id;
    }
    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Item_RV(int pic, String title, int number, String time,int id) {
        this.pic = pic;
        this.title = title;
        this.number = number;
        this.time = time;
        this.id=id;
    }

    public int getPic() {
        return pic;
    }

    public void setPic(int pic) {
        this.pic = pic;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Item_RV(int pic, String title, int number) {
        this.pic = pic;
        this.title = title;
        this.number = number;
    }
}
