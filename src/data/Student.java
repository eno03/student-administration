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
public class Student {

    private int indeks;
    private String smer;
    private String ime;
    private String prezime;
    private String datumRodjenja;
    private String mestoRodjenja, staratelj;
    private String datumUpisa;
    private String status;

    public Student(int indeks, String smer, String ime, String prezime, String datumRodjenja, String mestoRodjenja, String staratelj, String datumUpisa, String status) {
        this.indeks = indeks;
        this.smer = smer;
        this.ime = ime;
        this.prezime = prezime;
        this.datumRodjenja = datumRodjenja;
        this.mestoRodjenja = mestoRodjenja;
        this.staratelj = staratelj;
        this.datumUpisa = datumUpisa;
        this.status = status;
    }

    public int getIndeks() {
        return indeks;
    }

    public String getSmer() {
        return smer;
    }

    public String getIme() {
        return ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public String getDatumRodjenja() {
        return datumRodjenja;
    }

    public String getMestoRodjenja() {
        return mestoRodjenja;
    }

    public String getStaratelj() {
        return staratelj;
    }

    public String getStatus() {
        return status;
    }

    public String getDatumUpisa() {
        return datumUpisa;
    }

}
