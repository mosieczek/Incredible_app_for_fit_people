package com.example.incredible_app_for_fit_people.database;


import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

import java.util.List;

@Table(name = "Exercise", id="_id")
public class Exercise extends Model {

    @Column(name = "Training")
    private Training traning;

    @Column(name = "Cwiczenie")
    private String cwiczenie;



    public List<Series> sets() {
        return getMany(Series.class, "Exercise");
    }

    public Exercise(){

        super();
    }

    public Exercise(Training traning, String cwiczenie) {

        super();
        this.traning = traning;
        this.cwiczenie = cwiczenie;


    }

    public String getCwiczenie() {
        return cwiczenie;
    }

    public void setCwiczenie(String cwiczenie) {
        this.cwiczenie = cwiczenie;
    }

    public Training getTraning() {
        return traning;
    }

    public void setTraning(Training traning) {
        this.traning = traning;
    }
}
