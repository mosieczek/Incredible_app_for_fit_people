package com.example.incredible_app_for_fit_people.database;


import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

@Table(name = "Exercise", id="_id")
public class Exercise extends Model {

    @Column(name = "Training")
    private Training traning;

    @Column(name = "Cwiczenie")
    private String cwiczenie;
    @Column(name = "Obciazenie")
    private String obciazenie;
    @Column(name = "Serie")
    private String serie;
    @Column(name = "Powtorzenia")
    private String powtorzenia;



    public Exercise(){

        super();
    }

    public Exercise(Training traning, String cwiczenie, String obciazenie, String serie, String powtorzenia) {

        super();
        this.traning = traning;
        this.cwiczenie = cwiczenie;
        this.obciazenie = obciazenie;
        this.serie = serie;
        this.powtorzenia = powtorzenia;

    }

    public String getCwiczenie() {
        return cwiczenie;
    }

    public void setCwiczenie(String cwiczenie) {
        this.cwiczenie = cwiczenie;
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

    public Training getTraning() {
        return traning;
    }

    public void setTraning(Training traning) {
        this.traning = traning;
    }
}
