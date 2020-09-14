package com.example.incredible_app_for_fit_people.database;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

import java.util.List;

@Table(name = "Cardio", id="_id")
public class Cardio extends Model {


    @Column(name = "Data")
    private String date;
    @Column(name = "Typ")
    private String type;
    @Column(name = "Time")
    private String time;

    @Column(name = "Activity")
    private String activity;



    public Cardio(){

        super();
    }

    public Cardio(String date, String type, String time, String activity) {

        super();
        this.date = date;
        this.type = type;
        this.time = time;
        this.activity = activity;
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

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }
}
