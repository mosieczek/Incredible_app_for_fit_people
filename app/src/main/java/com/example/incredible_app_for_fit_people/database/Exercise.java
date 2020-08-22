package com.example.incredible_app_for_fit_people.database;


import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

@Table(name = "Exercises", id = "_id")
public class Exercise extends Model {

    @Column(name = "Cwiczenie")
    String cwiczenie;
    @Column(name = "Obciazenie")
    String obciazenie;
    @Column(name = "Serie")
    String serie;
    @Column(name = "Powtorzenia")
    String powtorzenia;

    @Column(name = "Training")
    public Traning traning;

    public Exercise(){

        super();
    }

    public Exercise(Traning traning, String cwiczenie, String obciazenie, String serie, String powtorzenia) {

        super();
        this.traning = traning;

        this.cwiczenie = cwiczenie;
        this.obciazenie = obciazenie;
        this.serie = serie;
        this.powtorzenia = powtorzenia;

    }
}
