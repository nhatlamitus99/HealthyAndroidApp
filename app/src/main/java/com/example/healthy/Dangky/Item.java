package com.example.healthy.Dangky;

public class Item {
    String name;
    int count;
    String time;
    int id;

    public int getId()
    {
        return  id;
    }
    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Item(String name, int count,int id, String time) {
        this.name = name;
        this.count = count;
        this.time = time;
        this.id=id;
    }

    public Item(String name, int count) {
        this.name = name;
        this.count = count;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
