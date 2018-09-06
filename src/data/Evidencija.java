/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

/**
 *
 * @author nikol
 */
public class Evidencija {
    private int id;
    private String rok;
    private int indeks;
    private String predmet;
    private String datum;
    private String nacin;
    private int ocena;

    public Evidencija(int id,int indeks, String rok,  String predmet, String datum, String nacin, int ocena) {
        this.id = id;
        this.rok = rok;
        this.indeks = indeks;
        this.predmet = predmet;
        this.datum = datum;
        this.nacin = nacin;
        this.ocena = ocena;
    }

    public int getId() {
        return id;
    }

    public String getRok() {
        return rok;
    }

    public int getIndeks() {
        return indeks;
    }

    public String getPredmet() {
        return predmet;
    }

    public String getDatum() {
        return datum;
    }

    public String getNacin() {
        return nacin;
    }

    public int getOcena() {
        return ocena;
    }
    
    
    
    
}
