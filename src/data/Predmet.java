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
public class Predmet {

    private int id;
    private String oznaka;
    private String naziv;
    private int espb;

    public Predmet(int id, String oznaka, String naziv, int espb) {
        this.id = id;
        this.oznaka = oznaka;
        this.naziv = naziv;
        this.espb = espb;
    }

    public int getId() {
        return id;
    }

    public String getOznaka() {
        return oznaka;
    }

    public String getNaziv() {
        return naziv;
    }

    public int getEspb() {
        return espb;
    }

}
