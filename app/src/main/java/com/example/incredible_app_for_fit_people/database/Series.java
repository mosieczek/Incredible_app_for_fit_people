package com.example.incredible_app_for_fit_people.database;

import android.graphics.ColorSpace;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

@Table(name = "Series", id="_id")
public class Series extends Model {

    @Column(name = "Exercise")
    private Exercise exercise;

    @Column(name = "Obciazenie")
    private String obciazenie;
    @Column(name = "Serie")
    private String serie;
    @Column(name = "Powtorzenia")
    private String powtorzenia;

    public Series() {
        super();
    }

    public Series(Exercise exercise, String obciazenie, String serie, String powtorzenia) {

        super();
        this.exercise = exercise;
        this.obciazenie = obciazenie;
        this.serie = serie;
        this.powtorzenia = powtorzenia;
    }

    public void updateSeries(Exercise exercise, String obciazenie, String serie, String powtorzenia) {

        this.exercise = exercise;
        this.obciazenie = obciazenie;
        this.serie = serie;
        this.powtorzenia = powtorzenia;
    }

    public String getObciazenie() {
        return obciazenie;
    }

    public void setObciazenie(String obciazenie) {
        this.obciazenie = obciazenie;
    }

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public String getPowtorzenia() {
        return powtorzenia;
    }

    public void setPowtorzenia(String powtorzenia) {
        this.powtorzenia = powtorzenia;
    }

    public Exercise getExercise() {
        return exercise;
    }

    public void setExercise(Exercise exercise) {
        this.exercise = exercise;
    }
}
