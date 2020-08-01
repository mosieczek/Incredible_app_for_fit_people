package com.example.incredible_app_for_fit_people;

import android.content.ContentValues;


import java.io.Serializable;

public class Measurement implements Serializable   {

    private String data;
    private String szyja;
    private String klatkaPiersiowa;
    private String bicepsLewy;
    private String bicepsLewyNapiety;
    private String bicepsPrawy;
    private String bicepsPrawyNapiety;
    private String przedramieLewe;
    private String przedramiePrawe;
    private String talia;
    private String brzuch;
    private String biodra;
    private String udoLewe;
    private String udoPrawe;
    private String lydkaLewa;
    private String lydkaPrawa;
    private String tkankaTluszczowa;

    private transient ContentValues values;

    public Measurement(){}

    public Measurement(String data, String szyja, String klatkaPiersiowa, String bicepsLewy,
                        String bicepsLewyNapiety, String bicepsPrawy, String bicepsPrawyNapiety,
                        String przedramieLewe, String przedramiePrawe, String talia, String brzuch,
                        String biodra, String udoLewe, String udoPrawe, String lydkaLewa,
                        String lydkaPrawa, String tkankaTluszczowa) {
        this.data = data;
        this.szyja = szyja;
        this.klatkaPiersiowa = klatkaPiersiowa;
        this.bicepsLewy = bicepsLewy;
        this.bicepsLewyNapiety = bicepsLewyNapiety;
        this.bicepsPrawy = bicepsPrawy;
        this.bicepsPrawyNapiety = bicepsPrawyNapiety;
        this.przedramieLewe = przedramieLewe;
        this.przedramiePrawe = przedramiePrawe;
        this.talia = talia;
        this.brzuch = brzuch;
        this.biodra = biodra;
        this.udoLewe = udoLewe;
        this.udoPrawe = udoPrawe;
        this.lydkaLewa = lydkaLewa;
        this.lydkaPrawa = lydkaPrawa;
        this.tkankaTluszczowa = tkankaTluszczowa;
    }

    public void putValues(){
        values = new ContentValues();
        values.put(DBHandler.COL1, data);
        values.put(DBHandler.COL2, szyja);
        values.put(DBHandler.COL3, klatkaPiersiowa);
        values.put(DBHandler.COL4, bicepsLewy);
        values.put(DBHandler.COL5, bicepsLewyNapiety);
        values.put(DBHandler.COL6, bicepsPrawy);
        values.put(DBHandler.COL7, bicepsPrawyNapiety);
        values.put(DBHandler.COL8, przedramieLewe);
        values.put(DBHandler.COL9, przedramiePrawe);
        values.put(DBHandler.COL10, talia);
        values.put(DBHandler.COL11, brzuch);
        values.put(DBHandler.COL12, biodra);
        values.put(DBHandler.COL13, udoLewe);
        values.put(DBHandler.COL14, udoPrawe);
        values.put(DBHandler.COL15, lydkaLewa);
        values.put(DBHandler.COL16, lydkaPrawa);
        values.put(DBHandler.COL17, tkankaTluszczowa);
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getSzyja() {
        return szyja;
    }

    public void setSzyja(String szyja) {
        this.szyja = szyja;
    }

    public String getKlatkaPiersiowa() {
        return klatkaPiersiowa;
    }

    public void setKlatkaPiersiowa(String klatkaPiersiowa) {
        this.klatkaPiersiowa = klatkaPiersiowa;
    }

    public String getBicepsLewy() {
        return bicepsLewy;
    }

    public void setBicepsLewy(String bicepsLewy) {
        this.bicepsLewy = bicepsLewy;
    }

    public String getBicepsLewyNapiety() {
        return bicepsLewyNapiety;
    }

    public void setBicepsLewyNapiety(String bicepsLewyNapiety) {
        this.bicepsLewyNapiety = bicepsLewyNapiety;
    }

    public String getBicepsPrawy() {
        return bicepsPrawy;
    }

    public void setBicepsPrawy(String bicepsPrawy) {
        this.bicepsPrawy = bicepsPrawy;
    }

    public String getBicepsPrawyNapiety() {
        return bicepsPrawyNapiety;
    }

    public void setBicepsPrawyNapiety(String bicepsPrawyNapiety) {
        this.bicepsPrawyNapiety = bicepsPrawyNapiety;
    }

    public String getPrzedramieLewe() {
        return przedramieLewe;
    }

    public void setPrzedramieLewe(String przedramieLewe) {
        this.przedramieLewe = przedramieLewe;
    }

    public String getPrzedramiePrawe() {
        return przedramiePrawe;
    }

    public void setPrzedramiePrawe(String przedramiePrawe) {
        this.przedramiePrawe = przedramiePrawe;
    }

    public String getTalia() {
        return talia;
    }

    public void setTalia(String talia) {
        this.talia = talia;
    }

    public String getBrzuch() {
        return brzuch;
    }

    public void setBrzuch(String brzuch) {
        this.brzuch = brzuch;
    }

    public String getBiodra() {
        return biodra;
    }

    public void setBiodra(String biodra) {
        this.biodra = biodra;
    }

    public String getUdoLewe() {
        return udoLewe;
    }

    public void setUdoLewe(String udoLewe) {
        this.udoLewe = udoLewe;
    }

    public String getUdoPrawe() {
        return udoPrawe;
    }

    public void setUdoPrawe(String udoPrawe) {
        this.udoPrawe = udoPrawe;
    }

    public String getLydkaLewa() {
        return lydkaLewa;
    }

    public void setLydkaLewa(String lydkaLewa) {
        this.lydkaLewa = lydkaLewa;
    }

    public String getLydkaPrawa() {
        return lydkaPrawa;
    }

    public void setLydkaPrawa(String lydkaPrawa) {
        this.lydkaPrawa = lydkaPrawa;
    }

    public String getTkankaTluszczowa() {
        return tkankaTluszczowa;
    }

    public void setTkankaTluszczowa(String tkankaTluszczowa) {
        this.tkankaTluszczowa = tkankaTluszczowa;
    }

    public ContentValues getValues() {
        return values;
    }

}
