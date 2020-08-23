package com.example.incredible_app_for_fit_people.database;


import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

import java.util.Date;
import java.util.List;

@Table(name = "Training", id= "_id")
public class Training extends Model {


    @Column(name = "Data")
    private String date;
    @Column(name = "Typ")
    private String type;

    public List<Exercise> exercises() {
        return getMany(Exercise.class, "Training");
    }

    public Training(){

        super();
    }

    public Training(String date, String type) {

        super();
        this.date = date;
        this.type = type;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}

