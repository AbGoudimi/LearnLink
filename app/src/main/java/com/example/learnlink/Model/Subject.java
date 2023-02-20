package com.example.learnlink.Model;

import com.example.learnlink.Model.Year;

public class Subject {
    private String id;
    private String name;
    private Year year;

    public Subject(String id,String name, Year year) {
        this.id = id;
        this.name = name;
        this.year = year;
    }
    public Subject() {

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

    public Year getYear() {
        return year;
    }

    public void setYear(Year year) {
        this.year = year;
    }

}
