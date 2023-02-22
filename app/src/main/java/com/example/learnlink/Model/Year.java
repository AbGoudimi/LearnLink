package com.example.learnlink.Model;

public class Year {
    private String id;
    private String name;


    public Year(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public Year() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
