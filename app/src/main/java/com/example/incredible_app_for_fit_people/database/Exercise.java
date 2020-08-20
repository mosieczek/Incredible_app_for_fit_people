package com.example.incredible_app_for_fit_people.database;


import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

@Table(name = "Exercises", id = "_id")
public class Exercise extends Model {

    @Column(name = "Nazwa")
    String nazwa;
    @Column(name = "Obciazenie")
    String obciazenie;
    @Column(name = "Serie")
    String serie;
    @Column(name = "Powtorzenia")
    String powtorzenia;


}
