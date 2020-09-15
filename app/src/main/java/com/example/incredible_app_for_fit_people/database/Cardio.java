package com.example.incredible_app_for_fit_people.database;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

import java.util.List;

@Table(name = "Cardio", id="_id")
public class Cardio extends Model {


    @Column(name = "Data")
    private String date;
    @Column(name = "Title")
    private String title;
    @Column(name = "Time")
    private String time;



    public Cardio(){

        super();
    }

    public Cardio(String date, String type, String time) {

        super();
        this.date = date;
        this.title = type;
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

}
